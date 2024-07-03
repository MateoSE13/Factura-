package com.example.invoiceProyect.service

import com.example.invoiceProyect.model.Detail
import com.example.invoiceProyect.repository.DetailRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DetailService {
    @Autowired
    lateinit var detailRepository: DetailRepository

    fun list(): List<Detail> {
        return detailRepository.findAll()
    }

    fun save(detail: Detail): Detail {
        validateDetail(detail)
        return detailRepository.save(detail)
    }

    fun update(detail: Detail): Detail {
        try {
            detailRepository.findById(detail.id) ?: throw Exception("Id no Encontrado")
            validateDetail(detail)
            return detailRepository.save(detail)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateQuantity(detail: Detail): Detail {
        try {
            var response = detailRepository.findById(detail.id) ?: throw Exception("Id no Encontrado")
            response.apply {
                validateDetail(detail)
                quantity = detail.quantity
                subTotal = detail.subTotal
            }
            return detailRepository.save(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun delete(id: Long) {
        try {
            var response = detailRepository.findById(id).orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "No Existe con el Id:  $id") }
            detailRepository.delete(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar", ex)
        }
    }

    private fun validateDetail(detail: Detail) {
        if (detail.quantity <= 0) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad debe ser un número entero positivo")
        }
        if (detail.subTotal != detail.quantity * detail.price) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "El subTotal debe ser igual a quantity * price")
        }
    }
}
