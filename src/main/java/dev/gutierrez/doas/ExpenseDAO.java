package dev.gutierrez.doas;

import dev.gutierrez.entities.Expense;

import java.util.Map;

public interface ExpenseDAO {

    Expense createExpense(Expense expense);

    Expense getExpenseById(int id);

    Map<Integer, Expense> getAllExpenses();

    Expense updateExpense(Expense expense);

    boolean deleteExpenseById(int id);
}
