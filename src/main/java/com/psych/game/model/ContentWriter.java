package com.psych.game.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contentwriters")
public class ContentWriter extends Employee {

    @JsonIdentityReference
    @Getter @Setter
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Question> editedQuestions = new HashSet<>();
}
