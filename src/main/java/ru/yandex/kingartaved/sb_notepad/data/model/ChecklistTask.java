package ru.yandex.kingartaved.sb_notepad.data.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
//@Setter //todo: remove когда не будет использоваться (при переходе на ResponseDto)
public final class ChecklistTask {
    private final String description;
    private final boolean completed;

    public ChecklistTask(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ChecklistTask) obj;
        return Objects.equals(this.description, that.description) &&
                this.completed == that.completed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, completed);
    }

    @Override
    public String toString() {
        return "ChecklistTask[" +
                "text=" + description + ", " +
                "isDone=" + completed + ']';
    }

}