package com.example.invoiceProyect.service
import com.example.invoiceProyect.model.Invoice
import com.example.invoiceProyect.repository.InvoiceRepository
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class InvoiceServiceTest {

    @InjectMocks
    lateinit var invoiceService: InvoiceService // clase que se va a probar

    @Mock
    lateinit var invoiceRepository: InvoiceRepository // objeto simulado

    private lateinit var invoiceMock: Invoice

    @Test
    fun setup() {
        MockitoAnnotations.openMocks(this)
        val jsonString = File("./src/test/resources/invoice/invoice.json").readText(Charsets.UTF_8)
        invoiceMock = Gson().fromJson(jsonString, Invoice::class.java)
    }

    @Test
    fun saveClientCorrect() {
        Mockito.`when`(invoiceRepository.save(Mockito.any(Invoice::class.java))).thenReturn(invoiceMock)
        val response = invoiceService.save(invoiceMock)
        Assertions.assertEquals(response.id, invoiceMock.id)
    }

    @Test
    fun saveWhenNullClientIsCorrect() {
        Mockito.`when`(invoiceRepository.save(Mockito.any(Invoice::class.java))).thenReturn(invoiceMock)
        val response = invoiceService.isValidInvoiceCode(invoiceMock.code ?: "defaultCode")
        Assertions.assertEquals(true, response)
    }

    @Test
    fun saveWhenNullClientIsIncorrect() {
        invoiceMock.code = "xbhytragio"
        Mockito.`when`(invoiceRepository.save(Mockito.any(Invoice::class.java))).thenReturn(invoiceMock)
        val response = invoiceService.isValidInvoiceCode(invoiceMock.code ?: "defaultCode")
        Assertions.assertEquals(false, response)
    }

    @Test
    fun saveClientWithEmptyCode() {
        invoiceMock.code = ""
        Mockito.`when`(invoiceRepository.save(Mockito.any(Invoice::class.java))).thenReturn(invoiceMock)
        val response = invoiceService.isValidInvoiceCode(invoiceMock.code ?: "defaultCode")
        Assertions.assertEquals(false, response)
    }
}
