package com.example.invoiceProyect.service

import com.example.invoiceProyect.model.Client
import com.example.invoiceProyect.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ClientService {
    @Autowired
    lateinit var clientRepository: ClientRepository

    fun list(): List<Client> {
        return clientRepository.findAll()
    }

    fun save(client: Client): Client {

        return clientRepository.save(client)
    }

    fun update(client: Client): Client {
        try {
            clientRepository.findById(client.id)?: throw Exception("Cliente no Encontrado")
            return clientRepository.save(client)
        }catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(client: Client): Client {
        try {

            var response = clientRepository.findById(client.id) ?: throw Exception("Ya existe este ID")
            response.apply {
                fullName=client.fullName

            }
            return clientRepository.save(response)
        }
        catch(ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun  delete(id: Long) {
        try {

            var response = clientRepository.findById(id).orElseThrow{throw ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no Existe con el Id:  $id")}
            clientRepository.delete(response)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el cliente", ex)
        }
    }

    fun validarNui(nui:String?): Boolean? {
        if (nui.isNullOrBlank()) {
            return false
        }

        // Ejemplo de una regla básica de validación: longitud y solo números
        val nuiRegex = Regex("^[0-9]{10}$")
        if (!nuiRegex.matches(nui)) {
            return false
        }

        // Aquí puedes agregar otras validaciones según tus necesidades
        // Por ejemplo, validar un dígito verificador

        return true
    }



}