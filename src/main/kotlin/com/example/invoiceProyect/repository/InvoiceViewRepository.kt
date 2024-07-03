package com.example.invoiceProyect.repository

import com.example.invoiceProyect.model.InvoiceView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface   InvoiceViewRepository: JpaRepository<InvoiceView, Long> {
    fun findById (id: Long?): InvoiceView?
}