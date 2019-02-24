package s4got10dev.plangenerator.ws;

import s4got10dev.plangenerator.domain.Errors;
import s4got10dev.plangenerator.domain.LoanDetails;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

class Validator {

    void validate(LoanDetails details) {
        if (details == null) {
            throw new ValidationException("Bad request. Request should be like:\n"
                    + "{\n\"loanAmount\": \"5000\",\n\"nominalRate\": \"5.0\"," +
                    "\n\"duration\": 24,\n\"startDate\": \"2020-01-01\"\n}");
        }
        if (details.getLoanAmount().compareTo(ZERO) <= 0) {
            throw new ValidationException(Errors.LOAN_AMOUNT.message());
        }
        if (details.getNominalRate().compareTo(ZERO) <= 0 || details.getNominalRate().compareTo(valueOf(100L)) >= 0) {
            throw new ValidationException(Errors.NOMINAL_INTEREST_RATE.message());
        }
        if (details.getDuration() <= 0) {
            throw new ValidationException(Errors.DURATION.message());
        }
    }
}
