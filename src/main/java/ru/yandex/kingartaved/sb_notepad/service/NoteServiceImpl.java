package ru.yandex.kingartaved.sb_notepad.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.yandex.kingartaved.sb_notepad.data.constant.NotePriorityEnum;
import ru.yandex.kingartaved.sb_notepad.data.mapper.ContentMapper;
import ru.yandex.kingartaved.sb_notepad.data.mapper.NoteMapper;
import ru.yandex.kingartaved.sb_notepad.data.model.Content;
import ru.yandex.kingartaved.sb_notepad.data.model.Note;
import ru.yandex.kingartaved.sb_notepad.dto.ContentDto;
import ru.yandex.kingartaved.sb_notepad.dto.request.UpdateNoteRequestDto;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NoteServiceImpl implements NoteService {

    private final Map<Long, Note> noteMap;
    private final NoteMapper mapper;
    private final ContentMapper contentMapper;
    private final AtomicLong idCounter;

    Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

    public NoteServiceImpl(NoteMapper mapper, ContentMapper contentMapper) {
        this.noteMap = new HashMap<>();
        this.mapper = mapper;
        this.contentMapper = contentMapper;
        this.idCounter = new AtomicLong();
    }

    @Override
    public Note getNoteById(Long id) {
        if (!noteMap.containsKey(id)) {
            throw new NoSuchElementException("Заметки с id " + id + " не существует");
        }
        return noteMap.get(id);
    }

    public List<Note> getAllNotes() {
        return noteMap.values().stream().toList();
    }

    @Override
    public Note createNote(Note noteToCreate) {
        if (noteToCreate.getId() != null) {
            throw new IllegalArgumentException("Входящее id должно быть null");
        }
        if (noteToCreate.getPriority() != null) {
            throw new IllegalArgumentException("Входящий приоритет должен быть null");
        }

        var createdNote = Note.builder()
                .id(idCounter.incrementAndGet()) //задаем только здесь
                .title(noteToCreate.getTitle())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .pinned(false)
                .type(noteToCreate.getType())
                .priority(NotePriorityEnum.BASE) //задаем только здесь
                .content(noteToCreate.getContent())
                .build();

        noteMap.put(createdNote.getId(), createdNote);
        return createdNote;
    }

    @Override
    public List<Note> createNotes(List<Note> notesToCreate) {
        if (notesToCreate != null) {
            notesToCreate.forEach(this::createNote);
            return noteMap.values().stream().toList();
        }
        return List.of();
    }

    @Override
    public Note updateNote(Long id, UpdateNoteRequestDto requestDto) {
        validateNoteExists(id);
        var existingNote = noteMap.get(id);
        ensureNoteTypeMatchesContentType(id, requestDto.contentDto());

        Note updatedNote = mapper.updateNoteFromDto(requestDto, existingNote); //маппинг не создает новый объект, а дополняет существующий
        updatedNote.setUpdatedAt(LocalDateTime.now());

        noteMap.put(id, updatedNote);
        return updatedNote;
    }

    @Override
    public Note updateNoteContent(Long id, ContentDto contentDto) {
        validateNoteExists(id);
        Note existingNote = noteMap.get(id);

        if (contentDto == null) throw new IllegalArgumentException("Содержимое заметки не может быть null");
        ensureNoteTypeMatchesContentType(id, contentDto);
        Content updatedContent = contentMapper.toEntity(contentDto);

        Note updatedNote = Note.builder()
                .id(existingNote.getId())
                .title(existingNote.getTitle())
                .createdAt(existingNote.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .pinned(existingNote.isPinned())
                .type(existingNote.getType())
                .priority(existingNote.getPriority())
                .content(updatedContent) //todo
                .build();

        noteMap.put(id, updatedNote);
        return updatedNote;
    }

    @Override
    public Note updateNoteTitle(Long id, String titleToUpdate) {
        validateNoteExists(id);
        Note existingNote = noteMap.get(id);

        if (titleToUpdate == null) {
            throw new IllegalArgumentException("Название заметки не может быть null");
        }

        if (titleToUpdate.isBlank()) {
            throw new IllegalArgumentException("Название заметки не может быть пустым");
        }

        Note updatedNote = Note.builder()
                .id(existingNote.getId())
                .title(titleToUpdate) //todo
                .createdAt(existingNote.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .pinned(existingNote.isPinned())
                .type(existingNote.getType())
                .priority(existingNote.getPriority())
                .content(existingNote.getContent())
                .build();

        noteMap.put(id, updatedNote);
        return updatedNote;
    }

    @Override
    public Note toggleNotePinned(Long id) {
        validateNoteExists(id);
        Note existingNote = noteMap.get(id);

        Note updatedNote = Note.builder()
                .id(existingNote.getId())
                .title(existingNote.getTitle())
                .createdAt(existingNote.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .pinned(!existingNote.isPinned()) //todo
                .type(existingNote.getType())
                .priority(existingNote.getPriority())
                .content(existingNote.getContent())
                .build();

        noteMap.put(id, updatedNote);
        return updatedNote;
    }

    @Override
    public Note updateNotePriority(Long id, NotePriorityEnum priorityToUpdate) {
        validateNoteExists(id);
        Note existingNote = noteMap.get(id);

        if (priorityToUpdate == null) {
            throw new IllegalArgumentException("Приоритет заметки не может быть null");
        }

        try {
            NotePriorityEnum.valueOf(priorityToUpdate.name());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Передаваемый приоритет: '" + priorityToUpdate +
                            "' не соответствует енаму: 'NotePriorityEnum'. Поддерживаемые значения: " +
                            Arrays.toString(NotePriorityEnum.values()), e);
        }

        Note updatedNote = Note.builder()
                .id(existingNote.getId())
                .title(existingNote.getTitle())
                .createdAt(existingNote.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .pinned(existingNote.isPinned())
                .type(existingNote.getType())
                .priority(priorityToUpdate) //todo
                .content(existingNote.getContent())
                .build();

        noteMap.put(id, updatedNote);
        return updatedNote;
    }

    @Override
    public void deleteNote(Long id) {
        validateNoteExists(id);
        noteMap.remove(id);
    }

    private void validateNoteExists(Long id) {
        if (!noteMap.containsKey(id)) {
            throw new NoSuchElementException("Заметки с id " + id + " не существует");
        }
    }

    private void ensureNoteTypeMatchesContentType(Long id, ContentDto contentDto) {

        Note existingNote = noteMap.get(id);

        if (existingNote.getContent().getSupportedType() != contentDto.getSupportedType()) {
            logger.warn("Тип заметки с id = {} это = {}, а тип контента, который передан в метод updateNote = {}",
                    id,
                    existingNote.getType(),
                    contentDto.getSupportedType());
            throw new IllegalArgumentException("Тип заметки и тип передаваемого контента не совпадают");
        }
    }
}
