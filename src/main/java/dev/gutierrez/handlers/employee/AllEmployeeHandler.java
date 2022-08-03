package dev.gutierrez.handlers.employee;

import com.google.gson.Gson;
import dev.gutierrez.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class AllEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(App.employeeService.getAllEmployees().values());
        ctx.result(json);
    }
}
