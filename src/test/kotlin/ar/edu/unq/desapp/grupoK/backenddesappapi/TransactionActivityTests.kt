package ar.edu.unq.desapp.grupoK.backenddesappapi

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.TransactionActivity
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.dto.CreateTransactionDTO
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.services.TransactionService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TransactionActivityTests {
    @MockK
    var transactionService = mockk<TransactionService>()
    @MockK
    var transactionDTO = mockk<CreateTransactionDTO>()
    @Test
    fun CreateTransaction() {
        var transaction = TransactionActivity(transactionDTO)
        every { transactionService.CreateTransaction(transactionDTO) } returns transaction.id
        assert(transactionService.GetTransactionWithId(transaction.id.toLong()) == transaction)
    }


}