package s4got10dev.plangenerator;

import s4got10dev.plangenerator.domain.Errors;
import s4got10dev.plangenerator.domain.LoanDetails;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

import static java.lang.System.out;
import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ofPattern;

class InputReader {

    LoanDetails parseLoanDetails() {
        Scanner scanner = new Scanner(System.in);
        out.println("Please provide Loan Details:");

        LoanDetails loanDetails = new LoanDetails();
        out.println("Loan Amount (#.##):");
        while (loanDetails.getLoanAmount().equals(ZERO)) {
            double loanAmount = scanner.nextDouble();
            if (loanAmount <= 0) {
                out.println(Errors.LOAN_AMOUNT.message());
            } else {
                loanDetails.setLoanAmount(valueOf(loanAmount));
            }
        }

        out.println("Nominal Interest Rate in percents (#.##): ");
        while (loanDetails.getNominalRate().equals(ZERO)) {
            double nominalRate = scanner.nextDouble();
            if (nominalRate <= 0 || nominalRate >= 100) {
                out.println(Errors.NOMINAL_INTEREST_RATE.message());
            } else {
                loanDetails.setNominalRate(valueOf(nominalRate));
            }
        }

        out.println("Duration - number of instalments in months (#):");
        while (loanDetails.getDuration() == 0) {
            int duration = scanner.nextInt();
            if (duration < 0) {
                out.println(Errors.DURATION.message());
            } else {
                loanDetails.setDuration(duration);
            }
        }

        out.println("Date of Disbursement/Payout (dd.mm.yyyy):");
        while (loanDetails.getStartDate() == null) {
            try {
                LocalDate date = parse(scanner.next(), ofPattern("dd.MM.yyyy"));
                loanDetails.setStartDate(date);
            } catch (DateTimeException e) {
                out.println("Date should be in format dd.mm.yyyy");
                out.println(e.getMessage());
            }
        }

        scanner.close();

        return loanDetails;
    }

}
