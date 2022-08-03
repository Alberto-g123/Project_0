package dev.gutierrez.doas;

import dev.gutierrez.entities.Employee;

import java.util.Map;

public interface EmployeeDAO {

    // creates employee
    Employee createEmployee(Employee employee);

    // reads employee
    Employee getEmployeeById(int id);

    Map<Integer,Employee> getAllEmployees();


    // update employee
    Employee updateEmployee(Employee employee);

    // Delete employee
    boolean deleteEmployeeById(int id);

}
