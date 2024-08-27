package amzur.micronaut.gorm.domain

import grails.gorm.annotation.Entity
import groovy.transform.ToString

@Entity
@ToString(includeNames=true)
class LineItem {


    static belongsTo = [order: OrderDomain, book: Book]



}

