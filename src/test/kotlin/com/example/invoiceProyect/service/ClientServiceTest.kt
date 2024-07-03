package com.example.invoiceProyect.service

import com.example.invoiceProyect.model.Client
import com.example.invoiceProyect.repository.ClientRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class ClientServiceTest {

    @InjectMocks
    lateinit var clientService: ClientService // clase que se va a probar

    @Mock   // objeto simulado
    lateinit var clientRepository: ClientRepository

    val jsonString = File("./src/test/resources/client/invoice.json").readText(Charsets.UTF_8)

    val clientMock = Gson().fromJson(jsonString, Client::class.java)

    @Test
    fun saveClientCorrect() {
        Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
        val response = clientService.save(clientMock)
        Assertions.assertEquals(response.id, clientMock.id)
    }
/*
    @Test
    fun saveClientWhenFullNameIsBlank() {
        assertThrows(Exception::class.java) {
            clientMock.apply { fullName = " " }
            Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
            clientService.save(clientMock)
        }
    }

 */
    @Test
    fun saveWhenNuiClientIsCorrect(){
        Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
        val response = clientService.validarNui(clientMock.nui)
        Assertions.assertEquals(true, response)
    }
    @Test
    fun saveWhenNuiClientIsIncorrect(){
        clientMock.nui="xbhytragio"
        Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
        val response = clientService.validarNui(clientMock.nui)
        Assertions.assertEquals(false, response)
    }

    @Test
    fun saveWhenNuiClientIsBlanco(){
        clientMock.nui=""
        Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
        val response = clientService.validarNui(clientMock.nui)
        Assertions.assertEquals(false, response)
    }

    @Test
    fun saveWhenNuiClientIsIncoplete(){
        clientMock.nui="05462897"
        Mockito.`when`(clientRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMock)
        val response = clientService.validarNui(clientMock.nui)
        Assertions.assertEquals(false, response)
    }
}
