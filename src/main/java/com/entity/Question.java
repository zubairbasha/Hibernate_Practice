package com.entity;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="question102")
public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qid")
    private  int qid;

    private String qname;
    @ElementCollection
    @CollectionTable(
            name="answer102",
            joinColumns = @JoinColumn(name = "qid")
    )
    @OrderColumn(name="type")
    @Column(name="answer")
    private List<String> answers;//List can be of any type

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
