package edu.hnasc.credit.application.system.controller

import edu.hnasc.credit.application.system.dto.CreditDto
import edu.hnasc.credit.application.system.dto.CreditView
import edu.hnasc.credit.application.system.dto.CreditViewList
import edu.hnasc.credit.application.system.model.Credit
import edu.hnasc.credit.application.system.service.impl.CreditService
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/credits")
class CreditController(
    private val creditService: CreditService
) {
    @PostMapping
    fun saveCredit(@RequestBody creditDto: CreditDto): String {
        val credit = creditService.save(creditDto.toEntity())
        return "Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved!"
    }

    @GetMapping
    fun findAllByCustomerId(
        @RequestParam(value = "customerId") customerId: Long
    ): List<CreditViewList> =
        creditService.findAllByCustomer(customerId)
            .stream()
            .map { credit: Credit -> CreditViewList(credit) }
            .toList()

    @GetMapping
    fun findByCreditCode(@RequestParam(value = "customerId") customerId: Long,
                         @PathVariable creditCode: UUID
    ): CreditView {
        val credit = creditService.findByCreditCode(customerId, creditCode)
        return CreditView(credit)
    }
}