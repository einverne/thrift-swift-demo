package swift.calculator.service;

/**
 * CalculatorService
 */
public class CalculatorService {

    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public int sub(int num1, int num2) {
        return num1 - num2;
    }

    public int mul(int num1, int num2) {
        return num1 * num2;
    }

    public int dev(int num1, int num2) {
        if (num2 == 0) {
            throw new IllegalArgumentException("num2 must not be zero");
        }
        return num1 / num2;
    }
}
