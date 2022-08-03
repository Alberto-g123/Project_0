package dev.gutierrez.services;

import dev.gutierrez.entities.Expense;

import java.util.Map;

public interface ExpenseService {
    Expense createExpense(Expense expense);

    Expense getExpense(int id);
    Map<Integer, Expense> getAllExpenses();

    boolean deleteExpense(int id);

    Expense editExpense(Expense expense);


}
