package com.lehoaikhiem.dto.News;

import com.lehoaikhiem.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    NewsDTO toDto(News news);
    News toEntity(NewsDTO newsDTO);
    List<NewsDTO> toDtoList(List<News> entities);
}
