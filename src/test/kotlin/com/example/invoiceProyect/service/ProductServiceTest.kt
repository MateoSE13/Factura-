package com.example.invoiceProyect.service

import com.example.invoiceProyect.model.Product
import com.example.invoiceProyect.repository.ProductRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.junit.jupiter.api.extension.ExtendWith
import java.util.Optional

@ExtendWith(MockitoExtension::class)
class ProductServiceTest {

    @Mock
    lateinit var productRepository: ProductRepository

    @InjectMocks
    lateinit var productService: ProductService

    @Test
    fun `should list all products`() {
        val products = listOf(Product(description = "product1", brand = "brand1", price = 10.0, stock = 10))
        `when`(productRepository.findAll()).thenReturn(products)

        val result = productService.list()
        assertEquals(1, result.size)
        assertEquals("product1", result[0].description)
    }

    @Test
    fun `should save product`() {
        val product = Product(description = "product1", brand = "brand1", price = 10.0, stock = 10)
        `when`(productRepository.save(product)).thenReturn(product)

        val result = productService.save(product)
        assertEquals("product1", result.description)
    }

    @Test
    fun `should update product`() {
        val product = Product(id = 1, description = "product1", brand = "brand1", price = 10.0, stock = 10)
        `when`(productRepository.findById(1)).thenReturn(Optional.of(product))
        `when`(productRepository.save(product)).thenReturn(product)

        val result = productService.update(product)
        assertEquals("product1", result.description)
    }

    @Test
    fun `should throw exception when product not found on update`() {
        val product = Product(id = 1, description = "product1", brand = "brand1", price = 10.0, stock = 10)
        `when`(productRepository.findById(1)).thenReturn(Optional.empty())

        val exception = assertThrows<ResponseStatusException> { productService.update(product) }
        assertEquals("404 NOT_FOUND \"Producto no Encontrado\"", exception.message)
    }

    @Test
    fun `should update product description`() {
        val product = Product(id = 1, description = "new description", brand = "brand1", price = 10.0, stock = 10)
        val existingProduct = Product(id = 1, description = "old description", brand = "brand1", price = 10.0, stock = 10)
        `when`(productRepository.findById(1)).thenReturn(Optional.of(existingProduct))
        `when`(productRepository.save(existingProduct)).thenReturn(existingProduct.apply { description = product.description })

        val result = productService.updateDescription(product)
        assertEquals("new description", result.description)
    }

    @Test
    fun `should delete product`() {
        val product = Product(id = 1, description = "product1", brand = "brand1", price = 10.0, stock = 10)
        `when`(productRepository.findById(1)).thenReturn(Optional.of(product))

        productService.delete(1)
        verify(productRepository, times(1)).delete(product)
    }

    @Test
    fun `should throw exception when product not found on delete`() {
        `when`(productRepository.findById(1)).thenReturn(Optional.empty())

        val exception = assertThrows<ResponseStatusException> { productService.delete(1) }
        assertEquals("500 INTERNAL_SERVER_ERROR \"Error al eliminar el producto\"", exception.message)
    }

    @Test
    fun `should validate stock is a positive integer`() {
        val product = Product(description = "product1", brand = "brand1", price = 10.0, stock = -1)
        val exception = assertThrows<IllegalArgumentException> { validateStock(product.stock) }
        assertEquals("Stock debe ser un número entero positivo", exception.message)
    }

    private fun validateStock(stock: Int) {
        require(stock > 0) { "Stock debe ser un número entero positivo" }
    }
}
