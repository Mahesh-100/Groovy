package amzur.micronaut.gorm.service


import amzur.micronaut.gorm.domain.Users
import amzur.micronaut.gorm.handlers.UserNotFound
import amzur.micronaut.gorm.model.UserModel
import grails.gorm.transactions.Transactional

import javax.inject.Singleton

@Singleton
class UserService {

    @Transactional
    def addUser(UserModel userModel){
        Users users =UserModel.toUser(userModel)
        users.save()
        return users
    }

    @Transactional
    def removeUser(Long id){
        Users users = Users.findById(id)
        if (users) {
             users.delete()
            return true
        } else {
           throw new UserNotFound("User not Found")
        }
    }

    @Transactional
    def updateUser(Long id, UserModel updatedUser){
        Users users = Users.findById(id)
        if (users) {
            users.name = updatedUser.name
            users.email = updatedUser.email
            users.address = updatedUser.address
            users.password=updatedUser.password
            users.phoneNumber=updatedUser.phoneNumber


            return users.save()
        } else {
            throw new UserNotFound("User Not Found")
        }
    }

    @Transactional
    def getAllUsers(){
        def users=Users.findAll()
        return users
    }
    @Transactional
    def getEmail(Long id){
        Users users = Users.findById(id)
        if (users) {

            return users.email
        } else {
           throw new UserNotFound("User Not Found")
        }
    }

    @Transactional
    def login(String email,String password)
    {
        Users user=Users.findByEmailAndPassword(email,password)
        if(user)
        {
            return  user
        }
        else
        {
            throw new UserNotFound("Invalid credentials")
        }
    }

}
