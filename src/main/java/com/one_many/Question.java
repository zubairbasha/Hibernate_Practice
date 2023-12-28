package com.one_many;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "question501")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String qname;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="qid")
    @OrderColumn(name = "type")
    private List<Answer> answer;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }
}
