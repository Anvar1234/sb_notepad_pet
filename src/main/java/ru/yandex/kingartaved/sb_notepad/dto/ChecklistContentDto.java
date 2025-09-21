package ru.yandex.kingartaved.sb_notepad.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.yandex.kingartaved.sb_notepad.data.constant.NoteTypeEnum;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "supportedType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChecklistContentDto.class, name = "CHECKLIST")
})
@NoArgsConstructor
@Getter
public final class ChecklistContentDto extends ContentDto {

    private List<ChecklistTaskDto> tasks; // ← теперь ChecklistTaskDto

    @JsonCreator
    public ChecklistContentDto(@JsonProperty("tasks") List<ChecklistTaskDto> tasks) {
        this.tasks = tasks;
    }

    @Override
    public NoteTypeEnum getSupportedType() {
        return NoteTypeEnum.CHECKLIST;
    }
}