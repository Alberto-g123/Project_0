package dev.gutierrez.services;

import dev.gutierrez.daos.ExpenseDAO;
import dev.gutierrez.entities.Expense;
import dev.gutierrez.entities.Status;

import java.util.ArrayList;
import java.util.List;


public class ExpenseServiceImpl implements ExpenseService{
   private ExpenseDAO expenseDAO;

   public ExpenseServiceImpl(ExpenseDAO expenseDAO){
       this.expenseDAO = expenseDAO;
   }


    @Override
    public Expense createExpense(Expense expense) {

       if(expense.getStatus() == null){
           throw new RuntimeException("must have a status!");
       }else if(expense.getType() == null) {
           throw new RuntimeException("must have a type");
       } else if(expense.getDescription().length() == 0) {
           throw new RuntimeException(("please insert a description"));
       } else if(expense.getAmount() < 0) {
           throw new RuntimeException("amount cannot be less than 0");
       }else {
           Expense savedExpense = this.expenseDAO.createExpense(expense);
           return savedExpense;
       }

    }

    @Override
    public Expense getExpense(int id) {

        return this.expenseDAO.getExpenseById(id);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return this.expenseDAO.getAllExpenses();

    }

    @Override
    public List<Expense> getExpenseByEmployee(int id) {
        return this.expenseDAO.getExpenseByEmployee(id);
    }

    @Override
    public String deleteExpense(int id) {
       String isTrue = this.expenseDAO.deleteExpenseById(id);
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
        } else if(expense.getAmount() < 0) {
            throw new RuntimeException("amount cannot be less than 0");
        }else {
            return this.expenseDAO.updateExpense(expense);
        }

    }

    @Override
    public List<Expense> getExpenseStatus(Status status){
//       List<Expense> expenses = this.getAllExpenses();
//       List<Expense> expenseList = new ArrayList();
//       for(Expense expense : expenses){
//           if(expense.getStatus().equals(status)){
//               expenseList.add(expense);
//           }
//       }
       return this.expenseDAO.getExpenseStatus(status);
    }

    @Override
    public Expense editExpenseStatus(int id, Status status) {
        Expense expense = this.expenseDAO.getExpenseById(id);
        if(expense.getStatus().equals(Status.APPROVED)){
            throw new RuntimeException("Expense has already been approved");
        }else if(expense.getStatus().equals(Status.DENIED)){
            throw new RuntimeException("Expense has been denied");
        }
        expense.setStatus(status);
        this.expenseDAO.updateExpense(expense);
        return expense;
    }

}
