package org.library;

import java.util.HashMap;
import java.util.List;

public class Librarian {
    String name;
    String employee_id;
    static Library library;
    HashMap<String, Book> books;

    public Librarian() {
        library = new Library();
        books = library.books;
    }

    public void add_book(Book book) {
        books.put(book.ISBN, book);
    }

    public void remove_book(Book book) {
        books.remove(book.ISBN, book);
    }

    public void view_inventory() {
        for(String i:books.keySet()) {
            System.out.println(books.get(i).title+":"+books.get(i).copies+" available");
        }
    }

    public static void main(String[] args) {
        Librarian librarian = new Librarian();

        Book book1 = new Book("Book Title 1", "Author 1", "1234567890", 19);
        Book book2 = new Book("Book Title 2", "Author 2", "0987654321", 46);

        librarian.add_book(book1);
        librarian.add_book(book2);

        librarian.view_inventory();

        Member member1 = new Member("MemberA", "1");
        Member member2 = new Member("MemberB", "2");

        library.register_member(member1);
        library.register_member(member2);

        library.issue_book("1", "0987654321");
        library.issue_book("2", "1234567890");


        librarian.remove_book(book1);
        library.issue_book("2", "1234567890");
        librarian.view_inventory();
        library.list_all_books();
    }
}
