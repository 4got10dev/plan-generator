package s4got10dev.plangenerator.calc;

import org.junit.jupiter.api.Test;

import static java.math.RoundingMode.HALF_UP;
import static s4got10dev.plangenerator.calc.TestUtils.*;

class AnnuityCalculatorTest {

    @Test
    void testAnnuityAmountCalculated() {
        validateCalculation(219.36, 5000L, 5, 24);
        validateCalculation(308.77, 10000L, 7, 36);
        validateCalculation(837.85, 10000L, 1, 12);
        validateCalculation(25453.23, 300000L, 3.33, 12);
    }

    private static void validateCalculation(double expected, long amount, double rate, int duration) {
        assertNumberEquals(expected, new PlanCalculator().calculateAnnuity(loanDetails(amount, rate, duration)).setScale(2, HALF_UP));
    }


}
