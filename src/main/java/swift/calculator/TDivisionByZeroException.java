package swift.calculator;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

@ThriftStruct("TDivisionByZeroException")
public final class TDivisionByZeroException extends Exception
{
    private static final long serialVersionUID = 1L;

    @ThriftConstructor
    public TDivisionByZeroException() {
    }

    public static class Builder {

        public Builder() { }
        public Builder(TDivisionByZeroException other) {
        }

        public TDivisionByZeroException build() {
            return new TDivisionByZeroException (
            );
        }
    }


}
