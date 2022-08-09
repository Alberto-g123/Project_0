package dev.gutierrez.daos;

import dev.gutierrez.entities.Employee;

import java.util.HashMap;
import java.util.List;
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

    @Override
    public List<Employee> getAllEmployees(){
        //when it was Map<Integer, Employee>
        //return this.employeeTable;
        return  null;
    }



    @Override
    public Employee updateEmployee(Employee employee) {
        employeeTable.put(employee.getId(),employee);
        return employee;
    }

    @Override
    public String deleteEmployeeById(int id) {

        if (employeeTable.get(id).equals(null)){
            System.out.println("employee not found");
            return "error404";

        }else{
            employeeTable.remove(id);
            return "success204";
        }

    }


}
