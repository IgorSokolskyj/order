package com.riversoft.order.model

import javax.validation.constraints.NotNull

class UserModel {

    @NotNull
    String userId

    @NotNull
    String userName
}
