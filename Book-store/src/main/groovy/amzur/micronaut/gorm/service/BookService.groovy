package amzur.micronaut.gorm.service

import amzur.micronaut.gorm.domain.Author
import amzur.micronaut.gorm.domain.Book
import amzur.micronaut.gorm.model.AuthorModel
import amzur.micronaut.gorm.model.BookModel
import grails.gorm.transactions.Transactional
import org.springframework.dao.DataIntegrityViolationException

import javax.inject.Singleton


@Singleton
class BookService {

    @Transactional
    def saveBook(BookModel bookModel){
        // Method to save a book with author
        if (bookModel == null || bookModel.author == null) {
            return "Invalid book or author data provided"
        }

        // Find or create the author
        Author author = findOrCreateAuthor(bookModel.author)

        // Create a new book and set its details
        Book book = new Book()
        book.title = bookModel.title
        book.pages = bookModel.pages
        book.publishedDate = bookModel.publishedDate
        book.author = author

        // Save the book
        book.save(flush: true) // Use flush to immediately persist the changes

        return "Book saved successfully"

    }
    // Helper method to find or create an author
    private Author findOrCreateAuthor(AuthorModel authorModel) {
        // Attempt to find an existing author by firstName and lastName
        Author existingAuthor = Author.findByFirstNameAndLastName(authorModel.firstName, authorModel.lastName)

        if (existingAuthor != null) {
            // Author exists, return the existing author
            return existingAuthor
        } else {
            // Author does not exist, create a new one
            Author newAuthor = new Author()
            newAuthor.firstName = authorModel.firstName
            newAuthor.lastName = authorModel.lastName
            newAuthor.birthDate = authorModel.birthDate
            newAuthor.save(flush: true) // Save the new author

            return newAuthor
        }
    }
    @Transactional
    def deleteBook(Long id){
        Book book=Book.findById(id)
        if(book){
            book.delete()
            return "removed successfully"
        }else{
            return "Book Not Found"
        }

    }

    @Transactional
    def updateBook(Long bookId, BookModel updatedBookModel) {
        if (updatedBookModel == null) {
            return "Invalid book data provided"
        }

        // Find the existing book by ID
        Book book = Book.findById(bookId)

        if (book == null) {
            return "Book not found"
        }

        // Check for existing author
        Author author = null
        if (updatedBookModel.author != null) {
            author = Author.findByFirstNameAndLastName(
                    updatedBookModel.author.firstName,
                    updatedBookModel.author.lastName
            )
            if (author == null) {
                // Create new author if not found
                author = new Author(
                        firstName: updatedBookModel.author.firstName,
                        lastName: updatedBookModel.author.lastName,
                        birthDate: updatedBookModel.author.birthDate
                )
                author.save()
            }
        }

        // Update book details (excluding title)
        book.pages = updatedBookModel.pages
        book.publishedDate = updatedBookModel.publishedDate
        book.author = author

        try {
            // Save the updated book
            book.save(flush: true)  // Using flush to immediately persist changes
        } catch (DataIntegrityViolationException e) {
            // Handle unique constraint violation
            return "Error updating book: ${e.message}"
        }

        return "Book updated successfully"

    }



    @Transactional
    def getBookById(Long id) {
        Book book = Book.get(id)
        if (book) {
            BookModel bookModel = new BookModel()
            bookModel.title = book.title

            // Instantiate the AuthorModel and set its properties
            AuthorModel authorModel = new AuthorModel()
            authorModel.firstName = book.author.firstName
            authorModel.lastName = book.author.lastName
            authorModel.birthDate = book.author.birthDate

            // Set the authorModel in bookModel
            bookModel.author = authorModel
            bookModel.pages = book.pages
            bookModel.publishedDate = book.publishedDate

            return bookModel
        } else {
            return "Book Not Found"
        }
    }

    @Transactional
    def getAllBooks() {
        List<BookModel> books = Book.list().collect { book ->
            new BookModel(
                    title: book.title,
                    pages: book.pages,
                    publishedDate: book.publishedDate,
                    author: new AuthorModel(
                            firstName: book.author.firstName,
                            lastName: book.author.lastName,
                            birthDate:  book.author.birthDate
                    )
            )
        }
        return books
    }





}
