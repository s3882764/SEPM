package com.spem.application.pojo;

import java.util.List;

public class Reader {

	private String id;
	
	private String startTime;
	
	private String completionTime;
	
	private String email;
	
	private String name;
	
	private String firstName;
	
	private String  studentId;
	
	private String workshopClass;
	
	private List<String> preferStudents;
	
	private List<ProjectOption> projectOption;
	
	private List<Technology> technologies;
	
	private String timezone;
	
	private List<PreferredDays> preferredWeekDays;
	
	private List<PreferredTime> preferredTimes;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	private Integer groupId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(String completionTime) {
		this.completionTime = completionTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getWorkshopClass() {
		return workshopClass;
	}

	public void setWorkshopClass(String workshopClass) {
		this.workshopClass = workshopClass;
	}

	
	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public List<String> getPreferStudents() {
		return preferStudents;
	}

	public void setPreferStudents(List<String> preferStudents) {
		this.preferStudents = preferStudents;
	}

	public List<ProjectOption> getProjectOption() {
		return projectOption;
	}

	public void setProjectOption(List<ProjectOption> projectOption) {
		this.projectOption = projectOption;
	}

	public List<Technology> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<Technology> technologies) {
		this.technologies = technologies;
	}

	public List<PreferredDays> getPreferredWeekDays() {
		return preferredWeekDays;
	}

	public void setPreferredWeekDays(List<PreferredDays> preferredWeekDays) {
		this.preferredWeekDays = preferredWeekDays;
	}

	public List<PreferredTime> getPreferredTimes() {
		return preferredTimes;
	}

	public void setPreferredTimes(List<PreferredTime> preferredTimes) {
		this.preferredTimes = preferredTimes;
	}

	@Override
	public String toString() {
		return "Reader [id=" + id + ", startTime=" + startTime + ", completionTime=" + completionTime + ", email="
				+ email + ", name=" + name + ", firstName=" + firstName + ", studentId=" + studentId
				+ ", workshopClass=" + workshopClass + ", preferStudents=" + preferStudents + ", projectOption="
				+ projectOption + ", technologies=" + technologies + ", timezone=" + timezone + ", preferredWeekDays="
				+ preferredWeekDays + ", preferredTimes=" + preferredTimes + ", groupId=" + groupId + "]";
	}

	
	
	
}
