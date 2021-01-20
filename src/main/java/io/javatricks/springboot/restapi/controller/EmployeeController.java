package io.javatricks.springboot.restapi.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import io.javatricks.springboot.restapi.dto.EmployeeDTO;
import io.javatricks.springboot.restapi.entities.Employee;
import io.javatricks.springboot.restapi.exception.handler.ResourceNotFoundException;
import io.javatricks.springboot.restapi.service.EmployeeService;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employees")
	public @ResponseBody ResponseEntity<?> save(@Valid @RequestBody Employee employee) {

		LOGGER.info("@@@ request ==> " + new Gson().toJson(employee));

		return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
	}

	@GetMapping("/employees/{id}")
	public @ResponseBody ResponseEntity<?> findById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {

		LOGGER.info("@@@ request ==> " + id);

		Employee employee = employeeService.findById(id);
		return new ResponseEntity<>(employee, HttpStatus.OK);

	}

	@GetMapping("/employees")
	public @ResponseBody ResponseEntity<?> findAll() {

		return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
	}

	@PutMapping("/employees")
	public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody Employee employee)
			throws ResourceNotFoundException {

		LOGGER.info("@@@ request ==> " + new Gson().toJson(employee));

		return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);
	}

	@PatchMapping("/employees")
	public @ResponseBody ResponseEntity<?> patch(@RequestBody EmployeeDTO employeeDTO)
			throws ResourceNotFoundException {

		LOGGER.info("@@@ request ==> " + new Gson().toJson(employeeDTO));

		return new ResponseEntity<>(employeeService.patch(employeeDTO), HttpStatus.OK);
	}

	@DeleteMapping("/employees/{id}")
	public @ResponseBody ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

		LOGGER.info("@@@ request ==> " + id);

		return new ResponseEntity<>(employeeService.delete(id), HttpStatus.OK);
	}

}