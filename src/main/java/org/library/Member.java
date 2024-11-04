package org.library;

import java.util.ArrayList;
import java.util.List;

public class Member {
    String name;
    String member_id;
    List<Book> borrowed_books;

    public Member(String name, String member_id) {
        borrowed_books = new ArrayList<>();
        this.name = name;
        this.member_id = member_id;
    }

    public void borrow_book(Book book) {
        borrowed_books.add(book);
    }

    public void return_book(Book book) {
        borrowed_books.remove(book);
    }

    public List<Book> list_borrowed_books() {
        return borrowed_books;
    }
}
