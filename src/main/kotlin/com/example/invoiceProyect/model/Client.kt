package com.example.invoiceProyect.model

import jakarta.persistence.*

@Entity
@Table(name = "client")
class Client {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(updatable = false)
    var id: Long? = null
    var nui : String? = null
    @Column(name = "fullname")
    var fullName : String? = null
    var address : String? = null
    var email : String? = null
}