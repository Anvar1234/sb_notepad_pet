package ru.yandex.kingartaved.sb_notepad.data.model;

import ru.yandex.kingartaved.sb_notepad.data.constant.NoteTypeEnum;

public sealed abstract class Content permits TextContent, ChecklistContent { //- Sealed класс определяет ограниченную иерархию

    public abstract NoteTypeEnum getSupportedType();
}