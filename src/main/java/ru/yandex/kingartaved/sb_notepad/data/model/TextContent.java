package ru.yandex.kingartaved.sb_notepad.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.kingartaved.sb_notepad.data.constant.NoteTypeEnum;

//@Setter //todo: remove после того как будут ResponseDto
@Getter
@ToString
public final class TextContent extends Content {
    private final String text;

    public TextContent(String text) {
        this.text = text;
    }

    @Override
    public NoteTypeEnum getSupportedType() {
        return NoteTypeEnum.TEXT_NOTE;
    }
}
