package com.example.invoiceProyect.repository

import com.example.invoiceProyect.model.Detail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DetailRepository: JpaRepository<Detail, Long> {
    fun findById (id: Long?): Detail?
}