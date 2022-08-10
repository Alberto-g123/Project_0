package dev.gutierrez.mockbasics;

import dev.gutierrez.daos.EmployeeDAO;
import dev.gutierrez.daos.ExpenseDAO;
import dev.gutierrez.entities.Employee;
import dev.gutierrez.entities.Expense;
import dev.gutierrez.entities.Status;
import dev.gutierrez.entities.Type;
import dev.gutierrez.services.EmployeeService;
import dev.gutierrez.services.EmployeeServiceImpl;
import dev.gutierrez.services.ExpenseService;
import dev.gutierrez.services.ExpenseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;

public class MockExampleTests {
    @Test
    void mock_1(){
        List<String> mockedList = Mockito.mock(List.class);
        Mockito.when(mockedList.get(99)).thenReturn("Bowser");
        System.out.println(mockedList.get(99));
    }

    @Test
    void register_employee_must_have_fname(){
        EmployeeDAO employeeDAO = Mockito.mock(EmployeeDAO.class);
        Employee employee = new Employee(0, "","Gutierrez");
        Mockito.when(employeeDAO.createEmployee(employee)).thenReturn(employee);
        EmployeeService employeeService = new EmployeeServiceImpl(employeeDAO);

        Assertions.assertThrows(RuntimeException.class, ()->{
            employeeService.createEmployee(employee);
        });
    }

    @Test
    void get_all_employees(){
        EmployeeDAO employeeDAO = Mockito.mock(EmployeeDAO.class);
        List<Employee> fakeEmployees = new ArrayList();
        fakeEmployees.add(new Employee(0,"Alberto","Gutierrez"));
        fakeEmployees.add(new Employee(0,"Alex","Great"));
        fakeEmployees.add(new Employee(0,"luis","Ramirez"));
        Mockito.when(employeeDAO.getAllEmployees()).thenReturn(fakeEmployees);
        EmployeeService employeeService = new EmployeeServiceImpl(employeeDAO);
        List<Employee> allEmployees = employeeService.getAllEmployees();
        Assertions.assertEquals(3,allEmployees.size());
    }

    @Test
    void get_all_expenses(){
        ExpenseDAO expenseDAO = Mockito.mock(ExpenseDAO.class);

        List<Expense> fakeExpense = new ArrayList();

        fakeExpense.add(new Expense(0,500, Status.PENDING, Type.FOOD,"need food", 1));
        fakeExpense.add(new Expense(0,500, Status.APPROVED, Type.LODGING,"hotel needed", 1));
        fakeExpense.add(new Expense(0,20, Status.APPROVED, Type.TRAVEL,"CAR WENT VROM", 1));
        Mockito.when(expenseDAO.getAllExpenses()).thenReturn(fakeExpense);

        ExpenseService expenseService = new ExpenseServiceImpl(expenseDAO);
        List<Expense> allExpenses = expenseService.getAllExpenses();
        Assertions.assertEquals(3,allExpenses.size());
    }


}
