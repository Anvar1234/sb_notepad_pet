package ru.yandex.kingartaved.sb_notepad.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ru.yandex.kingartaved.sb_notepad.data.constant.NoteTypeEnum;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "supportedType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextContent.class, name = "TEXT_NOTE"),
        @JsonSubTypes.Type(value = ChecklistContent.class, name = "CHECKLIST")
})
public sealed abstract class Content permits TextContent, ChecklistContent {

    @JsonIgnore
    public abstract NoteTypeEnum getSupportedType();
}