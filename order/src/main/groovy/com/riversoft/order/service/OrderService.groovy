package com.riversoft.order.service

import groovy.util.logging.Slf4j
import com.riversoft.order.model.OrderModel
import org.springframework.stereotype.Service

@Slf4j
@Service

class OrderService {

    /**initialization List to save orders
     */

    private List<OrderModel> orders = []

    /**add order to object
     * @param order
     * @return
     */

    List<OrderModel> addOrder(OrderModel order) {

        orders.add(order)

        def allOrders = orders.findAll { it.orderName }

        log.info("save orders : id - ${order.orderId}, name - ${order.orderName}, price - ${order.orderPrice}")

        log.info("All orders : ${allOrders.orderName}")

        return allOrders

    }

    /**give all Orders
     * @return
     */

    List<OrderModel> getAllOrders() {

        def models = orders.findAll()

        if (!models) {

            log.debug("Not found any mo")

            return []
        }

        log.debug("Find ${models.size()} models")

        return models
    }

    /**give Order by Id
     * @param OrderId
     * @return
     */

    List<OrderModel> searchOrderById(String orderId) {

        def foundOrder = orders.findAll { it.orderId == orderId }

        if(!foundOrder) { throw new IllegalStateException("Sorry dude but i dont have id like that : ${orderId}") }

        return foundOrder
    }

    /**delete Order by Id
     * @param OrderId
     * @return
     */

    OrderModel deleteOrder(String orderId) {

        def orderToDelete = orders.find { it.orderId == orderId }

        if(!orderToDelete) { throw new IllegalStateException("Sorry dude but i dont have id like that : ${orderId}") }

        orders.remove(orderToDelete)

        log.info("Delete order : id - ${orderToDelete.orderId}")

        return orderToDelete

    }

    /**update Order price by Id
     *
     * @param OrderId
     * @param OrderPrice
     * @return
     */

    OrderModel updateOrderPrice(String orderId, String OrderPrice) {

        def foundOrderById = orders.find { it.orderId == orderId }

        if(!foundOrderById) { throw new IllegalStateException("Sorry dude but i dont have id like that : ${orderId}") }

        foundOrderById.orderPrice = OrderPrice

        log.info("change order name for : ${foundOrderById.orderId}")

        return foundOrderById

    }
}






