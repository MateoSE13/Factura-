package com.example.invoiceProyect.controller

import com.example.invoiceProyect.model.Client
import com.example.invoiceProyect.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/client")
class ClientController {
    @Autowired
    lateinit var clientService: ClientService



    @GetMapping
    fun list(): List<Client> {
    return clientService.list()
    }



    @PostMapping
    fun save(@RequestBody client: Client): Client {
        return clientService.save(client)
    }

    @PutMapping
    fun update(@RequestBody client: Client):ResponseEntity< Client> {
        return ResponseEntity( clientService.update(client), HttpStatus.OK)
    }

    @PatchMapping
    fun updateName(@RequestBody client: Client):ResponseEntity< Client> {
        return ResponseEntity(clientService.updateName(client), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long):ResponseEntity< String> {
        clientService.delete(id)
        return ResponseEntity( "Cliente Eliminado",HttpStatus.OK)
    }
}