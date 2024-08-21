package amzur.micronaut.gorm.controller

import amzur.micronaut.gorm.model.BookModel
import amzur.micronaut.gorm.service.BookService
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


@Controller("/books")
class BookController {
    @Inject
    BookService bookService


    @Post
    @Status(HttpStatus.CREATED)
    def saveBook(@Body BookModel bookModel){
        return bookService.saveBook(bookModel)
    }

    @Delete("/{id}")
    def deleteBook(@PathVariable Long id){
        return bookService.deleteBook(id)
    }
    @Put("{id}")
    def updateBook(@PathVariable Long id,@Body BookModel bookModel){
        return bookService.updateBook(id,bookModel)
    }
    @Get("/{id}")
    def getBookById(@PathVariable Long id){
        return bookService.getBookById(id)
    }

    @Get
    def getAllBooks(){
        return bookService.getAllBooks()
    }
}
