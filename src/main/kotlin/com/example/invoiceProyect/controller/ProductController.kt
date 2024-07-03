package com.example.invoiceProyect.controller

import com.example.invoiceProyect.model.Product
import com.example.invoiceProyect.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
class ProductController {
    @Autowired
    lateinit var productService: ProductService
    @GetMapping
    fun list(): List<Product> {
    return productService.list()
    }

    @PostMapping
    fun save(@RequestBody product: Product):ResponseEntity<Product> {
        return ResponseEntity(productService.save(product), HttpStatus.OK)
    }

    @PutMapping
    fun update(@RequestBody product: Product):ResponseEntity< Product> {
        return ResponseEntity( productService.update(product), HttpStatus.OK)
    }

    @PatchMapping
    fun updateDescription(@RequestBody product: Product):ResponseEntity< Product> {
        return ResponseEntity(productService.updateDescription(product), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long):ResponseEntity< String> {
        productService.delete(id)
        return ResponseEntity( "Producto Eliminado",HttpStatus.OK)
    }
}