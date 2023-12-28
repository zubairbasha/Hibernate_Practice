package com.one_many;

import javax.persistence.*;

@Entity
@Table(name="answer101")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String answerName;
    private String postedBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }
}
