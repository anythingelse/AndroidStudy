package com.example.administrator.kankanbook.db;

import org.litepal.crud.DataSupport;

public class RankingList extends DataSupport{
    private int id;
    private int ranking;
    private String bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookShortIntro;//短文预览
    private String bookMajorCate;//主分类
    private String bookMinorCate;//子分类

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
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

    public String getBookShortIntro() {
        return bookShortIntro;
    }

    public void setBookShortIntro(String bookShortIntro) {
        this.bookShortIntro = bookShortIntro;
    }

    public String getBookMajorCate() {
        return bookMajorCate;
    }

    public void setBookMajorCate(String bookMajorCate) {
        this.bookMajorCate = bookMajorCate;
    }

    public String getBookMinorCate() {
        return bookMinorCate;
    }

    public void setBookMinorCate(String bookMinorCate) {
        this.bookMinorCate = bookMinorCate;
    }
}
