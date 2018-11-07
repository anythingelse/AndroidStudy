package com.example.administrator.kankanbook.db;

import org.litepal.crud.DataSupport;

public class BookShelfList extends DataSupport {
    public int id;
    public String bookId;
    public String bookTitle;
    public String bookAuthor;
    private String bookPicUrl;
    private int order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPicUrl() {
        return bookPicUrl;
    }

    public void setBookPicUrl(String bookPicUrl) {
        this.bookPicUrl = bookPicUrl;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
