package com.example.invoiceProyect.model

import jakarta.persistence.*

@Entity
@Table(name = "detail")
class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null
    var quantity: Int = 0
    var price: Double? = null
    @Column(name = "subtotal",  nullable = false, insertable = false)
    var subTotal: Double? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    var invoice: Invoice? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    var product:Product? = null

}