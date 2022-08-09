package dev.gutierrez.handlers.expense;

import dev.gutierrez.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        String result = App.expenseService.deleteExpense(id);
        if (result == "success202")
        {
            ctx.result("The expense has been deleted");
            ctx.status(202);
        }
        else if (result == "error404")
        {
            ctx.result("Expense not found");
            ctx.status(404);
        }
        else if (result == "error422")
        {
            ctx.status(422);
            ctx.result("Expense is already approved or denied");
        }
        else
        {
            throw new RuntimeException("");
        }

    }
}
