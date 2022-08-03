package dev.gutierrez.handlers.expense;

import dev.gutierrez.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = App.expenseService.deleteExpense(id);
        if(result){
            ctx.status(204);
        } else{
            ctx.status(404);
            ctx.result("no expense found");
        }

    }
}
