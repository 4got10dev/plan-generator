package s4got10dev.plangenerator.calc;

import s4got10dev.plangenerator.domain.LoanDetails;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestUtils {

    static LoanDetails loanDetails(long amount, double rate, int duration) {
        LoanDetails loanDetails = new LoanDetails();
        loanDetails.setLoanAmount(valueOf(amount));
        loanDetails.setNominalRate(valueOf(rate));
        loanDetails.setDuration(duration);
        loanDetails.setStartDate(LocalDate.now());
        return loanDetails;
    }

    static void assertNumberEquals(double expected, BigDecimal actual) {
        assertNumberEquals(valueOf(expected), actual.setScale(2, HALF_UP));
    }

    static void assertNumberEquals(BigDecimal expected, BigDecimal actual) {
        assertEquals(expected.stripTrailingZeros(), actual.stripTrailingZeros());
    }

}
