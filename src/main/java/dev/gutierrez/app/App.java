package dev.gutierrez.app;

import dev.gutierrez.daos.EmployeeDaoPostgres;
import dev.gutierrez.daos.ExpenseDaoLocal;
import dev.gutierrez.handlers.employee.*;
import dev.gutierrez.handlers.expense.*;
import dev.gutierrez.services.EmployeeService;
import dev.gutierrez.services.EmployeeServiceImpl;
import dev.gutierrez.services.ExpenseService;
import dev.gutierrez.services.ExpenseServiceImpl;
import io.javalin.Javalin;

public class App {

    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoPostgres());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDaoLocal());

    public static void main(String[] args) {
        Javalin app = Javalin.create();

        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();
        DeleteEmployeeHandler deleteEmployeeHandler = new DeleteEmployeeHandler();
        GetEmployeeHandler getEmployeeHandler = new GetEmployeeHandler();
        UpdateEmployeeHandler updateEmployeeHandler = new UpdateEmployeeHandler();
        AllEmployeeHandler allEmployeeHandler = new AllEmployeeHandler();

        app.post("/employee", createEmployeeHandler);
        app.get("/employee", allEmployeeHandler);
        app.get("/employee/{id}", getEmployeeHandler);
        app.delete("/employee/{id}", deleteEmployeeHandler);
        app.put("/employee/{id}", updateEmployeeHandler);


        AllExpenseHandler allExpenseHandler = new AllExpenseHandler();
        CreateExpenseHandler createExpenseHandler = new CreateExpenseHandler();
        DeleteExpenseHandler deleteExpenseHandler = new DeleteExpenseHandler();
        GetExpenseHandler getExpenseHandler = new GetExpenseHandler();
        UpdateExpenseHandler updateExpenseHandler = new UpdateExpenseHandler();

        app.post("/expense",createExpenseHandler );
        app.get("/expense", allExpenseHandler);
        app.get("/expense/{id}", getExpenseHandler);
        app.delete("/expense/{id}", deleteExpenseHandler);
        app.put("/expense/{id}", updateExpenseHandler);



        app.start();
    }

}
