package ru.yandex.kingartaved.sb_notepad.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.yandex.kingartaved.sb_notepad.data.constant.NoteTypeEnum;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "supportedType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextContentDto.class, name = "TEXT_NOTE")
})
@NoArgsConstructor
@Getter
public final class TextContentDto extends ContentDto {

    private String text;

    @JsonCreator
    public TextContentDto(@JsonProperty("text") String text) {
        this.text = text;
    }

    @Override
    public NoteTypeEnum getSupportedType() {
        return NoteTypeEnum.TEXT_NOTE;
    }
}