package amzur.micronaut.gorm.service

import amzur.micronaut.gorm.domain.Book
import amzur.micronaut.gorm.domain.LineItem
import amzur.micronaut.gorm.domain.OrderDomain
import amzur.micronaut.gorm.domain.Users
import amzur.micronaut.gorm.handlers.UserNotFound
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
    def getAllOrdersByUserId(Long userId){
        def user=Users.findById(userId)

        def orders=OrderDomain.findAllByUser(user)
        def orderModels = []
        orders.each { order ->
            orderModels << toOrderModel(order)
        }
        return orderModels
    }


    @Transactional
    def getOrderById(Long orderId){
        OrderDomain order = OrderDomain.findById(orderId)
        return toOrderModel(order)
    }


   @Transactional
    def getAllOrders(){
        def orders= OrderDomain.findAll()
        def allOrders = []
        orders.each { order ->
            allOrders << toOrderModel(order)
        }

        return allOrders
    }


    @Transactional
    def updateOrder(Long orderId, OrderModel updatedOrderModel) {
        def order = OrderDomain.findById(orderId)
        if (order) {
            // Explicitly delete existing line items
            LineItem.findAllByOrder(order).each { it.delete(flush: true) }

            // Add new line items based on the updated book IDs
            updatedOrderModel.bookIds.each { bookId ->
                Book book = Book.findById(bookId)
                if (book) {
                    LineItem lineItem = new LineItem(order: order, book: book)
                    order.addToLineItems(lineItem)
                } else {
                    throw new IllegalArgumentException("Book with ID ${bookId} not found")
                }
            }
            order=order.save(flush: true)
            return toOrderModel(order)
        } else {
            throw new UserNotFound("Order Not Found")
        }
    }

    @Transactional
    def deleteOrderById(Long orderId) {
        def order = OrderDomain.findById(orderId)
        if (order) {
            // Delete all associated line items
          //  LineItem.findAllByOrder(order).each { it.delete(flush: true) }

            // Delete the order itself
            order.delete(flush: true)
            return "Order and associated line items deleted successfully."
        } else {
            throw new UserNotFound("Order Not Found")
        }
    }






    static OrderModel toOrderModel(OrderDomain order) {
        if (order == null) {
            throw new UserNotFound("Order Not Found")
        }
        OrderModel orderModel = new OrderModel()
        orderModel.userId = order.userId
       orderModel.orderId=order.id
        orderModel.bookIds=[]
        order.lineItems.each { lineItem ->
            orderModel.bookIds << (lineItem.book.id as Integer)
        }

        return orderModel
    }

}
