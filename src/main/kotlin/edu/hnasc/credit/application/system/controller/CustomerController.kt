package edu.hnasc.credit.application.system.controller

import edu.hnasc.credit.application.system.dto.CustomerDto
import edu.hnasc.credit.application.system.dto.CustomerUpdateDto
import edu.hnasc.credit.application.system.dto.CustomerView
import edu.hnasc.credit.application.system.service.impl.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerController(
    private val customerService: CustomerService
) {
    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDto: CustomerDto): ResponseEntity<String> {
        val savedCustomer = customerService.save(customerDto.toEntity())

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("Customer ${savedCustomer.email} saved!")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer = customerService.findById(id)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) = customerService.delete(id)

    @PatchMapping
    fun updateCustomer(
        @RequestParam(value = "customerId") id: Long,
        @RequestBody @Valid customerUpdateDto: CustomerUpdateDto
    ): ResponseEntity<CustomerView> {
        val customer = customerService.findById(id)
        val customerToUpdate = customerUpdateDto.toEntity(customer)
        val customerUpdated = customerService.save(customerToUpdate)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(CustomerView(customerUpdated))
    }
}