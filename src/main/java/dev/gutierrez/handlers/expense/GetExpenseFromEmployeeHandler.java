package dev.gutierrez.handlers.expense;

import com.google.gson.Gson;
import dev.gutierrez.app.App;
import dev.gutierrez.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetExpenseFromEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        List<Expense> expense = App.expenseService.getExpenseByEmployee(id);
        Gson gson = new Gson();
        String json = gson.toJson(expense);
        ctx.result(json);
    }
}
