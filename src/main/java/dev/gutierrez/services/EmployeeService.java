package dev.gutierrez.services;

import dev.gutierrez.entities.Employee;


import java.util.Map;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    Employee getEmployee(int id);
    Map<Integer,Employee> getAllEmployees();

    boolean deleteEmployee(int id);

    Employee editEmployee(Employee employee);
}
