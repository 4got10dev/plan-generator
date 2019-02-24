package s4got10dev.plangenerator.calc;

import org.junit.jupiter.api.Test;
import s4got10dev.plangenerator.domain.RepaymentRecord;

import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static s4got10dev.plangenerator.calc.TestUtils.loanDetails;

class PlanCalculatorTest {

    @Test
    void testPlanCalculated() {
        List<RepaymentRecord> records = new ArrayList<>(new PlanCalculator().calculate(loanDetails(3000, 3, 4)).getRepayments());

        assertEquals(4, records.size());
        assertEquals(records.get(0).getDate().plusMonths(2), records.get(2).getDate());
        assertEquals(records.get(0).getAnnuity(), records.get(1).getAnnuity());
        assertEquals(ZERO, records.get(3).getRemainingOP().stripTrailingZeros());
    }
}
