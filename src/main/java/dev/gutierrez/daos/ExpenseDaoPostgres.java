package dev.gutierrez.daos;

import dev.gutierrez.entities.Expense;
import dev.gutierrez.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ExpenseDaoPostgres implements ExpenseDAO{
    @Override
    public Expense createExpense(Expense expense) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "insert into expense values(default,)";

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Expense getExpenseById(int id) {
        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return null;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return null;
    }

    @Override
    public boolean deleteExpenseById(int id) {
        return false;
    }
}
