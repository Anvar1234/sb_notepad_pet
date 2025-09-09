package ru.yandex.kingartaved.sb_notepad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.kingartaved.sb_notepad.data.model.Note;
import ru.yandex.kingartaved.sb_notepad.service.NoteService;

import java.util.List;

@RequestMapping("api/v1/notes")
@RestController
public class NoteControllerImpl implements NoteController {

    private final NoteService noteService;

    public NoteControllerImpl(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable("id") Long id) {
       return noteService.getNoteById(id);
    }

    @GetMapping
    public List<Note> getAllNotes() {
       return noteService.getAllNotes();
    }

    @Override
    @GetMapping("/test")
    public String helloWorld() {
        return "Hello World";
    }
}
