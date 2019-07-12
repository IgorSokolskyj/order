package com.riversoft.order.controller

import com.riversoft.order.model.OrderModel
import com.riversoft.order.service.OrderService
import groovy.util.logging.Slf4j
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Slf4j
@RequestMapping("/api/order")
@RestController
@Api

class OrderController {

    @Autowired

    private OrderService orderService

    @ApiOperation(value = "Add order")
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)

    List<OrderModel> addOrder(@RequestBody @Valid OrderModel order) {

        log.info("received order : id - ${order.orderId}, name - ${order.orderName}, price - ${order.orderPrice}")

        return orderService.addOrder(order)

    }

    @ApiOperation(value = "Get order by Id")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)

    List<OrderModel> getOrders(@RequestParam(required = false) String orderId) {

        if (orderId) {

            log.info("give to product order by id : ${orderId}")

            return orderService.searchOrderById(orderId)

        } else {

            log.info("give product all orders")

            return orderService.getAllOrders()

        }

    }

    @ApiOperation(value = "Delete order by Id")
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)

    OrderModel deleteOrder(@RequestParam String orderId) {

        log.info("received order id to delete this order")

        orderService.deleteOrder(orderId)

    }

    @ApiOperation(value = "Update order by Id")
    @PutMapping("/{orderId}")
    @ResponseStatus(HttpStatus.ACCEPTED)

    OrderModel updateUser(@RequestBody OrderModel orderModel, @PathVariable String orderId) {

        log.info("received update order by id : ${orderId}")

        return orderService.updateOrderPrice(orderId, orderModel.orderPrice)
    }

}
