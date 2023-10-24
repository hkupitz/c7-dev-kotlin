package io.camunda.training.services

import org.slf4j.LoggerFactory

class CreditCardService {
    private val log = LoggerFactory.getLogger(CreditCardService::class.java)

    fun chargeAmount(cardNumber: String?, cvc: String?, expiryDate: String?, amount: Double?) {
        log.info(
            "charging card {} that expires on {} and has cvc {} with amount of {}",
            cardNumber, expiryDate, cvc, amount
        )
        log.info("payment completed")
    }
}