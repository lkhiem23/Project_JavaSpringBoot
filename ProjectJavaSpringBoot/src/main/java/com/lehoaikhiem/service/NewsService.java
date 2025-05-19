package com.lehoaikhiem.service;

import com.lehoaikhiem.dto.News.NewsDTO;
import com.lehoaikhiem.dto.News.NewsMapper;
import com.lehoaikhiem.entity.News;
import com.lehoaikhiem.repository.NewsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

// findAll; findById; save; update; delete; findByName

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    public List<NewsDTO> findAll() {
        return newsMapper.toDtoList(newsRepository.findAll());
    }

    public NewsDTO findById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found id News"));
        return newsMapper.toDto(news);
    }

    public NewsDTO save(NewsDTO newsDTO) {
        News entity;

        if (newsDTO.getId() != null) {
            // Tìm kiếm entity trong database dựa trên ID
            entity = newsRepository.findById(newsDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("News not found"));

            // Sử dụng builder để cập nhật các trường
            entity = entity.toBuilder()
                    .name(newsDTO.getName())
                    .description(newsDTO.getDescription())
                    .image(newsDTO.getImage())
                    .contents(newsDTO.getContents())
                    .slug(newsDTO.getSlug())
                    .metaTitle(newsDTO.getMetaTitle())
                    .metaKeyword(newsDTO.getMetaKeyword())
                    .metaDescription(newsDTO.getMetaDescription())
                    .isDelete(newsDTO.getIsDelete())
                    .isActive(newsDTO.getIsActive())
                    .updatedDate(new Date()) // Cập nhật thời gian cập nhật
                    .build();
        } else {
            // Nếu là đối tượng mới, tạo từ builder và thiết lập thời gian tạo và cập nhật
            entity = News.builder()
                    .name(newsDTO.getName())
                    .description(newsDTO.getDescription())
                    .image(newsDTO.getImage())
                    .contents(newsDTO.getContents())
                    .slug(newsDTO.getSlug())
                    .metaTitle(newsDTO.getMetaTitle())
                    .metaKeyword(newsDTO.getMetaKeyword())
                    .metaDescription(newsDTO.getMetaDescription())
                    .isDelete(newsDTO.getIsDelete())
                    .isActive(newsDTO.getIsActive())
                    .createdDate(new Date())
                    .updatedDate(new Date())
                    .build();
        }

        // Lưu entity vào database và chuyển lại thành DTO để trả về
        return newsMapper.toDto(newsRepository.save(entity));
    }

    public void deleteById(Long id) {
        if (!newsRepository.existsById(id)) {
            throw new EntityNotFoundException("News not found with ID: " + id);
        }
        newsRepository.deleteById(id);
    }

    public List<NewsDTO> findByName(String name) {
        List<News> entities = newsRepository.findByNameContaining(name);
        if (entities.isEmpty()) {
            throw new EntityNotFoundException("News not found with name: " + name);
        }
        return newsMapper.toDtoList(entities);
    }
    public long countAllNews() {
        return newsRepository.count();
    }
}
