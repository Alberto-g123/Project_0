package dev.gutierrez.services;

import dev.gutierrez.doas.ExpenseDAO;
import dev.gutierrez.entities.Expense;

import java.util.Map;

public class ExpenseServiceImpl implements ExpenseService{
   private ExpenseDAO expenseDAO;

   public ExpenseServiceImpl(ExpenseDAO expenseDAO){
       this.expenseDAO = expenseDAO;
   }


    @Override
    public Expense createExpense(Expense expense) {
       Expense savedExpense = this.expenseDAO.createExpense(expense);
       if(expense.getStatus() == null){
           throw new RuntimeException("must have a status!");
       }else if(expense.getType() == null) {
           throw new RuntimeException("must have a type");
       } else if(expense.getDescription().length() == 0) {
           throw new RuntimeException(("please insert a description"));
       } else{
           return savedExpense;
       }
    }

    @Override
    public Expense getExpense(int id) {

        return this.expenseDAO.getExpenseById(id);
    }

    @Override
    public Map<Integer, Expense> getAllExpenses() {
        return this.expenseDAO.getAllExpenses();
    }

    @Override
    public boolean deleteExpense(int id) {
       boolean isTrue = this.expenseDAO.deleteExpenseById(id);
        return isTrue;
    }

    @Override
    public Expense editExpense(Expense expense) {
        if(expense.getStatus() == null){
            throw new RuntimeException("must have a status!");
        }else if(expense.getType() == null) {
            throw new RuntimeException("must have a type");
        } else if(expense.getDescription().length() == 0) {
            throw new RuntimeException(("please insert a description"));
        } else{
            return this.expenseDAO.updateExpense(expense);
        }

    }
}
