package swift.calculator;

import com.facebook.swift.codec.*;

public enum TOperation
{
    ADD(1), SUB(2), MUL(3), DIV(4);

    private final int value;

    TOperation(int value)
    {
        this.value = value;
    }

    @ThriftEnumValue
    public int getValue()
    {
        return value;
    }
}
