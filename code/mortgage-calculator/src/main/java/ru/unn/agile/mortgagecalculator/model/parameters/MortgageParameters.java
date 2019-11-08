package ru.unn.agile.mortgagecalculator.model.parameters;

import ru.unn.agile.mortgagecalculator.model.parameters.commission.Commission;
import ru.unn.agile.mortgagecalculator.model.parameters.commission.FixedCommission;
import ru.unn.agile.mortgagecalculator.model.parameters.monthlycommission.MonthlyCommission;
import ru.unn.agile.mortgagecalculator.model.validation.Validator;

public class MortgageParameters {

    private final int MONTHS_IN_YEAR = 12;

    private double amount;
    private Percent percent;
    private int monthsPeriod;
    private double initialPayment;
    private Commission commission;
    private MonthlyCommission monthlyCommission;

    private Validator validator;

    public MortgageParameters(double amount, double percent, PeriodType periodType, int period) {
        validator = new Validator();
        validate(amount, percent, period);
        this.amount = amount;
        this.percent = new Percent(percent);
        this.monthsPeriod = getMonths(periodType, period);
    }

    public MortgageParameters(double amount, double percent, int period) {
        this(amount, percent, PeriodType.MONTH, period);
    }

    public void setInitialPayment(double initialPayment) {
        validator.checkPositiveDouble(initialPayment);
        validator.checkCorrectInitialPayment(initialPayment, amount);
        this.initialPayment = initialPayment;
        amount -= initialPayment;
    }

    public double getAmount() {
        return amount;
    }

    public double getFractionPercent() {
        return percent.getPercent();
    }

    public double getMonthPercent() {
        return percent.getPercent() / MONTHS_IN_YEAR;
    }

    public int getMonthsPeriod() {
        return monthsPeriod;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }

    public Commission getCommission() {
        return commission;
    }

    public void setMonthlyCommission(MonthlyCommission monthlyCommission) {
        this.monthlyCommission = monthlyCommission;
    }

    public MonthlyCommission getMonthlyCommission() {
        return monthlyCommission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MortgageParameters that = (MortgageParameters) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        if (monthsPeriod != that.monthsPeriod) return false;
        return percent.equals(that.percent);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(amount);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + percent.hashCode();
        result = 31 * result + monthsPeriod;
        return result;
    }

    private void validate(double amount, double percent, int period) {
        Validator validator = new Validator();
        validator.checkPositiveDouble(amount);
        validator.checkCorrectPercent(percent);
        validator.checkPositiveInteger(period);
    }

    private int getMonths(PeriodType periodType, int period) {
        if (PeriodType.MONTH.equals(periodType)) {
            return period;
        } else {
            return period * MONTHS_IN_YEAR;
        }
    }

}
