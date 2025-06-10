package com.lehoaikhiem.controller.admin;

import com.lehoaikhiem.dto.Category.CategoryDTO;
import com.lehoaikhiem.response.ResponseFailure;
import com.lehoaikhiem.response.ResponseSuccess;
import com.lehoaikhiem.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
@Validated
public class AdminCategoryController {

    private final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseSuccess getAllCategories() {
        try {
            List<CategoryDTO> listCategory = categoryService.findAll();
            return new ResponseSuccess(HttpStatus.OK, "Category list retrieved successfully", listCategory);
        } catch (Exception e) {
            return new ResponseFailure(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseSuccess getCategoryById(@PathVariable Long id) {
        try {
            CategoryDTO dto = categoryService.findById(id);
            if (dto == null) {
                return new ResponseFailure(HttpStatus.NOT_FOUND, "Category not found");
            }
            return new ResponseSuccess(HttpStatus.OK, "Category retrieved successfully", dto);
        } catch (Exception e) {
            return new ResponseFailure(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    public ResponseSuccess createCategory(@Valid @RequestBody CategoryDTO category) {
        try {
            CategoryDTO saved = categoryService.save(category);
            return new ResponseSuccess(HttpStatus.CREATED, "Category created successfully", saved);
        } catch (Exception e) {
            return new ResponseFailure(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseSuccess updateCategory(@PathVariable @Min(1) Long id, @Valid @RequestBody CategoryDTO category) {
        try {
            CategoryDTO updated = categoryService.update(id, category);
            if (updated == null) {
                return new ResponseFailure(HttpStatus.NOT_FOUND, "Category not found");
            }
            return new ResponseSuccess(HttpStatus.OK, "Category updated successfully", updated);
        } catch (Exception e) {
            return new ResponseFailure(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseSuccess deleteCategory(@PathVariable Long id) {
        try {
            CategoryDTO existing = categoryService.findById(id);
            if (existing == null) {
                return new ResponseFailure(HttpStatus.NOT_FOUND, "Category not found");
            }
            categoryService.deleteById(id);
            // DELETE thì trả 204 No Content vẫn
            return new ResponseSuccess(HttpStatus.NO_CONTENT, "Category deleted successfully");
        } catch (Exception e) {
            return new ResponseFailure(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseSuccess searchByName(@RequestParam("name") String name) {
        try {
            List<CategoryDTO> results = categoryService.findByName(name);
            return new ResponseSuccess(HttpStatus.OK, "Search results retrieved successfully", results);
        } catch (Exception e) {
            return new ResponseFailure(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}