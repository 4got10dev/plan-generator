package s4got10dev.plangenerator.domain;

import java.util.LinkedHashSet;
import java.util.Set;

public class RepaymentPlan {

    private Set<RepaymentRecord> repayments = new LinkedHashSet<>();

    public Set<RepaymentRecord> getRepayments() {
        return repayments;
    }

    public RepaymentRecord addRepayment(RepaymentRecord record) {
        repayments.add(record);
        return record;
    }
}
