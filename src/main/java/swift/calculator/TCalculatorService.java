package swift.calculator;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.ThriftException;
import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;
import com.google.common.util.concurrent.ListenableFuture;
import org.apache.thrift.TException;

@ThriftService("TCalculatorService")
public interface TCalculatorService {
    @ThriftMethod(value = "calculate",
            exception = {
                    @ThriftException(type = TDivisionByZeroException.class, id = 1)
            })
    int calculate(
            @ThriftField(value = 1, name = "num1", requiredness = Requiredness.NONE) final int num1,
            @ThriftField(value = 2, name = "num2", requiredness = Requiredness.NONE) final int num2,
            @ThriftField(value = 3, name = "op", requiredness = Requiredness.NONE) final TOperation op
    ) throws TDivisionByZeroException, TException;

    @ThriftService("TCalculatorService")
    public interface Async {
        @ThriftMethod(value = "calculate",
                exception = {
                        @ThriftException(type = TDivisionByZeroException.class, id = 1)
                })
        ListenableFuture<Integer> calculate(
                @ThriftField(value = 1, name = "num1", requiredness = Requiredness.NONE) final int num1,
                @ThriftField(value = 2, name = "num2", requiredness = Requiredness.NONE) final int num2,
                @ThriftField(value = 3, name = "op", requiredness = Requiredness.NONE) final TOperation op
        );
    }
}