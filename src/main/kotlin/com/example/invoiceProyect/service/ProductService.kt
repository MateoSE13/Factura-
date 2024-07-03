package com.example.invoiceProyect.service

import com.example.invoiceProyect.model.Product
import com.example.invoiceProyect.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository

    fun list(): List<Product> {
        return productRepository.findAll()
    }

    fun save(product: Product): Product {
        return productRepository.save(product)
    }

    fun update(product: Product): Product {
        try {
            productRepository.findById(product.id)?: throw Exception("Producto no Encontrado")
            return productRepository.save(product)
        }catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateDescription(product: Product): Product {
        try {

            var response = productRepository.findById(product.id) ?: throw Exception("Ya existe este ID")
            response.apply {
                description=product.description

            }
            return productRepository.save(response)
        }
        catch(ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun  delete(id: Long) {
        try {

            var response = productRepository.findById(id).orElseThrow{throw ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no Existe con el Id:  $id")}
            productRepository.delete(response)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el producto", ex)
        }
    }

}