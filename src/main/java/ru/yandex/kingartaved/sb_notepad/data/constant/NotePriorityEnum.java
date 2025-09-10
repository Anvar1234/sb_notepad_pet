package ru.yandex.kingartaved.sb_notepad.data.constant;

public enum NotePriorityEnum {

    HIGH("Высокий"),
    BASE("Базовый"),
    LOW("Низкий");

    private final String priority;

    NotePriorityEnum(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }
}
