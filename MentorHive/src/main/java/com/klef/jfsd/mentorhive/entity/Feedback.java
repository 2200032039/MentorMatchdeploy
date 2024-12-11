package com.klef.jfsd.mentorhive.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {

    public Feedback(Long id, String feedbackText, Mentee mentee) {
		
		this.id = id;
		this.feedbackText = feedbackText;
		this.mentee = mentee;
	}

	public Feedback() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFeedbackText() {
		return feedbackText;
	}

	public void setFeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}

	public Mentee getMentee() {
		return mentee;
	}

	public void setMentee(Mentee mentee) {
		this.mentee = mentee;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String feedbackText;

    @ManyToOne
    @JoinColumn(name = "mentee_id")
    private Mentee mentee;  // This establishes the relationship with Mentee

    // Getters and Setters
}
