package io.javatricks.springboot.restapi.controller;

import java.lang.reflect.InvocationTargetException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.javatricks.springboot.restapi.dto.EmployeeDTO;
import io.javatricks.springboot.restapi.entities.Employee;
import io.javatricks.springboot.restapi.exception.BadRequestException;
import io.javatricks.springboot.restapi.exception.ResourceNotFoundException;
import io.javatricks.springboot.restapi.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/employees")
	public @ResponseBody ResponseEntity<?> getAllEmployees() {

		return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
	}

	@GetMapping("/employees/{id}")
	public @ResponseBody ResponseEntity<?> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeById(employeeId);
		return new ResponseEntity<>(employee, HttpStatus.OK);

	}

	@PostMapping("/employees")
	public @ResponseBody ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee,
			BindingResult bindingResult) throws BadRequestException {
		if (bindingResult.hasErrors()) {
			throw new BadRequestException(bindingResult.getFieldError().getDefaultMessage());

		}
		return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
	}

	@PutMapping("/employees")
	public @ResponseBody ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO employeeDetails)
			throws ResourceNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		return new ResponseEntity<>(employeeService.updateEmployee(employeeDetails), HttpStatus.OK);
	}

	@DeleteMapping("/employees/{id}")
	public @ResponseBody ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws Exception {

		return new ResponseEntity<>(employeeService.deleteEmployee(employeeId), HttpStatus.OK);
	}

}