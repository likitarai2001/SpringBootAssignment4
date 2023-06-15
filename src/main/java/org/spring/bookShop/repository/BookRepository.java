package org.spring.bookShop.repository;

import jakarta.transaction.Transactional;
import org.spring.bookShop.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Transactional
    @Modifying
    @Query(
            value = "UPDATE book SET title = ?1, author = ?2, price = ?3 WHERE id = ?4",
            nativeQuery = true
    )
    public int updateBook(String title, String author, int price, int id);

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE book SET price = ?2 WHERE id = ?1",
            nativeQuery = true
    )
    public int updateBookPrice(int id, int price);
}
