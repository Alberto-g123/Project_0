package dev.gutierrez.services;

import dev.gutierrez.entities.Expense;
import dev.gutierrez.entities.Status;

import java.util.List;


public interface ExpenseService {
    Expense createExpense(Expense expense);

    Expense getExpense(int id);
    List<Expense> getAllExpenses();
    List<Expense> getExpenseByEmployee(int id);

    String deleteExpense(int id);

    Expense editExpense(Expense expense);

    List<Expense> getExpenseStatus(Status status);

    Expense editExpenseStatus(int id, Status status);


}
