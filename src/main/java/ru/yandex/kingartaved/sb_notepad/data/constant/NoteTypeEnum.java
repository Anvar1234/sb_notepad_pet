package ru.yandex.kingartaved.sb_notepad.data.constant;

import lombok.Getter;

@Getter
public enum NoteTypeEnum {

    TEXT_NOTE("Текстовая заметка"),
    CHECKLIST("Чек-лист");

    // Временно для тестов
//    TEST_UNSUPPORTED_TYPE("Неподдерживаемый тип для тестов");

    private final String description;

    NoteTypeEnum(String description) {
        this.description = description;
    }
}
