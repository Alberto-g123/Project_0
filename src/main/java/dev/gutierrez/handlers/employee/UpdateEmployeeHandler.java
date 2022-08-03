package dev.gutierrez.handlers.employee;

import com.google.gson.Gson;
import dev.gutierrez.app.App;
import dev.gutierrez.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Employee employee = App.employeeService.getEmployee(id);

        if(employee != null){
            String employeeJson = ctx.body();
            Gson gson = new Gson();
            Employee employee2 = gson.fromJson(employeeJson,Employee.class);
            employee2.setId(id);
            Employee updatedEmployee = App.employeeService.editEmployee(employee2);
            String json = gson.toJson(updatedEmployee);
            ctx.result(json);
        }else{
            ctx.status(404);
            ctx.result("employee not found");
        }

    }
}
