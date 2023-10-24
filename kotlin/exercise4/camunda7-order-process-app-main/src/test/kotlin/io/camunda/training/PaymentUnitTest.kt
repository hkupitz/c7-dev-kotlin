package io.camunda.training

import org.assertj.core.api.Assertions.*
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*

import org.camunda.bpm.engine.test.Deployment
import org.camunda.bpm.extension.process_test_coverage.junit5.ProcessEngineCoverageExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(ProcessEngineCoverageExtension::class)
@Deployment(resources = ["payment-process.bpmn"])
class PaymentUnitTest {

  @Test
  fun testHappyPath() {
    val processInstance = runtimeService().startProcessInstanceByKey("PaymentProcess",
      withVariables("orderTotal", 30, "customerCredit", 45.99)
    )

    assertThat(processInstance).isStarted

    assertThat(processInstance).isWaitingAt(findId("Deduct credit"))
      .externalTask()
      .hasTopicName("creditDeduction")
    complete(externalTask())

    assertThat(processInstance).isEnded
  }

  @Test
  fun testCreditCardPath() {
    val processInstance = runtimeService().createProcessInstanceByKey("PaymentProcess")
      .startAfterActivity(findId("Deduct credit"))
      .setVariables(withVariables("orderTotal", 45.99, "customerCredit", 30))
      .execute()

    assertThat(processInstance).isStarted

    assertThat(processInstance).isWaitingAt(findId("Charge credit card"))
      .externalTask()
      .hasTopicName("creditCardCharging")
    complete(externalTask())

    assertThat(processInstance).isEnded
      .hasPassed(findId("Payment completed"))
  }
}
