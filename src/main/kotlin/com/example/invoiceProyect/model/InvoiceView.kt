package com.example.invoiceProyect.model

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "invoice_view")
class InvoiceView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null
    var code: String? = null
    @Column(name = "create_at")
    var createAt: Date? = null
    var total: Double? = null
    var fullname: String? = null

}