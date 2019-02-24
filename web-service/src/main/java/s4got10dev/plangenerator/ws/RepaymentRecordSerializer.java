package s4got10dev.plangenerator.ws;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import s4got10dev.plangenerator.domain.RepaymentRecord;

import java.lang.reflect.Type;

import static java.lang.String.format;

class RepaymentRecordSerializer implements JsonSerializer<RepaymentRecord> {

    @Override
    public JsonElement serialize(RepaymentRecord record, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        object.addProperty("date", record.getDate().toString());

        object.addProperty("borrowerPaymentAmount", format("%.2f", record.getAnnuity()));
        object.addProperty("principal", format("%.2f", record.getPrincipal()));
        object.addProperty("interest", format("%.2f", record.getInterest()));

        object.addProperty("initialOutstandingPrincipal", format("%.2f", record.getInitialOP()));
        object.addProperty("remainingOutstandingPrincipal", format("%.2f", record.getRemainingOP()));

        return object;
    }
}
