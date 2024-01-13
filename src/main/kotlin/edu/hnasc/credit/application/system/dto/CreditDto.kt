package edu.hnasc.credit.application.system.dto

import edu.hnasc.credit.application.system.model.Credit
import edu.hnasc.credit.application.system.model.Customer
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "Invalid input")
    @field:DecimalMin(value = "0.01", message = "Invalid value")
    val creditValue: BigDecimal,
    @field:NotNull(message = "Invalid input")
    @field:Future(message = "Invalid input")
    val dayFirstInstallment: LocalDate,
    @field:NotNull(message = "Invalid input")
    @field:DecimalMin(value = "0.01", message = "Invalid value")
    val numberOfInstallment: Int,
    @field:NotNull(message = "Invalid input")
    val customerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallment = this.numberOfInstallment,
        customer = Customer(id = this.customerId)
    )
}
