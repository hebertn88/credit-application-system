package edu.hnasc.credit.application.system.service.impl

import edu.hnasc.credit.application.system.exception.BusinessException
import edu.hnasc.credit.application.system.model.Customer
import edu.hnasc.credit.application.system.repository.CustomerRepository
import edu.hnasc.credit.application.system.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer = customerRepository.save(customer)
    override fun findById(id: Long): Customer = customerRepository.findById(id).orElseThrow {
        throw BusinessException("Customer Id $id not found") }

    override fun delete(id: Long): Unit {
        val customer = this.findById(id)
        customerRepository.delete(customer)
    }
}