package com.lehoaikhiem.controller.admin;

import com.lehoaikhiem.dto.Category.CategoryDTO;
import com.lehoaikhiem.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // GET /admin/categories - Get All
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> list = categoryService.findAll();
        return ResponseEntity.ok(list);
    }

    // GET /admin/categories/{id} - findById
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        CategoryDTO dto = categoryService.findById(id);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
        return ResponseEntity.ok(dto);
    }

    // POST /admin/categories - Create Category
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO dto) {
        CategoryDTO saved = categoryService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT /admin/categories/{id} - Update Category
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO dto) {
        CategoryDTO updated = categoryService.update(id, dto);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
        return ResponseEntity.ok(updated);
    }

    // DELETE /admin/categories/{id} - Delete Category
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        CategoryDTO existing = categoryService.findById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // GET /admin/categories/search?name=... - Search by Name
    @GetMapping("/search")
    public ResponseEntity<List<CategoryDTO>> searchByName(@RequestParam("name") String name) {
        List<CategoryDTO> results = categoryService.findByName(name);
        return ResponseEntity.ok(results);
    }
}