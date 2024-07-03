package com.example.invoiceProyect.model

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "invoice")
class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null
    var code: String? = null
    @Column(name = "create_at")
    var createAt: Date? = null
    var total: Double? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    var client:Client? = null

}