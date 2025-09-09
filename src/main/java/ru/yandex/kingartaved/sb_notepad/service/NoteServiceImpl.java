package ru.yandex.kingartaved.sb_notepad.service;

import org.springframework.stereotype.Service;
import ru.yandex.kingartaved.sb_notepad.data.constant.NoteTypeEnum;
import ru.yandex.kingartaved.sb_notepad.data.model.ChecklistContent;
import ru.yandex.kingartaved.sb_notepad.data.model.ChecklistTask;
import ru.yandex.kingartaved.sb_notepad.data.model.Note;
import ru.yandex.kingartaved.sb_notepad.data.model.TextContent;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Override
    public Note createNote(Note note) {
        return null;
    }

    @Override
    public Note getNoteById(Long id) {
        return Note.builder()
                .id(1L)
                .title("Note title")
                .createdAt(LocalDateTime.of(2023, 5, 24, 0, 0))
                .pinned(false)
                .type(NoteTypeEnum.TEXT_NOTE)
                .content(new TextContent("TextNote new content"))
                .build();
    }

    public List<Note> getAllNotes() {
        return List.of(
                Note.builder()
                        .id(1L)
                        .title("Text note title")
                        .createdAt(LocalDateTime.of(2023, 5, 24, 0, 0))
                        .pinned(false)
                        .type(NoteTypeEnum.TEXT_NOTE)
                        .content(new TextContent("TextNote new content"))
                        .build(),
                Note.builder()
                        .id(2L)
                        .title("Checklist title")
                        .createdAt(LocalDateTime.of(2023, 5, 24, 0, 0))
                        .pinned(false)
                        .type(NoteTypeEnum.CHECKLIST)
                        .content(new ChecklistContent(
                                List.of(new ChecklistTask("Task1", false),
                                        new ChecklistTask("Task2", false))))
                        .build()
        );
    }
}
