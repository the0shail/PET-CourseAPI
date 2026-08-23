package com.the0shail.course_api.mapper;

import com.the0shail.course_api.dto.request.module.CreateModuleRequest;
import com.the0shail.course_api.dto.request.module.UpdateModuleRequest;
import com.the0shail.course_api.dto.response.module.ModuleSummaryDto;
import com.the0shail.course_api.entity.Module;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ModuleMapper {
    ModuleSummaryDto toDto(Module module);

    @Mapping(target = "id",      ignore = true)
    @Mapping(target = "course",  ignore = true)   // ставит сервис
    @Mapping(target = "lessons", ignore = true)
    Module toEntity(CreateModuleRequest request);

    @Mapping(target = "id",      ignore = true)
    @Mapping(target = "course",  ignore = true)   // ставит сервис
    @Mapping(target = "lessons", ignore = true)
    void updateModule(UpdateModuleRequest request, @MappingTarget Module module);
}
