package ru.yandex.kingartaved.sb_notepad.service;

import ru.yandex.kingartaved.sb_notepad.data.constant.NotePriorityEnum;
import ru.yandex.kingartaved.sb_notepad.data.model.Note;
import ru.yandex.kingartaved.sb_notepad.dto.ContentDto;
import ru.yandex.kingartaved.sb_notepad.dto.request.UpdateNoteRequestDto;

import java.util.List;

public interface NoteService {
    // Общие методы
    Note createNote(Note noteToCreate);
    Note getNoteById(Long id);
    List<Note> getAllNotes();
    void deleteNote(Long id);
    Note updateNote(Long id, UpdateNoteRequestDto noteToUpdate);
    // Специализированный метод создания оптом
    List<Note> createNotes(List<Note> notesToCreate);
    // Специализированные методы обновления
    Note updateNoteTitle(Long id, String titleToUpdate);
    Note updateNoteContent(Long id, ContentDto newContentDto);
    Note toggleNotePinned(Long id);
    Note updateNotePriority(Long id, NotePriorityEnum priorityToUpdate);

}
