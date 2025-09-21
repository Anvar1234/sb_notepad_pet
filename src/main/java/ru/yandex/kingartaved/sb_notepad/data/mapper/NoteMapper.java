package ru.yandex.kingartaved.sb_notepad.data.mapper;

import org.mapstruct.*;
import ru.yandex.kingartaved.sb_notepad.data.model.Note;
import ru.yandex.kingartaved.sb_notepad.dto.request.UpdateNoteRequestDto;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - игнорирует поля в Note, которых нет в DTO
 * nullValuePropertyMappingStrategy = IGNORE - не обновляет поля, если в DTO значение null
 *
 * @MappingTarget - MapStruct обновляет существующий объект, а не создает новый
 */

@Mapper(
        uses = ContentMapper.class,
        unmappedTargetPolicy = ReportingPolicy.WARN,
        componentModel = "spring"
)
public interface NoteMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "contentDto", target = "content")
    Note updateNoteFromDto(UpdateNoteRequestDto updateNoteRequestDto, @MappingTarget Note note);
}
