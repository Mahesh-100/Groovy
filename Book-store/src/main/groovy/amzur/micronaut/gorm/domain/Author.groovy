package amzur.micronaut.gorm.domain

import grails.gorm.annotation.Entity
import groovy.transform.ToString



@Entity
@ToString(includeNames=true)
class Author {

    String firstName

    String lastName

    Date birthDate


    static hasMany = [books:Book]

    static constraints = {
        firstName nullable: false, blank: false, unique: true
        lastName nullable: false, blank: false
        birthDate nullable: true
    }
}
