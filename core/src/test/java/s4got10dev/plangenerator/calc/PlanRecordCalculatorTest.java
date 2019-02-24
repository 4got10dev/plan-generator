package s4got10dev.plangenerator.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import s4got10dev.plangenerator.domain.LoanDetails;
import s4got10dev.plangenerator.domain.RepaymentRecord;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static s4got10dev.plangenerator.calc.TestUtils.assertNumberEquals;
import static s4got10dev.plangenerator.calc.TestUtils.loanDetails;

class PlanRecordCalculatorTest {

    private PlanCalculator planCalculator;

    @BeforeEach
    void setUp() {
        if (planCalculator == null)
            planCalculator = new PlanCalculator();
    }

    @Test
    void testInitialRecordCalculated() {
        LoanDetails loanDetails = loanDetails(5000, 5, 24);
        RepaymentRecord record = planCalculator.calculateRepaymentRecord(loanDetails, planCalculator.calculateAnnuity(loanDetails), null);
        checkRepaymentRecord(record, 219.36, 198.52, 20.83);
    }

    @Test
    void testSecondRecordCalculated() {
        LoanDetails loanDetails = loanDetails(3000, 3, 2);
        BigDecimal annuity = planCalculator.calculateAnnuity(loanDetails);
        RepaymentRecord initial = planCalculator.calculateRepaymentRecord(loanDetails, annuity, null);
        RepaymentRecord lastOne = planCalculator.calculateRepaymentRecord(loanDetails, annuity, initial);
        checkRepaymentRecord(initial, 1505.63, 1498.13, 7.5);
        checkRepaymentRecord(lastOne, 1505.63, 1501.87, 3.75);
        assertEquals(initial.getDate().plusMonths(1L), lastOne.getDate());
    }

    private void checkRepaymentRecord(RepaymentRecord actual, double annuity, double principal, double interest) {
        assertNumberEquals(annuity, actual.getAnnuity());
        assertNumberEquals(principal, actual.getPrincipal());
        assertNumberEquals(interest, actual.getInterest());
        assertNumberEquals(actual.getAnnuity(), actual.getInterest().add(actual.getPrincipal()));
        assertNumberEquals(actual.getInitialOP(), actual.getRemainingOP().add(actual.getPrincipal()));
    }

}
