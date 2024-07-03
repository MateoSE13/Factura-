package com.example.invoiceProyect.controller

import com.example.invoiceProyect.model.Detail
import com.example.invoiceProyect.service.DetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/detail")
class DetailController {
    @Autowired
    lateinit var detailService: DetailService
    @GetMapping
    fun list(): List<Detail> {
    return detailService.list()
    }


    @PostMapping
    fun save(@RequestBody detail: Detail): ResponseEntity<Detail> {
        return ResponseEntity(detailService.save(detail), HttpStatus.OK)
    }

    @PutMapping
    fun update(@RequestBody detail: Detail):ResponseEntity< Detail> {
        return ResponseEntity( detailService.update(detail), HttpStatus.OK)
    }

    @PatchMapping
    fun updateQuantity(@RequestBody detail: Detail):ResponseEntity< Detail> {
        return ResponseEntity(detailService.updateQuantity(detail), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long):ResponseEntity< String> {
        detailService.delete(id)
        return ResponseEntity( "Detalle Eliminado",HttpStatus.OK)
    }
}