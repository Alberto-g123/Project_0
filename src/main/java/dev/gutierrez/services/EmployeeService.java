package dev.gutierrez.services;

import dev.gutierrez.entities.Employee;


import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    Employee getEmployee(int id);
    List<Employee> getAllEmployees();

    boolean deleteEmployee(int id);

    Employee editEmployee(Employee employee);
}
