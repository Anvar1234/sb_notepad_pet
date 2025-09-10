package ru.yandex.kingartaved.sb_notepad.data.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ru.yandex.kingartaved.sb_notepad.data.constant.NoteTypeEnum;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "contentType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextContent.class, name = "text"),
        @JsonSubTypes.Type(value = ChecklistContent.class, name = "checklist")
})
public sealed abstract class Content permits TextContent, ChecklistContent { //- Sealed класс определяет ограниченную иерархию

    public abstract NoteTypeEnum getSupportedType();
}