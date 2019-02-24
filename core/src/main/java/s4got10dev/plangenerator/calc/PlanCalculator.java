package s4got10dev.plangenerator.calc;

import s4got10dev.plangenerator.domain.LoanDetails;
import s4got10dev.plangenerator.domain.RepaymentPlan;
import s4got10dev.plangenerator.domain.RepaymentRecord;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static java.util.Optional.ofNullable;

public class PlanCalculator {

    private static final BigDecimal DAYS_IN_MONTH = valueOf(30L);
    private static final BigDecimal DAYS_IN_YEAR = valueOf(360L);


    public RepaymentPlan calculate(LoanDetails details) {
        RepaymentPlan plan = new RepaymentPlan();

        BigDecimal annuity = calculateAnnuity(details);
        RepaymentRecord record = plan.addRepayment(calculateRepaymentRecord(details, annuity, null));
        for (int i = 1; i < details.getDuration(); i++) {
            record = plan.addRepayment(calculateRepaymentRecord(details, annuity, record));
        }

        return plan;
    }


    BigDecimal calculateAnnuity(LoanDetails details) {
        BigDecimal rate = details.getNominalRate().divide(valueOf(1200), 8, HALF_UP);
        return details.getLoanAmount().multiply(rate)
                .divide(ONE.subtract(rate.add(ONE).pow(details.getDuration() * -1, new MathContext(8))), 8, HALF_UP);
    }

    RepaymentRecord calculateRepaymentRecord(LoanDetails details, BigDecimal annuity, RepaymentRecord previous) {
        Objects.requireNonNull(details);
        Objects.requireNonNull(annuity);

        RepaymentRecord record = new RepaymentRecord();

        record.setDate(ofNullable(previous)
                .map(RepaymentRecord::getDate)
                .map(localDate -> localDate.plusMonths(1))
                .orElse(details.getStartDate()));

        record.setInitialOP(ofNullable(previous)
                .map(RepaymentRecord::getRemainingOP)
                .orElse(details.getLoanAmount()));

        record.setInterest(details.getNominalRate()
                .multiply(DAYS_IN_MONTH)
                .multiply(record.getInitialOP())
                .divide(DAYS_IN_YEAR, 8, HALF_UP)
                .divide(valueOf(100L), 8, HALF_UP));

        record.setPrincipal(annuity.subtract(record.getInterest()).min(record.getInitialOP()));

        record.setAnnuity(record.getPrincipal().add(record.getInterest()));

        record.setRemainingOP(record.getInitialOP().subtract(record.getPrincipal()));

        return record;
    }


}
