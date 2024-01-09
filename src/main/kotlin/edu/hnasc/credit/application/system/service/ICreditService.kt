package edu.hnasc.credit.application.system.service

import edu.hnasc.credit.application.system.model.Credit
import java.util.UUID

interface ICreditService {
    fun save(credit: Credit): Credit
    fun findAllByCustomer(customerId: Long): List<Credit>
    fun findByCreditCode(creditCode: UUID): Credit
}