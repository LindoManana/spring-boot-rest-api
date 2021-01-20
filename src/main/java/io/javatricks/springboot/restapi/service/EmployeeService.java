package io.javatricks.springboot.restapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javatricks.springboot.restapi.dto.EmployeeDTO;
import io.javatricks.springboot.restapi.entities.Employee;
import io.javatricks.springboot.restapi.exception.handler.ResourceNotFoundException;
import io.javatricks.springboot.restapi.mapper.EmployeeMapper;
import io.javatricks.springboot.restapi.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	EmployeeMapper employeeMapper;

	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public Employee findById(Long id) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id :: " + id));
		return employee;
	}

	public Employee update(Employee employee) throws ResourceNotFoundException {

		this.findById(employee.getId());

		return employeeRepository.save(employee);
	}

	public Employee patch(EmployeeDTO employeeDTO) throws ResourceNotFoundException {

		Employee employee = this.findById(employeeDTO.getId());

		employee = employeeMapper.employeeFromDto(employeeDTO, employee);

		return employeeRepository.save(employee);
	}

	public String delete(Long id) {
		Employee employee = this.findById(id);
		employeeRepository.delete(employee);
		String msg = "Employee deleted successfully";
		return msg;
	}

}
