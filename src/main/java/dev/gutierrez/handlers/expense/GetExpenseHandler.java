package dev.gutierrez.handlers.expense;

import com.google.gson.Gson;
import dev.gutierrez.app.App;
import dev.gutierrez.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense expense = App.expenseService.getExpense(id);
        Gson gson = new Gson();
        String json = gson.toJson(expense);
        if(expense != null){
            ctx.result(json);
        }else {
            ctx.status(404);
            ctx.result("expense not found");
        }
    }
}
