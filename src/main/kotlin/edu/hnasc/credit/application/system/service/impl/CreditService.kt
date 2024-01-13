package edu.hnasc.credit.application.system.service.impl

import edu.hnasc.credit.application.system.exception.BusinessException
import edu.hnasc.credit.application.system.model.Credit
import edu.hnasc.credit.application.system.repository.CreditRepository
import edu.hnasc.credit.application.system.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): ICreditService {
    override fun save(credit: Credit): Credit {
        val customerFound = customerService.findById((credit.customer?.id!!))
        credit.apply {
            customer = customerFound
        }
        return creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> = creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit = creditRepository.findByCreditCode(creditCode)?: throw BusinessException("CreditCode $creditCode not found!")
        return if (credit.customer?.id == customerId) credit else throw IllegalArgumentException("Contact Admin")
    }
}