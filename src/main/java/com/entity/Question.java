package com.entity;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;
@Entity
@Table(name="question102")
public class Question {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qid")
    private  int qid;

    private String qname;
    private String username;
    @ElementCollection
    @CollectionTable(
            name="answer102",
            joinColumns = @JoinColumn(name = "qid")
    )
    @MapKeyColumn(name="answer")
    @Column(name="username")
    private Map<String,String> answers;//List can be of any type
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

    public Map<String, String> getAnswers() {
        return answers;
    }
    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
