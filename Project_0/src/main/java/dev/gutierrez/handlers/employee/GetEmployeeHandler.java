package dev.gutierrez.handlers.employee;

import com.google.gson.Gson;
import dev.gutierrez.app.App;
import dev.gutierrez.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Employee employee = App.employeeService.getEmployee(id);
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        if(employee != null){
            ctx.result(json);
        }else{
            ctx.status(404);
            ctx.result("employee not found");
        }
    }
}
