package io.camunda.training.services

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class CustomerService {
    private val log = LoggerFactory.getLogger(CustomerService::class.java)

    /**
     * The customer credit are the last digits of the customer id
     */
    private val pattern = Pattern.compile("(.*?)(\\d*)")

    /**
     * Deduct the credit for the given customer and the given amount
     *
     * @param customerId
     * @param amount
     * @return the open order amount
     */
    fun deductCredit(customerId: String?, amount: Double): Double? {
        val credit = getCustomerCredit(customerId)
        val openAmount: Double
        val deductedCredit: Double
        if (credit > amount) {
            deductedCredit = amount
            openAmount = 0.0
        } else {
            openAmount = amount - credit
            deductedCredit = credit
        }
        log.info("charged {} from the credit, open amount is {}", deductedCredit, openAmount)
        return openAmount
    }

    /**
     * Get the current customer credit
     *
     * @param customerId
     * @return the current credit of the given customer
     */
    fun getCustomerCredit(customerId: String?): Double {
        var credit = 0.0
        val matcher = pattern.matcher(customerId)
        if (matcher.matches() && matcher.group(2) != null && matcher.group(2).length > 0) {
            credit = java.lang.Double.valueOf(matcher.group(2))
        }
        log.info("customer {} has credit of {}", customerId, credit)
        return credit
    }
}