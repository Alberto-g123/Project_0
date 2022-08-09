package dev.gutierrez.daos;

import dev.gutierrez.entities.Employee;


import java.util.List;


public interface EmployeeDAO {

    // creates employee
    Employee createEmployee(Employee employee);

    // reads employee
    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();


    // update employee
    Employee updateEmployee(Employee employee);

    // Delete employee
    String deleteEmployeeById(int id);



}
