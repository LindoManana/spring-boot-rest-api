package io.javatricks.springboot.restapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "First name is mandatory")
	@Column(name = "first_name")
	private String firstName;

	@NotBlank(message = "Last name is mandatory")
	@Column(name = "last_name")
	private String lastName;

	@NotBlank(message = "Email is mandatory")
	@Column(name = "email")
	private String email;

	@Column(name = "salary")
	private Double salary;

	@NotNull(message = "Phone no is mandatory")
	@Column(name = "phone_no")
	private Long phoneNo;

	public Employee() {
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
