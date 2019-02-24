package s4got10dev.plangenerator.domain;

public enum Errors {
    LOAN_AMOUNT("Loan Amount should be should be positive value"),
    NOMINAL_INTEREST_RATE("Nominal Interest Rate should should be positive value that is less than 100 percent"),
    DURATION("Duration should be positive value");

    private String message;

    Errors(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
