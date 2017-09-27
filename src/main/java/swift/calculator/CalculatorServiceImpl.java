package swift.calculator;

import com.facebook.swift.codec.ThriftField;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import swift.calculator.service.CalculatorService;

/**
 * Created by mi on 17-9-27.
 */
public class CalculatorServiceImpl implements TCalculatorService {

    private CalculatorService calculatorService = new CalculatorService();

    @Override
    public int calculate(@ThriftField(value = 1, name = "num1", requiredness = ThriftField.Requiredness.NONE) int num1, @ThriftField(value = 2, name = "num2", requiredness = ThriftField.Requiredness.NONE) int num2, @ThriftField(value = 3, name = "op", requiredness = ThriftField.Requiredness.NONE) TOperation op)
            throws TDivisionByZeroException, TException {
        switch (op) {
            case ADD:
                return calculatorService.add(num1, num2);
            case SUB:
                return calculatorService.sub(num1, num2);
            case MUL:
                return calculatorService.mul(num1, num2);
            case DIV:
                try {
                    return calculatorService.dev(num1, num2);
                } catch (IllegalArgumentException e) {
                    throw new TDivisionByZeroException();
                }
            default:
                throw new TException("Unknown operation " + op);
        }
    }
}
