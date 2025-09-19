package ru.yandex.kingartaved.sb_notepad.service;

import ru.yandex.kingartaved.sb_notepad.data.constant.NotePriorityEnum;
import ru.yandex.kingartaved.sb_notepad.data.model.Content;
import ru.yandex.kingartaved.sb_notepad.data.model.Note;

import java.util.List;

public interface NoteService {
    // Общие методы
    Note createNote(Note noteToCreate);
    Note getNoteById(Long id);
    List<Note> getAllNotes();
    void deleteNote(Long id);
    Note updateNote(Long id, Note noteToUpdate);
    // Специализированный метод создания оптом
    List<Note> createNotes(List<Note> notesToCreate);
    // Специализированные методы обновления
    Note updateNoteTitle(Long id, String titleToUpdate);
    Note updateNoteContent(Long id, Content newContent);
    Note toggleNotePinned(Long id);
    Note updateNotePriority(Long id, NotePriorityEnum priorityToUpdate);

}
