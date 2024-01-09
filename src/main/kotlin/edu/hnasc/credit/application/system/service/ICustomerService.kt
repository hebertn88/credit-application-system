package edu.hnasc.credit.application.system.service

import edu.hnasc.credit.application.system.repository.CustomerRepository

interface ICustomerService {
    fun save(customer: CustomerRepository): CustomerRepository
    fun findById(id: Long): CustomerRepository
    fun delete(id: Long)
}