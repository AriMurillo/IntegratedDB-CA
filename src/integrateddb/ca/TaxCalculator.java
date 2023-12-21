/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrateddb.ca;

/**
 *
 * @author Ari
 */
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

    //public static String getTaxInformation(Users user, double income){
    //    double calculatedTax = calculateTax(user, income);
    //    String taxInfo = "Tax Information for User " + user.getUsername() + ":\n" +
    //            "Income: $" + income + "\n" +
    //            "Calculated Tax: $" + calculatedTax + "\n";
    //    return taxInfo;
    //}


    public static double calculateUSC(double income) {
        double usc = 0;

        if (income <= 12012) {
            usc = 0.005 * income; // 0.5%
        } else if (income <= 22110) {
            usc = 0.02 * income; // 2% after 12012
        } else if (income <= 69234) {
            usc = 0.045 * income; // 4.5% after 22110
        } else {
            usc = 0.08 * income; // 8% after 69234
        }
        return usc;
    }

    public static double calculatePRSI(double income) {
        double weeklyIncome = income / 52;
        double prsi = 0;

        if (weeklyIncome > 352) {
            prsi = income * 0.04; // 4% of income
            return prsi;
        } else {
            return 0 ;
        }
    }
}

