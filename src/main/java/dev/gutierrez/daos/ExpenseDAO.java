package dev.gutierrez.daos;

import dev.gutierrez.entities.Expense;
import dev.gutierrez.entities.Status;

import java.util.List;


public interface ExpenseDAO {

    Expense createExpense(Expense expense);

    Expense getExpenseById(int id);

    List<Expense> getExpenseByEmployee(int id);

    List<Expense> getAllExpenses();

    Expense updateExpense(Expense expense);

    String deleteExpenseById(int id);

    List<Expense> getExpenseStatus(Status status);
    Expense updateExpenseStatus(int id, Status status);
}
