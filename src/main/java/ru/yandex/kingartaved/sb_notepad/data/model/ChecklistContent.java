package ru.yandex.kingartaved.sb_notepad.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.yandex.kingartaved.sb_notepad.data.constant.NoteTypeEnum;

import java.util.List;

//@Setter //todo: remove после того как будут ResponseDto
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
