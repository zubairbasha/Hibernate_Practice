package com.many_many;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String qname;
    @ManyToMany(
            targetEntity = Answer.class,
            cascade = CascadeType.ALL
    )
    @JoinTable(name="question_answer",
            joinColumns = {
                    @JoinColumn(name = "q_id")
            },
            inverseJoinColumns = {
            @JoinColumn(name="a_id")
            }
    )
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
