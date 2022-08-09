package dev.gutierrez.daos;


import dev.gutierrez.entities.Expense;
import dev.gutierrez.entities.Status;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseDaoLocal implements ExpenseDAO{

    private Map<Integer, Expense> expenseTable = new HashMap();

    private int idMaker = 1;

    @Override
    public Expense createExpense(Expense expense) {
        expense.setExpense_id(idMaker);
        idMaker++;
        expenseTable.put(expense.getExpense_id(), expense);
        return expense;
    }

    @Override
    public Expense getExpenseById(int id) {
        return expenseTable.get(id);
    }

    @Override
    public List<Expense> getExpenseByEmployee(int id) {

        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {

        return null;

    }

    @Override
    public Expense updateExpense(Expense expense) {
        expenseTable.put(expense.getExpense_id(),expense);
        return expense;
    }

    @Override
    public String deleteExpenseById(int id) {
        Expense expense = expenseTable.get(id);
        if (expenseTable.get(id).equals(null))
        {
            // throw some error
            System.out.println("expense not found");

            return "error404";
        }

        if (expense.getStatus() == Status.APPROVED || expense.getStatus() == Status.DENIED)
        {
            System.out.println("Cannot delete Expense status");
            return "error422";
        }

        expense = expenseTable.remove(id);

        return "success202";
    }

    @Override
    public List<Expense> getExpenseStatus(Status status){
        return null;
    }

    @Override
    public Expense updateExpenseStatus(int id, Status status) {
        return null;
    }


}

