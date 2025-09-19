package ru.yandex.kingartaved.sb_notepad.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.kingartaved.sb_notepad.data.constant.NotePriorityEnum;
import ru.yandex.kingartaved.sb_notepad.data.model.Content;
import ru.yandex.kingartaved.sb_notepad.data.model.Note;
import ru.yandex.kingartaved.sb_notepad.dto.request.UpdateNotePriorityRequestDto;
import ru.yandex.kingartaved.sb_notepad.dto.request.UpdateTitleRequestDto;
import ru.yandex.kingartaved.sb_notepad.service.NoteService;

import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("api/v1/notes")
@RestController
public class NoteControllerImpl implements NoteController {

    private static Logger logger = LoggerFactory.getLogger(NoteControllerImpl.class);
    private final NoteService noteService;

    public NoteControllerImpl(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK) //это код 200. Можно написать ResponseEntity.status(200) - работает так же.
                    .body(noteService.getNoteById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404) //404 - не найдено
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note noteToCreate) {
        return ResponseEntity.status(HttpStatus.CREATED) //это код 201
                .body(noteService.createNote(noteToCreate));
    }

    @PostMapping("/create_notes")
    public ResponseEntity<List<Note>> createNotes(
            @RequestBody List<Note> notesToCreate
    ) {
        return ResponseEntity.status(HttpStatus.CREATED) //это код 201
                .body(noteService.createNotes(notesToCreate));
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Note> updateNote(
            @PathVariable("id") Long id,
            @RequestBody Note noteToUpdate) {
        var updated = noteService.updateNote(id, noteToUpdate);

        return ResponseEntity.ok(updated);
    }

    @PatchMapping({"/{id}/title"})
    public ResponseEntity<Note> updateNoteTitle(
            @PathVariable("id") Long id,
            @RequestBody UpdateTitleRequestDto titleRequestDto
            ) {
        logger.info("Попытка изменения заголовка заметки с id = {}", id);
        try {
            var updated = noteService.updateNoteTitle(id, titleRequestDto.title());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400)
                    .build(); // 400 или HttpStatus.BAD_REQUEST - ошибка в запросе (null или пустая строка)
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404)
                    .build(); // 404 или HttpStatus.NOT_FOUND - не найдено
        }
    }

    @PatchMapping({"/{id}/content"})
    public ResponseEntity<Note> updateNoteContent(
            @PathVariable("id") Long id,
            @RequestBody Content contentToUpdate
    ) {
        logger.info("Попытка изменения контента заметки с id = {}", id);
        try {
            var updated = noteService.updateNoteContent(id, contentToUpdate);
            return ResponseEntity.status(200)
                    .body(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400)
                    .build(); // 400 или HttpStatus.BAD_REQUEST - ошибка в запросе (null или пустая строка)
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404)
                    .build(); // 404 или HttpStatus.NOT_FOUND - не найдено
        }
    }

    @PatchMapping({"/{id}/toggle_pinned"})
    public ResponseEntity<Note> toggleNotePinned(
            @PathVariable("id") Long id
    ) {
        logger.info("Попытка изменения закрепа заметки с id = {}", id);
        try {
            var updated = noteService.toggleNotePinned(id);
            return ResponseEntity.status(200)
                    .body(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400)
                    .build(); // 400 или HttpStatus.BAD_REQUEST - ошибка в запросе (null или пустая строка)
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404)
                    .build(); // 404 или HttpStatus.NOT_FOUND - не найдено
        }
    }

    @PatchMapping({"/{id}/priority"})
    public ResponseEntity<Note> updateNotePriority(
            @PathVariable("id") Long id,
            @RequestBody UpdateNotePriorityRequestDto priorityRequestDto
            ) {
        logger.info("Попытка изменения приоритета заметки с id = {}", id);
        try {
            var updated = noteService.updateNotePriority(id, priorityRequestDto.priorityEnumToUpdate());
            return ResponseEntity.status(200)
                    .body(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400)
                    .build(); // 400 или HttpStatus.BAD_REQUEST - ошибка в запросе (null или пустая строка)
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404)
                    .build(); // 404 или HttpStatus.NOT_FOUND - не найдено
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id) {
        logger.info("Попытка удаления заметки с id = {}", id);
        try {
            noteService.deleteNote(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404)
                    .build();
        }
    }
}
