package org.library;

public class Book {
    String title;
    String author;
    String ISBN;
    Integer copies;

    public Book(String title, String author, String isbn, int copies) {
        this.title = title;
        this.author = author;
        this.ISBN = isbn;
        this.copies = copies;
    }

    public Book get_details(){
        return new Book(title, author, ISBN, copies);
    }

    public boolean is_available() {
        if(copies==0) {
            return false;
        }
        else {
            return true;
        }
    }

    public void borrow_book() {
        copies--;
    }

    public void return_book() {
        copies++;
    }

}
