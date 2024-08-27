package amzur.micronaut.gorm.service

import amzur.micronaut.gorm.domain.Book
import amzur.micronaut.gorm.domain.LineItem
import amzur.micronaut.gorm.domain.OrderDomain
import amzur.micronaut.gorm.domain.Users
import amzur.micronaut.gorm.model.OrderModel
import grails.gorm.transactions.Transactional

import javax.inject.Singleton


@Singleton
class OrderService {

    @Transactional
    def createOrder(OrderModel orderModel){
        OrderDomain order = new OrderDomain()

        order.user=Users.get(orderModel.userId)
        order = order.save()


        orderModel.bookIds.each { bookId ->
            LineItem lineItem = new LineItem()
            lineItem.order = order
            lineItem.book = Book.get(bookId)  // Assuming Book is retrieved by ID
            lineItem.save()
        }
        return "your order Id is ${order.id}"
    }

    @Transactional
    def getOrderById(Long orderId){
        OrderDomain order = OrderDomain.findById(orderId)

        if (!order) {
            return "Order not found" // or throw an exception, depending on your error handling strategy
        }

        OrderModel orderModel = new OrderModel()
        orderModel.orderId = order.id
        orderModel.userId = order.userId
        orderModel.bookIds = []


        // Collecting all bookIds from the line items
        order.lineItems.each { lineItem ->
            orderModel.bookIds << (lineItem.book.id as Integer)
        }

        return orderModel
    }

//    static OrderModel toOrderModel(OrderDomain order) {
//        if (order == null) {
//            return null
//        }
//
//        OrderModel orderModel = new OrderModel()
//        orderModel.userId = order.userId
//       orderModel.orderId=order.id
//
//        return orderModel
//    }

}
