package dev.gutierrez.doas;

import dev.gutierrez.entities.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeDaoLocal implements EmployeeDAO{

    private Map<Integer,Employee> employeeTable = new HashMap();
    private int idMaker = 1;

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setId(idMaker);
        idMaker++;
        employeeTable.put(employee.getId(),employee);
        return employee;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeTable.get(id);
    }

    public Map<Integer, Employee> getAllEmployees(){ return this.employeeTable; }



    @Override
    public Employee updateEmployee(Employee employee) {
        employeeTable.put(employee.getId(),employee);
        return employee;
    }

    @Override
    public boolean deleteEmployeeById(int id) {
         Employee employee = employeeTable.remove(id);
        if(employee == null){
            return false;
        }
        return true;
    }
}
