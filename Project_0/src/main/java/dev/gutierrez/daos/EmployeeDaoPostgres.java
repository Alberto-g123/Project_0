package dev.gutierrez.daos;

import dev.gutierrez.entities.Employee;
import dev.gutierrez.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoPostgres implements EmployeeDAO{


    @Override
    public Employee createEmployee(Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "insert into employee values (default, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getFname());
            preparedStatement.setString(2, employee.getLname());


            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            int generatedKey = rs.getInt("id");
            employee.setId(generatedKey);
            return employee;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee getEmployeeById(int id) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from employee where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFname(rs.getString("fname"));
            employee.setLname(rs.getString("lname"));


            return employee;

        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from employee";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Employee> employeeList = new ArrayList();
            while(rs.next()){
                Employee employee = new Employee();

                employee.setId(rs.getInt("id"));
                employee.setFname(rs.getString("fname"));
                employee.setLname(rs.getString("lname"));


                employeeList.add(employee);

            }
            return employeeList;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "update employee set fname = ?, lname = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1,employee.getFname());
            preparedStatement.setString(2,employee.getLname());
            preparedStatement.setInt(3,employee.getId());

            preparedStatement.executeUpdate();
            return employee;


        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String deleteEmployeeById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()){
            Employee employee =  new Employee();
            employee = getEmployeeById(id);

            System.out.println(employee);

            if (employee == null){
                System.out.println("Employee not found");
                return "error404";
            } else{

                String sql = "delete from employee where id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1,id);
                ps.execute();
                System.out.println("Employee deleted");
                return "success204";

            }


        }catch(SQLException e){
            e.printStackTrace();
            return "issue with delete";
        }
    }


}
