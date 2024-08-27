package amzur.micronaut.gorm.controller

import amzur.micronaut.gorm.model.OrderModel
import amzur.micronaut.gorm.service.OrderService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post

import javax.inject.Inject

@Controller("/orders")
class OrderController {

    @Inject
    OrderService orderService

    @Post
    def createOrder(@Body OrderModel orderModel){
        return orderService.createOrder(orderModel)
    }

    @Get("/{orderId}")
   def getOrderById(@PathVariable Long orderId) {

        return orderService.getOrderById(orderId)
    }
    @Get("/userId/{userId}")
   def getAllOrdersByUserId(@PathVariable Long userId) {
        return orderService.getAllOrdersByUserId(userId);
    }

}
