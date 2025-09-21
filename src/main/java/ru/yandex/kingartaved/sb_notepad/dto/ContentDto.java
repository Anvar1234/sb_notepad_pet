package ru.yandex.kingartaved.sb_notepad.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ru.yandex.kingartaved.sb_notepad.data.constant.NoteTypeEnum;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "supportedType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextContentDto.class, name = "TEXT_NOTE"),
        @JsonSubTypes.Type(value = ChecklistContentDto.class, name = "CHECKLIST")
})
public sealed abstract class ContentDto permits TextContentDto, ChecklistContentDto {

    @JsonIgnore
    public abstract NoteTypeEnum getSupportedType();
}
