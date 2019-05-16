package com.example.freewheel;

import java.sql.Date;

public class Comment {
    private String comment, publisher;
    private Date date;

    public Comment(String publisher, String comment, int rate, Date date){
        this.publisher = publisher;
        this.comment = comment;
        this.date = date;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }

}