package dev.gutierrez.daotests;

import dev.gutierrez.daos.EmployeeDAO;
import dev.gutierrez.daos.EmployeeDaoLocal;
import dev.gutierrez.daos.EmployeeDaoPostgres;
import dev.gutierrez.entities.Employee;

import dev.gutierrez.entities.Status;
import dev.gutierrez.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDaoTests {


    static EmployeeDAO employeeDAO = new EmployeeDaoPostgres();

    @BeforeAll
    static void setup(){
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "create table employee(\n" +

                    "id serial primary key,\n" +
                    "fname varchar(50) not null,\n" +
                    "lname varchar(50) not null);";

            Statement statement = connection.createStatement();
            statement.execute(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }



    @Test
    @Order(1)
    void create_employee_test(){
        Employee employee1 = new Employee(0, "Alberto", "Gutierrez");
        Employee employee2 = new Employee(0, "Alex", "Great");
        Employee employee3 = new Employee(0, "Jackson", "Five");
        Employee employee4 = new Employee(0, "Tee", "Hee");

        Employee savedEmployee = employeeDAO.createEmployee(employee1);
        Assertions.assertNotEquals(0, savedEmployee.getId());
        System.out.println(savedEmployee);

        employeeDAO.createEmployee(employee2);
        employeeDAO.createEmployee(employee3);
        employeeDAO.createEmployee(employee4);
    }

    @Test
    @Order(2)
    void get_employee_by_id_test(){
        Employee employee = employeeDAO.getEmployeeById(1); // when running test increase by one
        Assertions.assertEquals("Alberto", employee.getFname());
        System.out.println(employee);
    }

    @Test
    @Order(3)
    void update_employee_test(){

        Employee employee2 = new Employee(1, "Luis", "Gutierrez");//increase id by 1 everytime test is ran once
        employeeDAO.updateEmployee(employee2);
        Employee employee = employeeDAO.getEmployeeById(1); // should have same id as new employee
        Assertions.assertEquals("Luis", employee.getFname());
        System.out.println(employee);

    }


    @Test
    @Order(4)
    void delete_employee_by_id_test(){
        String result = employeeDAO.deleteEmployeeById(1);// increase number by one every time test is ran
        Assertions.assertEquals("success204", result);
    }

    @Test
    @Order(5)
    void get_all_employees_test(){
        List<Employee> employeeList = employeeDAO.getAllEmployees(); // DB size is should be 3 people if deleted employee is correct
        Assertions.assertEquals(3,employeeList.size());
        System.out.println(employeeList);
    }


    @AfterAll
    static void teardown(){
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql1 = "drop table employee";
            String sql2 = "drop table expense";
            Statement statement = connection.createStatement();
            statement.execute(sql1);
            statement.execute(sql2);

        }catch(SQLException e){
            e.printStackTrace();
        }

    }



}
