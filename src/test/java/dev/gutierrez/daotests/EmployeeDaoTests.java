package dev.gutierrez.daotests;

import dev.gutierrez.daos.EmployeeDAO;
import dev.gutierrez.daos.EmployeeDaoLocal;
import dev.gutierrez.entities.Employee;

import org.junit.jupiter.api.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDaoTests {


    static EmployeeDAO employeeDAO = new EmployeeDaoLocal();

    @Test
    @Order(1)
    void create_employee_test(){
        Employee employee = new Employee(0, "Alberto", "Gutierrez");
        Employee savedEmployee = employeeDAO.createEmployee(employee);
        Assertions.assertNotEquals(0, savedEmployee.getId());
        System.out.println(savedEmployee);
    }

    @Test
    @Order(2)
    void get_employee_by_id_test(){
        Employee employee = employeeDAO.getEmployeeById(1); // we will assume we saved the first book
        Assertions.assertEquals("Alberto", employee.getFname());
    }

    @Test
    @Order(3)
    void update_employee_test(){

        Employee employee2 = new Employee(1, "Luis", "Gutierrez");
        Employee updatedEmployee = employeeDAO.updateEmployee(employee2);
        Employee employee = employeeDAO.getEmployeeById(1);
        Assertions.assertEquals("Luis", employee.getFname());
        System.out.println();

    }

    @Test
    @Order(4)
    void delete_employee_by_id_test(){
        boolean result = employeeDAO.deleteEmployeeById(1);
        Assertions.assertTrue(result);
    }



}
