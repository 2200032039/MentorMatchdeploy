package com.klef.jfsd.mentorhive.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "mentor")
public class Mentor {

   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Mentor() {
		super();
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
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

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	private String name;

    private LocalDate dob;

    private String email;

    private String password;

    @Column(length = 500)  // Limiting the size of skills description
    private String skills;

    private int availability;  // Number of mentees a mentor can handle

    private String status;  // "Pending", "Approved", or "Rejected"

    
    
    public Mentor(Long id, String name, LocalDate dob, String email, String password, String skills, int availability,
			String status, String specialisation, String location, int age, String bio, LocalDate createdAt,
			LocalDate updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.email = email;
		this.password = password;
		this.skills = skills;
		this.availability = availability;
		this.status = status;
		this.specialisation = specialisation;
		this.location = location;
		this.age = age;
		this.bio = bio;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

    private String specialisation;
	public String getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	private String location;
    
    private int age;

    private String bio;  // Optional short bio

    private LocalDate createdAt;

    private LocalDate updatedAt;

    // Getters and Setters
    
    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mentee> mentees;

    // Add Getter and Setter
    public List<Mentee> getMentees() {
        return mentees;
    }

    public void setMentees(List<Mentee> mentees) {
        this.mentees = mentees;
        
    }
   
    

    @OneToMany(mappedBy = "mentor")
    private List<Notification> notifications;
    
    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    
    

}
