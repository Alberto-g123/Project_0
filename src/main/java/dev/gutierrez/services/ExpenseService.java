package dev.gutierrez.services;

import dev.gutierrez.entities.Expense;

import java.util.List;


public interface ExpenseService {
    Expense createExpense(Expense expense);

    Expense getExpense(int id);
    List<Expense> getAllExpenses();

    boolean deleteExpense(int id);

    Expense editExpense(Expense expense);


}
