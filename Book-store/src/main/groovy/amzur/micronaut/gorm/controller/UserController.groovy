package amzur.micronaut.gorm.controller


import amzur.micronaut.gorm.model.UserModel
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

import javax.inject.Inject

@Controller('/users')
class UserController {

    @Inject
    UserService userService


//    UserController(UserService userService) {
//        this.userService = userService
//    }

    @Post
    @Status(HttpStatus.CREATED)
    def addUser( @Body UserModel userRequest){
        return userService.addUser(userRequest)
    }
    @Delete("/{userId}")
    def removeUser(@PathVariable  int userId){
        return  userService.removeUser(userId)
    }
    @Get
    def getAllUsers(){
        return userService.getAllUsers()
    }


    @Put("/{userId}")
    def updateUser(@PathVariable Long userId, @Body UserModel users) {
        return userService.updateUser(userId, users)
    }
    @Get("/{userId}")
    def getEmail(@PathVariable Long userId){
        return userService.getEmail(userId)
    }

    @Post("/login")
    def login(@Body UserModel userModel)
    {
        return  userService.login(userModel.email,userModel.password)
    }
}
