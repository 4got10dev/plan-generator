package s4got10dev.plangenerator;

import s4got10dev.plangenerator.calc.PlanCalculator;

import static java.lang.System.out;

public class ConsoleApplication {

    public static void main(String[] args) {
        out.println("Welcome to Plan Generator!");

        new OutputWriter().printRepaymentPlan(new PlanCalculator().calculate(new InputReader().parseLoanDetails()));
    }

}
