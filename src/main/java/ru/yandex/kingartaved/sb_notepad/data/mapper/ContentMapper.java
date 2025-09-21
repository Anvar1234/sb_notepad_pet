package ru.yandex.kingartaved.sb_notepad.data.mapper;

import org.mapstruct.Mapper;
import ru.yandex.kingartaved.sb_notepad.data.model.ChecklistContent;
import ru.yandex.kingartaved.sb_notepad.data.model.Content;
import ru.yandex.kingartaved.sb_notepad.data.model.TextContent;
import ru.yandex.kingartaved.sb_notepad.dto.ChecklistContentDto;
import ru.yandex.kingartaved.sb_notepad.dto.ContentDto;
import ru.yandex.kingartaved.sb_notepad.dto.TextContentDto;

@Mapper(componentModel = "spring")
public interface ContentMapper {

    default Content toEntity(ContentDto dto) {
        if (dto instanceof TextContentDto textDto) {
            return toEntity(textDto);
        } else if (dto instanceof ChecklistContentDto checklistDto) {
            return toEntity(checklistDto);
        }
        throw new IllegalArgumentException("Unsupported ContentDto type: " + dto.getClass());
    }

    // Явные методы для подтипов
    TextContent toEntity(TextContentDto dto);

    ChecklistContent toEntity(ChecklistContentDto dto);
}