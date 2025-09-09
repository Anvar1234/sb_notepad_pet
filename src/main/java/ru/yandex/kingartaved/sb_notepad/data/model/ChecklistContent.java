package ru.yandex.kingartaved.sb_notepad.data.model;

import lombok.Getter;
import lombok.ToString;
import ru.yandex.kingartaved.sb_notepad.data.constant.NoteTypeEnum;

import java.util.List;

@Getter
@ToString
public final class ChecklistContent extends Content {
    private final List<ChecklistTask> tasks;

    public ChecklistContent(List<ChecklistTask> tasks) {
        this.tasks = tasks;
    }

    @Override
    public NoteTypeEnum getSupportedType() {
        return NoteTypeEnum.CHECKLIST;
    }
}
