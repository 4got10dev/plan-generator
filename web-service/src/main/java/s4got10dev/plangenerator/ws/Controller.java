package s4got10dev.plangenerator.ws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import s4got10dev.plangenerator.calc.PlanCalculator;
import s4got10dev.plangenerator.domain.LoanDetails;
import s4got10dev.plangenerator.domain.RepaymentPlan;
import s4got10dev.plangenerator.domain.RepaymentRecord;

import java.time.LocalDate;

import static spark.Spark.*;

public class Controller {

    public static void main(String[] args) {
        port(8787);
        Gson gson = getGson();
        Validator validator = new Validator();
        PlanCalculator planCalculator = new PlanCalculator();

        post("/generate-plan", (request, response) -> {
            LoanDetails details = gson.fromJson(request.body(), LoanDetails.class);
            validator.validate(details);
            RepaymentPlan plan = planCalculator.calculate(details);
            response.type("application/json");
            return gson.toJson(plan.getRepayments());
        });

        exception(ValidationException.class, (exception, request, response) -> {
            response.body(exception.getMessage());
        });
    }

    private static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) ->
                        LocalDate.parse(json.getAsJsonPrimitive().getAsString()))
                .registerTypeAdapter(RepaymentRecord.class, new RepaymentRecordSerializer())
                .setPrettyPrinting()
                .setDateFormat("dd.MM.yyy")
                .create();
    }
}
