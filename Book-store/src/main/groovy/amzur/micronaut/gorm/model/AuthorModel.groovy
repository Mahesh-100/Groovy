package amzur.micronaut.gorm.model

import amzur.micronaut.gorm.domain.Author


class AuthorModel {

    String firstName

    String lastName

    Date birthDate


    static Author toAuthor(AuthorModel authorModel){
        if (authorModel == null) {
            return null
        }
        Author author = new Author()
        author.firstName = authorModel.firstName
        author.lastName = authorModel.lastName
        author.birthDate = authorModel.birthDate
        return author
    }

}
