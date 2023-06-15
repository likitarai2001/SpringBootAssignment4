package org.spring.bookShop.service;

import org.spring.bookShop.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public List<Book> getAllBooks();

    public Optional<Book> getBook(int id);

    public int addBook(Book book);

    public int updateBook(Book book);

    public int updateBookPrice(int id, int price);

    public int deleteBook(int id);
}
