package io.javatricks.springboot.restapi.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javatricks.springboot.restapi.dto.EmployeeDTO;
import io.javatricks.springboot.restapi.entities.Employee;
import io.javatricks.springboot.restapi.exception.ResourceNotFoundException;
import io.javatricks.springboot.restapi.repository.EmployeeRepository;
import io.javatricks.springboot.restapi.utilities.AppUtils;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id :: " + employeeId));
		return employee;
	}

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(EmployeeDTO employeeDetails)
			throws ResourceNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Employee employee = employeeRepository.findById(employeeDetails.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Employee not found with Id :: " + employeeDetails.getId()));

		BeanUtils.copyProperties(employeeDetails, employee, AppUtils.getNullPropertyNames(employeeDetails));

		return employeeRepository.save(employee);
	}

	public Map<String, Boolean> deleteEmployee(Long employeeId) throws Exception {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id :: " + employeeId));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
