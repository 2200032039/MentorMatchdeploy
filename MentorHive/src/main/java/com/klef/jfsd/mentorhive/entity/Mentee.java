package com.klef.jfsd.mentorhive.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mentee")
public class Mentee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Mentee() {
		super();
	}

	public Mentee(Long id, String name, Date dob, String email, String password, 
			List<Course> courses, List<Feedback> feedbacks, List<Notification> notifications) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.password = password;
		
		this.courses = courses;
		this.feedbacks = feedbacks;
		this.notifications = notifications;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	@Column(nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

   

    @ManyToMany(mappedBy = "mentees")
    private List<Course> courses = new ArrayList<>(); 

    @OneToMany(mappedBy = "mentee", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "mentee", cascade = CascadeType.ALL)
    private List<Notification> notifications;
    
    @Override
    public String toString() {
        return "Mentee{id=" + id + ", name='" + name + "', email='" + email + "', dob=" + dob + "}";
    }
    
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    // Add Getter and Setter
    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }



    // Getters and Setters
}
