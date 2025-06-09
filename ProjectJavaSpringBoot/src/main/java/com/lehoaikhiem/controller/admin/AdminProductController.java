package com.lehoaikhiem.controller.admin;

import com.lehoaikhiem.dto.Product.ProductDTO;
import com.lehoaikhiem.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get /admin/products - Get All
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> list = productService.findAllWithoutPaging();
        return ResponseEntity.ok(list);
    }

    // Get /admin/products/{id} - Find by Id
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        ProductDTO dto = productService.findById(id);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.ok(dto);
    }

    // POST /admin/products - Create Products
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO dto) {
        ProductDTO createdProduct = productService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // PUT /admin/products/{id} - Update Products
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto) {
        ProductDTO updatedProduct = productService.update(id, dto);
        if (updatedProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE /admin/products/{id} - Delete Category
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        ProductDTO deletedProduct = productService.findById(id);
        if (deletedProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        productService.deleteById(id);
        return ResponseEntity.ok(deletedProduct);
    }



    // GET /admin/products/search?name=... - Search by Name
    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam("name") String name) {
        List<ProductDTO> results = productService.findByName(name);
        return ResponseEntity.ok(results);
    }

}
