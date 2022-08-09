package dev.gutierrez.handlers.expense;

import com.google.gson.Gson;
import dev.gutierrez.app.App;
import dev.gutierrez.entities.Expense;
import dev.gutierrez.entities.Status;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AllExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String status = ctx.queryParam("status");

        List<Expense> expense;
        if (status != null) {

            expense = App.expenseService.getExpenseStatus(Status.valueOf(status.toUpperCase()));

        } else {
            expense = App.expenseService.getAllExpenses();

        } Gson gson = new Gson();
        String json = gson.toJson(expense);
        ctx.result(json);
    }
}
