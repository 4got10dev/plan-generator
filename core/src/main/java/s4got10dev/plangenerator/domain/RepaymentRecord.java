package s4got10dev.plangenerator.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class RepaymentRecord {

    private LocalDate date;
    private BigDecimal annuity;
    private BigDecimal principal;
    private BigDecimal interest;
    private BigDecimal initialOP;
    private BigDecimal remainingOP;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAnnuity() {
        return annuity;
    }

    public void setAnnuity(BigDecimal annuity) {
        this.annuity = annuity;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getInitialOP() {
        return initialOP;
    }

    public void setInitialOP(BigDecimal initialOP) {
        this.initialOP = initialOP;
    }

    public BigDecimal getRemainingOP() {
        return remainingOP;
    }

    public void setRemainingOP(BigDecimal remainingOP) {
        this.remainingOP = remainingOP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepaymentRecord that = (RepaymentRecord) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(annuity, that.annuity) &&
                Objects.equals(principal, that.principal) &&
                Objects.equals(interest, that.interest) &&
                Objects.equals(initialOP, that.initialOP) &&
                Objects.equals(remainingOP, that.remainingOP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, annuity, principal, interest, initialOP, remainingOP);
    }

}
