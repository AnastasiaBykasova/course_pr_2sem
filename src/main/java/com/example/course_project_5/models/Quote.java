package com.example.course_project_5.models;

import java.sql.Date;

public class Quote {
    private int id;
    private String text;
    private Date creationDate;
    private String subject;
    private String teacher;
    private int creatorID;
    private int accessLevel;

    public Quote(int id,
                 String text,
                 Date creationDate,
                 String subject,
                 String teacher,
                 int creatorID,
                 int accessLevel
    ) {
        this.id = id;
        this.text = text;
        this.creationDate = creationDate;
        this.subject = subject;
        this.teacher = teacher;
        this.creatorID = creatorID;
        this.accessLevel = accessLevel;
    }
    public Quote(int id,
                 Quote quote
    ) {
        this.id = id;
        this.text = quote.getText();
        this.creationDate = quote.getCreationDate();
        this.subject = quote.getSubject();
        this.teacher = quote.getTeacher();
        this.creatorID = quote.getCreatorID();
        this.accessLevel = quote.getAccessLevel();
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                ", subject='" + subject + '\'' +
                ", teacherID=" + teacher +
                ", teacher=" + teacher +
                ", creatorID=" + creatorID +
                ", accessLevel=" + accessLevel +
                '}';
    }
}
