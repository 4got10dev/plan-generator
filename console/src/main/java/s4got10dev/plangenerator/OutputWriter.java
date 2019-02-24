package s4got10dev.plangenerator;

import s4got10dev.plangenerator.domain.RepaymentPlan;

import static java.lang.System.out;

public class OutputWriter {

    void printRepaymentPlan(RepaymentPlan plan) {
        out.println("-----------------------------------------Repayment Plan:-----------------------------------------");
        out.printf("%12s %15s %15s %15s %15s %15s\n", "Date", "Annuity", "Principal", "Interest", "Initial", "Remaining");
        out.printf("%91s\n", "Outstanding Principal");
        out.println("-------------------------------------------------------------------------------------------------");
        plan.getRepayments().forEach(r -> out.printf("%12s %15.2f %15.2f %15.2f %15.2f %15.2f\n", r.getDate(),
                r.getAnnuity(), r.getPrincipal(), r.getInterest(), r.getInitialOP(), r.getRemainingOP()));
        out.println("-------------------------------------------------------------------------------------------------");
    }

}
