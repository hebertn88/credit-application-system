package edu.hnasc.credit.application.system.controller

import edu.hnasc.credit.application.system.dto.CreditDto
import edu.hnasc.credit.application.system.dto.CreditView
import edu.hnasc.credit.application.system.dto.CreditViewList
import edu.hnasc.credit.application.system.model.Credit
import edu.hnasc.credit.application.system.service.impl.CreditService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/credits")
class CreditController(
    private val creditService: CreditService
) {
    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String> {
        val credit = creditService.save(creditDto.toEntity())

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved!")
    }

    @GetMapping
    fun findAllByCustomerId(
        @RequestParam(value = "customerId") customerId: Long
    ): ResponseEntity<List<CreditViewList>> {
        val creditViewList = creditService.findAllByCustomer(customerId)
            .stream()
            .map { credit: Credit -> CreditViewList(credit) }
            .toList()

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(creditViewList)
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(
        @RequestParam(value = "customerId") customerId: Long,
        @PathVariable creditCode: UUID
    ): ResponseEntity<CreditView> {
        val credit = creditService.findByCreditCode(customerId, creditCode)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(CreditView(credit))
    }
}