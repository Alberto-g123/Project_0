package dev.gutierrez.handlers.expense;

import com.google.gson.Gson;
import dev.gutierrez.app.App;
import dev.gutierrez.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String json = ctx.body();
        Gson gson = new Gson();
        Expense expense = gson.fromJson(json, Expense.class);
        Expense createdExpense = App.expenseService.createExpense(expense);
        String expenseJson = gson.toJson(createdExpense);
        ctx.status(201);
        ctx.result(expenseJson);

    }
}
