package ru.yandex.kingartaved.sb_notepad.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ChecklistTaskDto {

    private final String description;
    private final boolean completed;

    @JsonCreator
    public ChecklistTaskDto(
            @JsonProperty("description") String description,
            @JsonProperty("completed") boolean completed
    ) {
        this.description = description;
        this.completed = completed;
    }
}