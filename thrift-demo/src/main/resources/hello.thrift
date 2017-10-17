namespace java info.einverne.thrift  # thrift 命名空间 相当于 Java Package，主要用于组织代码

// 结构体定义，转换为 Java 中的实体
struct Request {
1:required string name;
2:required string password;
}

// 返回类型
struct User {
1:required string name;
2:required i32 age;
}

/*
struct <结构体名称> {
    <序号>:[字段性质] <字段类型> <字段名称> [= <默认值>] [;|,]
}
·结构体名称：可以按照业务需求，给定不同的名称（区分大小写）。但是要注意，一组IDL定义文件中结构体名称不能重复，且不能使用IDL已经占用的关键字（例如required 、struct 等单词）。

·序号：序号非常重要。正整数，按照顺序排列使用。这个属性在Apache Thrift进行序列化的时候被使用。

·字段性质：包括两种关键字：required 和 optional，如果您不指定，那么系统会默认为required。required表示这个字段必须有值，并且Apache Thrift在进行序列化时，这个字段都会被序列化；optional表示这个字段不一定有值，且Apache Thrift在进行序列化时，这个字段只有有值的情况下才会被序列化。

·字段类型：在struct中，字段类型可以是某一个基础类型，也可以是某一个之前定义好的struct，还可以是某种Apache Thrift支持的容器（set、map、list），还可以是定义好的枚举。字段的类型是必须指定的。

·字段名称：字段名称区分大小写，不能重复，且不能使用IDL已经占用的关键字（例如required 、struct 等单词）。

·默认值：您可以为某一个字段指定默认值（也可以不指定）。

·结束符：在struct中，支持两种结束符，您可以使用“；”或者“,”。当然您也可以不使用结束符（Apache Thrift代码生成程序，会自己识别到）
 */

// 服务定义，用于生成接口
service HelloService {
    string hello(1: string name);
}

/*
enum <枚举名称> {
        <枚举字段名> = <枚举值>[;|,]
}
 */

//IDL允许定义常量。常量的关键字为“const”,与C语言类似，常量可以是基础类型，也可以是定义的Struct。
const i32 MAX_AREA = 60

//异常描述定义
exception HelloException{
1:required string msg;
}

/*
exception <异常名称> {
    <序号>:[字段性质] <字段类型> <字段名称> [= <默认值>] [;|,]
}
 */

service UserService {
    void ping();
    list<User> getAllStudent(1:Request request) throws (1:HelloException e);
}

/*
service <服务名称> {
    <void | 返回指类型> <服务方法名>([<入参序号>:[required | optional] <参数类型> <参数名> ...]) [throws ([<异常序号>:[required | optional] <异常类型> <异常参数名>...])]
}
·服务名称：服务名可以按照您的业务需求自行制定，注意服务名是区分大小写的。IDL中服务名称只有两个限制，就是不能重复使用相同的名称，不能使用IDL已经占用的关键字（例如required 、struct 等单词）。

·返回值类型：如果这个调用方法没有返回类型，那么可以关键字“void”; 可以是Apache Thrift的基础类型，也可以是某一个之前定义好的struct，还可以是某种Apache Thrift支持的容器（set、map、list），还可以是定义好的枚举。

·服务方法名：服务方法名可以根据您的业务需求自定制定，注意区分大小写。在同一个服务中，不能重复使用一个服务方法名命名多个方法（一定要注意），不能使用IDL已经占用的关键字。

·服务方法参数：<入参序号>:[required | optional] <参数类型> <参数名>。注意和struct中的字段定义相似，可以指定required或者optional；如果不指定则系统默认为required 。如果一个服务方法中有多个参数名，那么这些参数名称不能重复。

·服务方法异常：throws ([<异常序号>:[required | optional] <异常类型> <异常参数名>。throws关键字是服务方法异常定义的开始点。在throws关键字后面，可以定义1个或者多个不同的异常类型。

from: http://blog.csdn.net/peace1213/article/details/53785215
 */