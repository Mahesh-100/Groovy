package amzur.micronaut.gorm.domain

import grails.gorm.annotation.Entity
import groovy.transform.ToString



@Entity
@ToString(includeNames=true)
class Book {

    String title
    Integer pages
    Date publishedDate

    static belongsTo = [author: Author]
    static constraints = {
        title nullable: false, blank: false ,unique: true
        pages nullable: false, min: 1
        publishedDate nullable: true
    }

}
