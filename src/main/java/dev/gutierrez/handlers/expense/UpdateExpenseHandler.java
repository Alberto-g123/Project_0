package dev.gutierrez.handlers.expense;

import com.google.gson.Gson;
import dev.gutierrez.app.App;
import dev.gutierrez.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Expense expense = App.expenseService.getExpense(id);

        if(expense != null){
            String expenseJson = ctx.body();
            Gson gson = new Gson();
            Expense expense2 = gson.fromJson(expenseJson, Expense.class);
            expense2.setExpense_id(id);
            Expense updatedExpense = App.expenseService.editExpense(expense2);
            String json = gson.toJson(updatedExpense);
            ctx.result(json);
        } else{
            ctx.status(404);
            ctx.result("expense ID does not exist");

        }


    }
}
