package dev.gutierrez.daos;

import dev.gutierrez.entities.Expense;

import java.util.List;


public interface ExpenseDAO {

    Expense createExpense(Expense expense);

    Expense getExpenseById(int id);

    List<Expense> getAllExpenses();

    Expense updateExpense(Expense expense);

    boolean deleteExpenseById(int id);
}
