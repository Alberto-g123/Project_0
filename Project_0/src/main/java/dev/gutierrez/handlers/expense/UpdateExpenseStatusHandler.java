package dev.gutierrez.handlers.expense;

import com.google.gson.Gson;
import dev.gutierrez.app.App;
import dev.gutierrez.entities.Expense;
import dev.gutierrez.entities.Status;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateExpenseStatusHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense expense = App.expenseService.getExpense(id);
        if(expense == null){
            ctx.status(404);
            ctx.result("Could not find Expense request");
        } else if(expense.getStatus() != Status.PENDING){
            ctx.status(422);
            ctx.result("Status cannot be changed");
        } else{

            String string = ctx.pathParam("status");
            Gson gson = new Gson();

            Expense status = App.expenseService.editExpenseStatus(id, Status.valueOf(string.toUpperCase()));
            String json = gson.toJson(status);
            ctx.result(json);
        }
    }
}
