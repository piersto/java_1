package ru.stqa.pft.rest.model;

import java.util.Objects;

public class Issue {
    private int id;
    private String subject;
    private String description;
    private String state_name;
    private Creator creator;

    public String getState_name() { return state_name; }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public Issue withState_name(String state_name) {
        this.state_name = state_name;
        return this;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id && Objects.equals(subject, issue.subject) && Objects.equals(description, issue.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, description);
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }
}
