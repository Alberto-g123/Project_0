package dev.gutierrez.daos;

import dev.gutierrez.entities.Expense;
import dev.gutierrez.entities.Status;
import dev.gutierrez.entities.Type;
import dev.gutierrez.utils.ConnectionUtil;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDaoPostgres implements ExpenseDAO{
    @Override
    public Expense createExpense(Expense expense) {
        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "insert into expense values(default, ?, default, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, expense.getAmount());
            preparedStatement.setObject(2, expense.getType(), Types.OTHER);
            preparedStatement.setString(3,expense.getDescription());
            preparedStatement.setInt(4,expense.getId_empIssuer());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            int generatedKey = rs.getInt("expense_id");
            expense.setExpense_id(generatedKey);
            return expense;


        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Expense getExpenseById(int id) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from expense where expense_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Expense expense = new Expense();
            expense.setExpense_id(rs.getInt("expense_id"));
            expense.setAmount(rs.getDouble("amount"));
            expense.setStatus(Status.valueOf(rs.getString("status")));
            expense.setType(Type.valueOf(rs.getString("type")));
            expense.setDescription(rs.getString("description"));
            expense.setId_empIssuer(rs.getInt("id_empIssuer"));
            return expense;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Expense> getExpenseByEmployee(int id) {

        List<Expense> expenseList = new ArrayList();
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from expense where id_empIssuer = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Expense expense = new Expense();
                expense.setExpense_id(rs.getInt("expense_id"));
                expense.setId_empIssuer(rs.getInt("id_empIssuer"));

                if(expense.getId_empIssuer() == id){
                    expense.setAmount(rs.getDouble("amount"));
                    expense.setStatus(Status.valueOf(rs.getString("status")));
                    expense.setType(Type.valueOf(rs.getString("type")));
                    expense.setDescription(rs.getString("description"));
                    expenseList.add(expense);
                }
            }
            return expenseList;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Expense> getAllExpenses() {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from expense";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Expense> expenseList = new ArrayList();
            while(rs.next()){
                Expense expense = new Expense();
                expense.setExpense_id(rs.getInt("expense_id"));
                expense.setAmount(rs.getDouble("amount"));
                expense.setStatus(Status.valueOf(rs.getString("status")));
                expense.setType(Type.valueOf(rs.getString("type")));
                expense.setDescription(rs.getString("description"));
                expense.setId_empIssuer(rs.getInt("id_empIssuer"));

                expenseList.add(expense);
            }
            return expenseList;


        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public Expense updateExpense(Expense expense) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "update expense set amount = ?, status = CAST(? AS enum1), type = ?, description = ?, id_empIssuer = ? where expense_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, expense.getAmount());
            preparedStatement.setObject(2, expense.getStatus(), Types.OTHER);
            preparedStatement.setObject(3, expense.getType(), Types.OTHER);
            preparedStatement.setString(4,expense.getDescription());
            preparedStatement.setInt(5,expense.getId_empIssuer());
            preparedStatement.setInt(6, expense.getExpense_id());

            preparedStatement.executeUpdate();
            return expense;



        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String deleteExpenseById(int id) {
        try(Connection connection = ConnectionUtil.createConnection()){

            Expense expense = new Expense();
            expense = getExpenseById(id);
            System.out.println(expense);

            if (expense == null)
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
            else
            {

                String sql = "delete from expense where expense_id = ?";
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.setInt(1, id);
                ps.execute();
                return "success202";
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return "issue with delete";
        }


    }

    @Override
    public List<Expense> getExpenseStatus(Status status) {
        try(Connection connection = ConnectionUtil.createConnection()){

            String sql = "select * from expense where status = CAST(? AS enum1)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, status.toString());

            ResultSet rs = ps.executeQuery();

            List<Expense> expenseList = new ArrayList();
            while(rs.next()){
                Expense expense1 = new Expense();
                expense1.setExpense_id(rs.getInt("expense_id"));
                expense1.setAmount(rs.getDouble("amount"));
                expense1.setStatus(Status.valueOf(rs.getString("status")));
                expense1.setType(Type.valueOf(rs.getString("type")));
                expense1.setDescription(rs.getString("description"));
                expense1.setId_empIssuer(rs.getInt("id_empIssuer"));
                expenseList.add(expense1);
                }


            return expenseList;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense updateExpenseStatus(int id, Status status) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "update expense set status = CAST(? AS enum1) where expense_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, status.name());
            ps.setInt(2,id);
            ps.executeUpdate();
            return getExpenseById(id);


        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
