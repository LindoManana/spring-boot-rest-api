package io.javatricks.springboot.restapi.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import io.javatricks.springboot.restapi.dto.EmployeeDTO;
import io.javatricks.springboot.restapi.entities.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Employee employeeFromDto(EmployeeDTO employeeDTO, @MappingTarget Employee employee);

}
