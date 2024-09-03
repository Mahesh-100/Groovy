package amzur.micronaut.gorm.controller


import amzur.micronaut.gorm.model.UserModel
import amzur.micronaut.gorm.service.OrderService
import amzur.micronaut.gorm.service.UserService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.http.annotation.Status
import io.micronaut.http.HttpStatus
import io.micronaut.http.HttpResponse


import javax.inject.Inject

@Controller('/user-process')
class UserController {

    @Inject
    UserService userService

    @Inject
    OrderService orderService


//    UserController(UserService userService) {
//        this.userService = userService
//    }

    @Post
    @Status(HttpStatus.CREATED)
    def addUser(@Body UserModel userRequest) {
        try {
            def user = userService.addUser(userRequest)
            if (user) {
                return HttpResponse.created(user)
            } else {
                return HttpResponse.badRequest("Failed to add user")
            }
        } catch (Exception e) {
            return HttpResponse.serverError("An error occurred: ${e.message}")
        }
    }
    @Delete("/delete/{userId}")
    @Status(HttpStatus.NO_CONTENT)
    def removeUser(@PathVariable  int userId){
        try {
            def isDeleted = userService.removeUser(userId)
            if (isDeleted) {
                return HttpResponse.noContent()
            } else {
                return HttpResponse.badRequest("Failed to remove user")
            }
        } catch (Exception e) {
            return HttpResponse.serverError("An error occurred: ${e.message}")
        }
    }
    @Get
    def getAllUsers(){
        return userService.getAllUsers()
    }


    @Put("/update/{userId}")
    @Status(HttpStatus.CREATED)
    def updateUser(@PathVariable Long userId, @Body UserModel updatedUserRequest) {
        try {
            def updatedUser = userService.updateUser(userId,updatedUserRequest)
            if (updatedUser) {
                return HttpResponse.created(updatedUser)
            } else {
                return HttpResponse.badRequest("Failed to update user")
            }
        } catch (Exception e) {
            return HttpResponse.serverError("An error occurred: ${e.message}")
        }
    }
    @Get("/{userId}")
    def getEmail(@PathVariable Long userId){
        return userService.getEmail(userId)
    }

    @Post("/login")
    @Status(HttpStatus.OK)
    def login(@Body UserModel userModel) {
        try {
            def user = userService.login(userModel.email, userModel.password)

            //def userWithOrders=orderService.getAllOrdersByUserId(user.id)

            if (user) {
                return HttpResponse.ok(user)
            } else {
                return HttpResponse.badRequest("Failed to add user")
            }
        } catch (Exception e) {
            return HttpResponse.serverError("An error occurred: ${e.message}")
        }
    }
}
