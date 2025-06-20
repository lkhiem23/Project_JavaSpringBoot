package com.lehoaikhiem.controller.api.admin;

import com.lehoaikhiem.dto.Product.ProductDTO;
import com.lehoaikhiem.response.ResponseSuccess;
import com.lehoaikhiem.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get /api/admin/products - Get all products
    @GetMapping
    public ResponseSuccess getAllProducts(){
        try {
            List<ProductDTO> listProduct = productService.findAllWithoutPaging();
            return new ResponseSuccess(HttpStatus.OK, "Product list retrieved successfully", listProduct);
        } catch (Exception e) {
            return new ResponseSuccess(HttpStatus.INTERNAL_SERVER_ERROR, "Error while retrieving products");
        }
    }

    // Get /api/admin/products/{id} - Find by Id
    @GetMapping("/{id}")
    public ResponseSuccess getProductById(@PathVariable Long id){
        try {
            ProductDTO productDTO = productService.findById(id);
            return new ResponseSuccess(HttpStatus.OK, "Product retrieved successfully", productDTO);
        } catch (Exception e) {
            return new ResponseSuccess(HttpStatus.INTERNAL_SERVER_ERROR, "Error while retrieving product");
        }

    }

    // POST /admin/products - Create Products
    @PostMapping
    public ResponseSuccess createProduct(@RequestBody ProductDTO productDTO){
        try {
            ProductDTO createdProduct = productService.save(productDTO);
            return new ResponseSuccess(HttpStatus.CREATED, "Product created successfully", createdProduct);
        } catch (Exception e) {
            return new ResponseSuccess(HttpStatus.INTERNAL_SERVER_ERROR, "Error while creating product");
        }

    }

    // PUT /admin/products/{id} - Update Products
    @PutMapping("/{id}")
    public ResponseSuccess updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        try {
            ProductDTO updatedProduct = productService.update(id, productDTO);
            return new ResponseSuccess(HttpStatus.OK, "Product updated successfully", updatedProduct);
        } catch (Exception e) {
            return new ResponseSuccess(HttpStatus.INTERNAL_SERVER_ERROR, "Error while updating product");
        }

    }

    // DELETE /admin/products/{id} - Delete Products
    @DeleteMapping("/{id}")
    public ResponseSuccess deleteProduct(@PathVariable Long id){
        try {
            ProductDTO productDTO = productService.findById(id);
            productService.deleteById(id);
            return new ResponseSuccess(HttpStatus.OK, "Product deleted successfully", productDTO);
        } catch (Exception e) {
            return new ResponseSuccess(HttpStatus.INTERNAL_SERVER_ERROR, "Error while deleting product");
        }

    }

    // GET /admin/products/search?name=... - Search by Name
    @GetMapping("/search")
    public ResponseSuccess getProductsByName(@RequestParam String name){
        try {
            List<ProductDTO> result = productService.findByName(name);
            return new ResponseSuccess(HttpStatus.OK, "Search results Product retrieved successfully", result);
        } catch (Exception e) {
            return new ResponseSuccess(HttpStatus.INTERNAL_SERVER_ERROR, "Error while retrieving products");
        }
    }

}
