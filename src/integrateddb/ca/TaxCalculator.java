package integrateddb.ca;

public class TaxCalculator {
    public static double calculateTax(Users user, double income) {
        String maritalStatus = user.getMarital_status();
        boolean hasChildren = user.isChildren();
        boolean bothWork =  user.isIf_married_both_work();

        double firstBracketLimit;
        double secondBracketRate;

        switch (maritalStatus) {
            case "single":
                firstBracketLimit = hasChildren ? 44000 : 40000;
                secondBracketRate = 0.4;
                break;
            case "married":
                firstBracketLimit = bothWork ? 80000 : 49000;
                secondBracketRate = 0.4;
                break;
            default:
                throw new IllegalArgumentException("Invalid marital status");
        }

        double tax = 0;

        if (income <= firstBracketLimit) {
            // 20% of income in the first bracket
            tax = 0.2 * income;
        } else {
            // 20% of the first bracket limit
            tax = 0.2 * firstBracketLimit;

            // 40% of the remaining income
            tax += 0.4 * (income - firstBracketLimit);
        }

        return tax;
    }
}

