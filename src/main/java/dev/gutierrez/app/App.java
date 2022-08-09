package dev.gutierrez.app;

import com.google.gson.Gson;
import dev.gutierrez.daos.EmployeeDaoLocal;
import dev.gutierrez.daos.EmployeeDaoPostgres;
import dev.gutierrez.daos.ExpenseDaoLocal;
import dev.gutierrez.daos.ExpenseDaoPostgres;
import dev.gutierrez.entities.Employee;
import dev.gutierrez.entities.Expense;
import dev.gutierrez.entities.Status;
import dev.gutierrez.handlers.employee.*;
import dev.gutierrez.handlers.expense.*;
import dev.gutierrez.services.EmployeeService;
import dev.gutierrez.services.EmployeeServiceImpl;
import dev.gutierrez.services.ExpenseService;
import dev.gutierrez.services.ExpenseServiceImpl;
import io.javalin.Javalin;
import io.javalin.http.Handler;


import java.util.ArrayList;
import java.util.List;


public class App {

    public static List<Expense> expense = new ArrayList();
    public static List<Employee> employee = new ArrayList();
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoPostgres());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDaoPostgres());

    public static void main(String[] args) {
        Javalin app = Javalin.create();

        CreateEmployeeHandler createEmployeeHandler = new CreateEmployeeHandler();
        DeleteEmployeeHandler deleteEmployeeHandler = new DeleteEmployeeHandler();
        GetEmployeeHandler getEmployeeHandler = new GetEmployeeHandler();
        UpdateEmployeeHandler updateEmployeeHandler = new UpdateEmployeeHandler();
        AllEmployeeHandler allEmployeeHandler = new AllEmployeeHandler();

        app.post("/employees", createEmployeeHandler);
        app.get("/employees", allEmployeeHandler);
        app.get("/employees/{id}", getEmployeeHandler);
        app.put("/employees/{id}", updateEmployeeHandler);
        app.delete("/employees/{id}", deleteEmployeeHandler);

        AllExpenseHandler allExpenseHandler = new AllExpenseHandler();
        CreateExpenseHandler createExpenseHandler = new CreateExpenseHandler();
        DeleteExpenseHandler deleteExpenseHandler = new DeleteExpenseHandler();
        GetExpenseHandler getExpenseHandler = new GetExpenseHandler();
        UpdateExpenseHandler updateExpenseHandler = new UpdateExpenseHandler();
        UpdateExpenseStatusHandler updateExpenseStatusHandler = new UpdateExpenseStatusHandler();
        GetExpenseFromEmployeeHandler getExpenseFromEmployeeHandler = new GetExpenseFromEmployeeHandler();



        app.post("/expenses",createExpenseHandler );
        app.get("/expenses", allExpenseHandler);
        //all expenses also does /expenses?status=pending
        app.get("/expenses/{id}", getExpenseHandler);
        app.put("/expenses/{id}", updateExpenseHandler);
        app.patch("/expenses/{id}/{status}", updateExpenseStatusHandler);
        app.delete("/expenses/{id}", deleteExpenseHandler);

        Handler createExpenseByEmployeeID = ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            if (App.employeeService.getEmployee(id) == null) {
                ctx.status(404);
                ctx.result("Employee ID not found");
            } else {
                String json = ctx.body();
                Gson gson = new Gson();
                Expense expense = gson.fromJson(json, Expense.class);
                expense.setId_empIssuer(id);
                Expense registerExpense = App.expenseService.createExpense(expense);
                String expenseJson = gson.toJson(registerExpense);
                ctx.status(201); //successful in creating new Expense
                ctx.result(expenseJson);
            }
        };



        app.get("/employees/{id}/expenses", getExpenseFromEmployeeHandler);
        app.post("employees/{id}/expenses", createExpenseByEmployeeID);


        app.start();
    }

}
