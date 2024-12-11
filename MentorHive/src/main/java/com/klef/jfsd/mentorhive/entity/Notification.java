package com.klef.jfsd.mentorhive.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message; // Notification message content

    @ManyToOne
    @JoinColumn(name = "mentee_id")
    private Mentee mentee;  // Relationship with Mentee

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;  // Relationship with Mentor

    public Notification() {
        super();
    }

    // Constructor for Mentee
    public Notification(String message, Mentee mentee) {
        this.message = message;
        this.mentee = mentee;
    }

    // Constructor for Mentor
    public Notification(String message, Mentor mentor) {
        this.message = message;
        this.mentor = mentor;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Mentee getMentee() {
        return mentee;
    }

    public void setMentee(Mentee mentee) {
        this.mentee = mentee;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }
}
