package s4got10dev.plangenerator.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static java.math.BigDecimal.ZERO;

public class LoanDetails {

    private BigDecimal loanAmount;
    private BigDecimal nominalRate;
    private int duration;
    private LocalDate startDate;

    public BigDecimal getLoanAmount() {
        return loanAmount != null ? loanAmount : ZERO;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getNominalRate() {
        return nominalRate != null ? nominalRate : ZERO;
    }

    public void setNominalRate(BigDecimal nominalRate) {
        this.nominalRate = nominalRate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanDetails that = (LoanDetails) o;
        return duration == that.duration &&
                Objects.equals(loanAmount, that.loanAmount) &&
                Objects.equals(nominalRate, that.nominalRate) &&
                Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanAmount, nominalRate, duration, startDate);
    }

}
