package com.example.invoiceProyect.model

import jakarta.persistence.*

@Entity
@Table(name = "product")
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null
    var description: String? = null
    var brand: String? = null
    var price: Double? = null
    var stock: Long? = null
}