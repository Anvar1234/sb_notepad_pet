package ru.yandex.kingartaved.sb_notepad.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.yandex.kingartaved.sb_notepad.data.constant.NotePriorityEnum;

public record UpdateNotePriorityRequestDto(
        @JsonProperty("priority") // Указываем что в JSON поле называется "priority"
        NotePriorityEnum priorityEnumToUpdate
) {
}
