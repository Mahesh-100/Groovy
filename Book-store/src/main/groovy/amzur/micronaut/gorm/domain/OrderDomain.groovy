package amzur.micronaut.gorm.domain

import grails.gorm.annotation.Entity
import groovy.transform.ToString



@Entity
@ToString(includeNames=true)
class OrderDomain {



    static belongsTo = [user: Users]

    static hasMany = [lineItems: LineItem]



}
