package info.einverne.thrift.swift;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;

import java.util.List;

/**
 * 使用注解方式定义接口 thrift IDL
 * 这种方式生成接口方式比使用 thrift -gen java 命令生成的文件来的简单很多
 *
 * http://sunwenfeng.blogspot.com/2014/08/facebookswiftjavathrift.html
 * https://github.com/facebook/swift
 */
@ThriftService
public interface ThirdPartyCollectionIface {
    @ThriftMethod
    public ThirdPartyCollection update(@ThriftField(name = "collection") ThirdPartyCollection collection);

    @ThriftMethod
    public List<ThirdPartyCollection> getAll();
}
