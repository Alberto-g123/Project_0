package dev.gutierrez.daotests;


import dev.gutierrez.daos.EmployeeDAO;
import dev.gutierrez.daos.ExpenseDAO;
import dev.gutierrez.daos.ExpenseDaoPostgres;
import dev.gutierrez.daos.EmployeeDaoPostgres;
import dev.gutierrez.entities.Employee;
import dev.gutierrez.entities.Expense;
import dev.gutierrez.entities.Status;
import dev.gutierrez.entities.Type;
import dev.gutierrez.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDaoTests {

    static EmployeeDAO employeeDAO = new EmployeeDaoPostgres();
    static ExpenseDAO expenseDAO = new ExpenseDaoPostgres();

    @BeforeAll
    static void setup(){
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql1 = "create table employee(\n" +

                    "id serial primary key,\n" +
                    "fname varchar(50) not null,\n" +
                    "lname varchar(50) not null);";

            String sql2 = "create table expense(\n" +
                    "\n" +
                    "\texpense_id serial primary key,\n" +
                    "\tamount int,\n" +
                    "\tstatus enum1,\n" +
                    "\ttype enum2,\n" +
                    "\tdescription varchar(50),\n" +
                    "\tid_empIssuer int references employee(id));";

            Statement statement = connection.createStatement();
            statement.execute(sql1);
            statement.execute(sql2);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void create_expense_for_employee_test(){
        Employee employee1 = new Employee(0, "Alberto", "Gutierrez");
        Employee employee2 = new Employee(0, "Alex", "Great");
        Employee employee3 = new Employee(0, "Tee", "Hee");

        Expense expense1 = new Expense(0,400, Status.APPROVED, Type.FOOD,"Need food to survive", 1);
        Expense expense2 = new Expense(0,1200, Status.PENDING, Type.LODGING,"Need food to survive", 2);
        Expense expense3 = new Expense(0,100, Status.DENIED, Type.TRAVEL,"Need food to survive", 3);

        Employee savedEmployee = employeeDAO.createEmployee(employee1);
        Assertions.assertNotEquals(0, savedEmployee.getId());
        System.out.println(savedEmployee);
        employeeDAO.createEmployee(employee2);
        employeeDAO.createEmployee(employee3);

        Expense savedExpense = expenseDAO.createExpense(expense1);
        Assertions.assertNotEquals(0,savedExpense.getExpense_id());
        System.out.println(savedExpense);
        expenseDAO.createExpense(expense2);
        expenseDAO.createExpense(expense3);


    }

    @Test
    @Order(2)
    void get_expense_by_id_test(){
        Expense expense = expenseDAO.getExpenseById(1);
        Assertions.assertEquals(400, expense.getAmount());
        System.out.println(expense);
    }


    @Test
    @Order(3)
    void update_expense_test(){
        Expense expense = new Expense(3,50, Status.APPROVED, Type.LODGING,"Need food to survive", 2);
        expenseDAO.updateExpense(expense);
        Expense expense1 = expenseDAO.getExpenseById(3);
        Assertions.assertEquals(50, expense1.getAmount());
        System.out.println(expense1);
    }

    @Test
    @Order(4)
    void delete_expense_by_id_test(){
        String result = expenseDAO.deleteExpenseById(2);
        Assertions.assertEquals("success202", result);
    }

    @Test
    @Order(5)
    void get_all_expenses_test(){
        List<Expense> expenseList = expenseDAO.getAllExpenses();
        Assertions.assertEquals(2, expenseList.size());
        System.out.println(expenseList);
    }



    @AfterAll
    static void teardown(){
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql1 = "drop table employee";
            String sql2 = "drop table expense";
            Statement statement = connection.createStatement();
            statement.execute(sql2);
            statement.execute(sql1);

        }catch(SQLException e){
            e.printStackTrace();
        }

    }






}
