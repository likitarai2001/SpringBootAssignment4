package org.spring.bookShop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @SequenceGenerator(
            name = "bookId_seq",
            sequenceName = "bookId_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bookId_seq"
    )
    private int id;
    private String title;
    private String author;
    private int price;

}
