package com.lehoaikhiem.dto;

import com.lehoaikhiem.entity.NewsCategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsCategoryMapper {
    NewsCategoryDTO toNewsCategoryDTO(NewsCategory newsCategory);
    NewsCategory toNewsCategory(NewsCategoryDTO newsCategoryDTO);
    List<NewsCategoryDTO> toDtoList(List<NewsCategory> entities);
}
