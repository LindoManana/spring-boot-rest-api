package io.javatricks.springboot.restapi.dto;

public class EmployeeDTO {

	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private Double salary;

	private Long phoneNo;

	public EmployeeDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Double getSalary() {
		return salary;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

}
