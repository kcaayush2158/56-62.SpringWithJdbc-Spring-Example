package com.application.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import com.application.dao.EmployeeDao;
import com.application.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void createEmployee(Employee employee) {
		int update = jdbcTemplate.update("INSERT INTO employee_table(employee_name,email,gender,salary) VALUES(?,?,?,?)",
					employee.getEmployeeName(), employee.getEmail(), employee.getGender(), employee.getSalary());
		if (update > 0) {
			System.out.println("Employee is created");
		}
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		String SQL = "SELECT * FROM emplyee_table where employee_id=?";
		Employee employee = jdbcTemplate.queryForObject(SQL, new EmployeeRowMapper(), employeeId);
		return employee;
	}

	@Override
	public void deleteEmployeeById(int employeeId) {
		String SQL = "DELETE FROM employee_table WHERE employee_id = ?";
		int update = jdbcTemplate.update(SQL, employeeId);
		if (update > 0) {
			System.out.println("the row is deleted");
		}
	}

	@Override
	public void updateEmployeeEmailById(String newEmail, int employeeId) {
		String SQL = "UPDATE employee_table set email=? WHERE employee_Id=? ";
		int update = jdbcTemplate.update(SQL, newEmail, employeeId);
		if (update > 0) {
			System.out.println("the row is updated");
		}
	}

	@Override
	public List<Employee> getAllEmployeesDetails() {
		String SQL = "SELECT * FROM employee_table";
		return jdbcTemplate.query(SQL, new EmployeeRowMapper());
	}

}
