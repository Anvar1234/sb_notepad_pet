package ru.yandex.kingartaved.sb_notepad.dto.request;

import ru.yandex.kingartaved.sb_notepad.data.constant.NotePriorityEnum;
import ru.yandex.kingartaved.sb_notepad.dto.ContentDto;

public record UpdateNoteRequestDto(
        String title,
        boolean pinned,
        NotePriorityEnum priority,
        ContentDto contentDto
) {
}