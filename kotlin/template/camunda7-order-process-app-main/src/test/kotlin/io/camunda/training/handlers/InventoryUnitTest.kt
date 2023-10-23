package io.camunda.training.handlers

import org.assertj.core.api.Assertions.*
import org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*

import org.camunda.bpm.engine.test.Deployment
import org.camunda.bpm.extension.process_test_coverage.junit5.ProcessEngineCoverageExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(ProcessEngineCoverageExtension::class)
@Deployment(resources = ["inventory-process.bpmn"])
class InventoryUnitTest {

  val INVENTORY_PROCESS_ID = "InventroyProcess"

  val CHECK_AVAILABILITY_TASK = "Check availability"
  val RESERVE_AVAILABLE_ITEMS_TASK = "Reserve available items"
  val REMOVE_UNAVAILABLE_ITEMS_TASK = "Remove unavailable items"
  val INVENTORY_COMPLETED_EVENT = "Inventory completed"

  val AVAILABILITY_CHECK_TOPIC = "availabilityChek"
  val ITEM_REMOVAL_TOPIC = "itemRemoval"
  val ITEM_RESERVATION_TOPIC = "itemReservation"

  val ORDER_ITEMS_NUM_VAR = "orderItemsNum"
  val AVAILABLE_ITEMS_NUM_VAR = "availableItemsNu"

  @Test
  fun testHappyPath() {
    // Add testing logic
  }
}
