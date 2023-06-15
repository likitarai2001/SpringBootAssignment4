package org.spring.bookShop.service;

import lombok.extern.slf4j.Slf4j;
import org.spring.bookShop.entities.Book;
import org.spring.bookShop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookServiceImpl implements BookService{

    @Autowired
    public BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        if(bookList.size() == 0){
            log.warn("There are no books");
        } else {
            log.info(bookList.toString());
        }
        return bookList;
    }

    @Override
    public Optional<Book> getBook(int id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()){
            log.warn("Book with id = " + id + " doesn't exist");
        } else{
            log.info(book.toString());
        }
        return book;
    }

    @Override
    public int addBook(Book book) {
        if(book.getTitle() == null){
            log.warn("Object is empty");
            return 0;
        }
        bookRepository.save(book);
        log.info("Book added successfully");
        return 1;
    }

    @Override
    public int updateBook(Book book){
        Optional<Book> bookObj = bookRepository.findById(book.getId());
        if(bookObj.isEmpty()){
            log.warn("Book with id = " + book.getId() + " doesn't exist");
            return 0;
        }
        int rowsAffected = bookRepository.updateBook(book.getTitle(), book.getAuthor(), book.getPrice(), book.getId());
        log.info("Book updated successfully");
        return rowsAffected;
    }

    @Override
    public int updateBookPrice(int id, int price){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()){
            log.warn("Book with id = " + id + " doesn't exist");
            return 0;
        }
        int rowsAffected = bookRepository.updateBookPrice(id, price);
        log.info("Price updated successfully");
        return rowsAffected;
    }

    @Override
    public int deleteBook(int id){
        Optional<Book> book = bookRepository.findById(id);
        try{
            if(book.isPresent()){
                bookRepository.deleteById(id);
                log.info("Book deleted successfully");
                return 1;
            }else{
                throw new NullPointerException("Book with id = " + id + " doesn't exist");
            }
        }catch (Exception e){
            log.error(String.valueOf(e));
        }
        return 0;
    }
}
