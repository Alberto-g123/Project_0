package dev.gutierrez.handlers.employee;

import dev.gutierrez.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteEmployeeHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        String result = App.employeeService.deleteEmployee(id);
        System.out.println(result);
        if(result == "success204"){
            ctx.result("Employee has been removed");
            ctx.status(202);

        }else{
            ctx.status(404);
            ctx.result("Could not find employee");

        }

    }
}
