package amzur.micronaut.gorm.model

import amzur.micronaut.gorm.domain.Users

class UserModel {
    String name
    String email
    String password
    String phoneNumber
    String address


    static Users toUser(UserModel userModel){
        if (userModel == null) {
            return null
        }
        Users users=new Users()
        users.name=userModel.name
        users.email=userModel.email
        users.password=userModel.password
        users.phoneNumber=userModel.phoneNumber
        users.address=userModel.address

        return users
    }
}
