package com.example.invoiceProyect.service

import com.example.invoiceProyect.model.Detail
import com.example.invoiceProyect.repository.DetailRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.web.server.ResponseStatusException

@SpringBootTest
class DetailServiceTest  {

    @Autowired
    lateinit var detailService: DetailService

    @MockBean
    lateinit var detailRepository: DetailRepository

    @Test
    fun testSaveDetailWithValidQuantity() {
        val detail = Detail(id = 1L, quantity = 5)
        Mockito.`when`(detailRepository.save(detail)).thenReturn(detail)

        val result = detailService.save(detail)

        assertEquals(detail, result)
    }

    @Test
    fun testSaveDetailWithInvalidQuantity() {
        val detail = Detail(id = 1L, quantity = -5)

        val exception = assertThrows(ResponseStatusException::class.java) {
            detailService.save(detail)
        }

        assertEquals("La cantidad debe ser un número entero positivo", exception.reason)
    }

    @Test
    fun testUpdateDetailWithValidQuantity() {
        val detail = Detail(id = 1L, quantity = 5)
        Mockito.`when`(detailRepository.findById(detail.id)).thenReturn(java.util.Optional.of(detail))
        Mockito.`when`(detailRepository.save(detail)).thenReturn(detail)

        val result = detailService.update(detail)

        assertEquals(detail, result)
    }

    @Test
    fun testUpdateDetailWithInvalidQuantity() {
        val detail = Detail(id = 1L, quantity = -5)
        Mockito.`when`(detailRepository.findById(detail.id)).thenReturn(java.util.Optional.of(detail))

        val exception = assertThrows(ResponseStatusException::class.java) {
            detailService.update(detail)
        }

        assertEquals("La cantidad debe ser un número entero positivo", exception.reason)
    }
}