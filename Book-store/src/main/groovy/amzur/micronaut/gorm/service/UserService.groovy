package amzur.micronaut.gorm.service


import amzur.micronaut.gorm.domain.Users
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
            return "removed successfully"
        } else {
            return "User Not Found"
        }
    }

    @Transactional
    def updateUser(Long id, UserModel updatedUser){
        Users users = Users.findById(id)
        if (users) {
            users=UserModel.toUser(updatedUser)

            return users.save()
        } else {
            return "User Not Found"
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
            return "User Not Found"
        }
    }

}
