package org.library;

import java.util.HashMap;

public class Library {
    HashMap<String, Book> books;
    HashMap<String, Member> members;

    public Library() {
        books = new HashMap<>();
        members = new HashMap<>();
    }

    public void register_member(Member member) {
        members.put(member.member_id, member);
    }

    public void issue_book(String member_id, String book_isbn) {
        Member member = members.get(member_id);
        Book book = books.get(book_isbn);
        if(member!=null && book!=null) {
            if(book.copies!=0) {
                book.borrow_book();
                member.borrow_book(book);
            }
        }
    }

    public void receive_book(String member_id, String book_isbn) {
        Member member = members.get(member_id);
        Book book = books.get(book_isbn);
        if(member!=null && book!=null) {
            if(book.copies!=0) {
                book.return_book();
                member.return_book(book);
            }
        }
    }

    public void list_all_books() {
        for(String book:books.keySet()) {
            System.out.println(books.get(book).title);
        }
    }
}
