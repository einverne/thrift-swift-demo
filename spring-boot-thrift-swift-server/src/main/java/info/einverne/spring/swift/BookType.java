package info.einverne.spring.swift;

import com.facebook.swift.codec.*;

public enum BookType
{
    BOOK(0), NEWS_PAPER(1);

    private final int value;

    BookType(int value)
    {
        this.value = value;
    }

    @ThriftEnumValue
    public int getValue()
    {
        return value;
    }
}
