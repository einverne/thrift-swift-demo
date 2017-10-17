package info.einverne.thrift.swift;

import com.facebook.swift.codec.ThriftConstructor;
import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.codec.ThriftStruct;

/**
 * 用Thrift在Java中开发过的同学都了解，使用Thrift的IDL语言生成的Java文件是无比巨大和复杂的。
 * 很多时候对这样一种超大的实体和service定义都感到非常无力。增减方法，增减字段的文件替换都非常不方便。
 * Facebook/Swift 能够简化开发，github地址：https://github.com/facebook/swift
 * 这个框架的核心原理是通过定义和实现注解来达到简化Thrift文件的目的，使得在开发时定义的POJO和Interface都能够和普通的Java文件一致；
 */
@ThriftStruct
public class ThirdPartyCollection {
    public final long id;
    public final String date;

    @ThriftConstructor
    public ThirdPartyCollection(long id, String date) {
        this.id = id;
        this.date = date;
    }

    @ThriftField(1)
    public long getId() {
        return id;
    }

    @ThriftField(2)
    public String getDate() {
        return date;
    }
}
