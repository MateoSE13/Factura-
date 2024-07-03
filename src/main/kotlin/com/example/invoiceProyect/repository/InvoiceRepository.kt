package com.example.invoiceProyect.repository


import com.example.invoiceProyect.model.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface InvoiceRepository: JpaRepository<Invoice, Long> {
    fun findById (id: Long?): Invoice?

    @Query(nativeQuery = true)
    fun optenerTotal(value: Double): List<Invoice>
}