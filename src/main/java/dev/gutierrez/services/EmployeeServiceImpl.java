package dev.gutierrez.services;

import dev.gutierrez.daos.EmployeeDAO;
import dev.gutierrez.entities.Employee;

import java.util.List;


public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }
    @Override
    public Employee createEmployee(Employee employee) {

        if(employee.getFname().length() == 0){
            throw new RuntimeException("employee must have a First Name");
        }
        if(employee.getLname().length() == 0){
            throw new RuntimeException("Employee must have a Last Name");
        }
        Employee savedEmployee = this.employeeDAO.createEmployee(employee);
        return savedEmployee;
    }

    @Override
    public Employee getEmployee(int id) {
        return this.employeeDAO.getEmployeeById(id);

    }

    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeDAO.getAllEmployees();
    }


    @Override
    public boolean deleteEmployee(int id) {
        boolean isSuccessful = this.employeeDAO.deleteEmployeeById(id);
        return isSuccessful;
    }

    @Override
    public Employee editEmployee(Employee employee) {
       if (employee.getFname().length() == 0){
           throw new RuntimeException("First name cannot be empty");
       }
       if(employee.getLname().length() == 0){
           throw new RuntimeException("Last name cannot be empty");
       }
       return this.employeeDAO.updateEmployee(employee);
    }
}
