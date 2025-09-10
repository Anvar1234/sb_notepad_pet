package ru.yandex.kingartaved.sb_notepad.data.model;

import lombok.*;
import ru.yandex.kingartaved.sb_notepad.data.constant.NotePriorityEnum;
import ru.yandex.kingartaved.sb_notepad.data.constant.NoteTypeEnum;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Note {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean pinned;
    private NoteTypeEnum type;
    private NotePriorityEnum priority;
    private Content content;
}
