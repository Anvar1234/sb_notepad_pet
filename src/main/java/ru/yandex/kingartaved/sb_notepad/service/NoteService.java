package ru.yandex.kingartaved.sb_notepad.service;

import ru.yandex.kingartaved.sb_notepad.data.model.Note;

import java.util.List;

public interface NoteService {
    Note createNote(Note note);

    Note getNoteById(Long id);
    List<Note> getAllNotes();
}
