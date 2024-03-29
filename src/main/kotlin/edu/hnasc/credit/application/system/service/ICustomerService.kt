package edu.hnasc.credit.application.system.service

import edu.hnasc.credit.application.system.model.Customer

interface ICustomerService {
    fun save(customer: Customer): Customer
    fun findById(id: Long): Customer
    fun delete(id: Long): Unit
}