package com.riversoft.order.model

import javax.validation.constraints.NotNull

class OrderModel {

    @NotNull
    String orderId

    @NotNull
    String orderName

    @NotNull
    String orderPrice
}
