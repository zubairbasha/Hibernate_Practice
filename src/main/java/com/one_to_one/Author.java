package com.one_to_one;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "author1")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int birthYear;
   /* @OneToOne
    @JoinColumn(name="book_id")
    private Book book;*/
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Book> book;

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
