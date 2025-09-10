package ru.yandex.kingartaved.sb_notepad.service;

import org.springframework.stereotype.Service;
import ru.yandex.kingartaved.sb_notepad.data.constant.NotePriorityEnum;
import ru.yandex.kingartaved.sb_notepad.data.constant.NoteTypeEnum;
import ru.yandex.kingartaved.sb_notepad.data.model.ChecklistContent;
import ru.yandex.kingartaved.sb_notepad.data.model.ChecklistTask;
import ru.yandex.kingartaved.sb_notepad.data.model.Note;
import ru.yandex.kingartaved.sb_notepad.data.model.TextContent;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NoteServiceImpl implements NoteService {

    private final Map<Long, Note> noteMap;
    private AtomicLong idCounter; //todo: в итоге final или нет?

    public NoteServiceImpl() {
        noteMap = new HashMap<>();
        idCounter = new AtomicLong();
    }

    //            Map.of(
//            1L, Note.builder()
//                    .id(1L)
//                    .title("Note1 title")
//                    .createdAt(LocalDateTime.of(2024, 5, 24, 0, 0))
//                    .updatedAt(LocalDateTime.of(2025, 6, 25, 1, 1))
//                    .pinned(false)
//                    .type(NoteTypeEnum.TEXT_NOTE)
//                    .content(new TextContent("Text1 note body"))
//                    .build(),
//            2L, Note.builder()
//                    .id(2L)
//                    .title("Checklist2 title")
//                    .createdAt(LocalDateTime.now())
//                    .updatedAt(LocalDateTime.now())
//                    .pinned(false)
//                    .type(NoteTypeEnum.CHECKLIST)
//                    .content(new ChecklistContent(
//                            List.of(new ChecklistTask("Task1", false),
//                                    new ChecklistTask("Task2", true))))
//                    .build(),
//            3L, Note.builder()
//                    .id(3L)
//                    .title("Название3")
//                    .createdAt(LocalDateTime.now())
//                    .updatedAt(LocalDateTime.now().plusDays(7))
//                    .pinned(false)
//                    .type(NoteTypeEnum.TEXT_NOTE)
//                    .content(new TextContent("TextNote3 new content"))
//                    .build()
//    );


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
        if(noteToCreate.getPriority() != null) {
            throw new IllegalArgumentException("Входящий приоритет должен быть null");
        }

        var createdNote = Note.builder()
                .id(idCounter.incrementAndGet()) //задаем только здесь
                .title(noteToCreate.getTitle())
                .createdAt(noteToCreate.getCreatedAt())
                .updatedAt(noteToCreate.getUpdatedAt())
                .pinned(noteToCreate.isPinned())
                .type(noteToCreate.getType())
                .priority(NotePriorityEnum.BASE) //задаем только здесь
                .content(noteToCreate.getContent())
                .build();

        noteMap.put(createdNote.getId(), createdNote);
        return createdNote;
    }

}
