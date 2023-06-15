package org.spring.bookShop.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.bookShop.entities.Book;
import org.spring.bookShop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/book")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllBookDetails(){
        List<Book> bookList = bookService.getAllBooks();
        if(bookList.size() == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There are no books");
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSpecificBookDetails(@PathVariable("id") int id){
        Optional<Book> book = bookService.getBook(id);
        if(book.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book with id = " + id + " doesn't exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PostMapping("/")
    public ResponseEntity<String> addBookDetails(@RequestBody Book book){
        int rowsAffected = bookService.addBook(book);
        if(rowsAffected <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Object is empty");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Book added successfully");
    }

    @PutMapping("/")
    public ResponseEntity<String> updateBookDetails(@RequestBody Book book){
        if(book.getId() == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Object is empty");
        }
        int rowsAffected = bookService.updateBook(book);
        if(rowsAffected <= 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with id = " + book.getId() + " doesn't exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Book updated successfully");
    }

    @PatchMapping("/")
    public ResponseEntity<String> updateBookPrice(@RequestParam("id") String id, @RequestParam("price") String price){
        int bookId =  Integer.parseInt(id);
        int bookPrice = Integer.parseInt(price);

        if(bookId == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Params missing");
        }
        int rowsAffected = bookService.updateBookPrice(bookId, bookPrice);
        if(rowsAffected <= 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with id = " + id + " doesn't exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Price updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") String id){
        int bookId =  Integer.parseInt(id);

        int rowsAffected = bookService.deleteBook(bookId);
        if(rowsAffected <= 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with id = " + id + " doesn't exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully");
    }


}
