package ru.yandex.kingartaved.sb_notepad.service;

import org.springframework.stereotype.Service;
import ru.yandex.kingartaved.sb_notepad.data.constant.NoteTypeEnum;
import ru.yandex.kingartaved.sb_notepad.data.model.ChecklistContent;
import ru.yandex.kingartaved.sb_notepad.data.model.ChecklistTask;
import ru.yandex.kingartaved.sb_notepad.data.model.Note;
import ru.yandex.kingartaved.sb_notepad.data.model.TextContent;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteServiceImpl implements NoteService {

    private final Map<Long, Note> noteMap = Map.of(
            1L, Note.builder()
                    .id(1L)
                    .title("Note1 title")
                    .createdAt(LocalDateTime.of(2023, 5, 24, 0, 0))
                    .pinned(false)
                    .type(NoteTypeEnum.TEXT_NOTE)
                    .content(new TextContent("Text1 note body"))
                    .build(),
            2L, Note.builder()
                    .id(2L)
                    .title("Checklist2 title")
                    .createdAt(LocalDateTime.of(2023, 5, 24, 0, 0))
                    .pinned(false)
                    .type(NoteTypeEnum.CHECKLIST)
                    .content(new ChecklistContent(
                            List.of(new ChecklistTask("Task1", false),
                                    new ChecklistTask("Task2", false))))
                    .build(),
            3L, Note.builder()
                    .id(3L)
                    .title("Название3")
                    .createdAt(LocalDateTime.of(2023, 5, 24, 0, 0))
                    .pinned(false)
                    .type(NoteTypeEnum.TEXT_NOTE)
                    .content(new TextContent("TextNote3 new content"))
                    .build()
    );

    @Override
    public Note createNote(Note note) {
        return null;
    }

    @Override
    public Note getNoteById(Long id) {
        return noteMap.get(id);
    }

    public List<Note> getAllNotes() {
        return noteMap.values().stream().toList();
    }
}
