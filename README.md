Apache Thrift 是一个跨语言的RPC框架, 源于Facebook, 目前已经作为开源项目提交给了Apache 软件基金会。Thrift解决了Facebook各系统的大数据量传输通信和内部不同语言环境的跨平台调用。 它结合了功能强大的软件堆栈和代码生成引擎，以构建在 C++, Java, Go,Python, PHP, Ruby, Erlang, Perl, Haskell, C#, Cocoa, JavaScript, Node.js, Smalltalk, and OCaml 这些编程语言间无缝结合的、高效的服务。

RPC，全称 Remote Procedure Call（远程过程调用），即调用远程计算机上的服务，就像调用本地服务一样。

Thrift是一种接口描述语言和二进制通讯协议，它被用来定义和创建跨语言的服务。它被当作一个远程过程调用（RPC）框架来使用，是由Facebook为“大规模跨语言服务开发”而开发的。它通过一个代码生成引擎联合了一个软件栈，来创建不同程度的、无缝的跨平台高效服务，可以使用C#、C++（基于POSIX兼容系统）、Cappuccino、Cocoa、Delphi、Erlang、Go、Haskell、Java、Node.js、OCaml、Perl、PHP、Python、Ruby和Smalltalk。该实现被描述在2007年4月的一篇由Facebook发表的技术论文中，该论文现由Apache掌管。

Thrift的官方网站: <http://thrift.apache.org/>

作为一个高性能的RPC框架，Thrift的主要特点有:

1. 基于二进制的高性能的编解码框架
2. 基于NIO的底层通信
3. 相对简单的服务调用模型
4. 使用IDL支持跨平台调用

Thrift的核心组件有：

1. TProtocol 协议和编解码组件
2. TTransport 传输组件
3. TProcessor 服务调用组件
4. TServer，Client 服务器和客户端组件
5. IDL 服务描述组件，负责生产跨平台客户端

## 安装

安装Thrift主要是为了通过IDL文件生成需要的接口和类。 

下载地址： <https://thrift.apache.org/download>

C++ 如果需要支持 C++ 需要安装依赖

Pull in the main thrift dependencies (taken from http://thrift.apache.org/docs/install/ubuntu/ ):

    sudo apt install libboost-dev libboost-test-dev libboost-program-options-dev libevent-dev automake libtool flex bison pkg-config g++ libssl-dev

Java 如果需要支持 Java 需要安装 ant

    sudo apt-get install ant

Python 如果需要支持 Python 需要安装如下依赖

    sudo apt-get install python-all python-all-dev python-all-dbg
   
安装：

    wget http://mirror.fibergrid.in/apache/thrift/0.10.0/thrift-0.10.0.tar.gz
    tar -xvf thrift-0.10.0.tar.gz 
    cd thrift-0.10.0
    ./configure
    make #有点慢 
    sudo make install
    thrift -version
    
安装时遇到问题：

    BUILD FAILED
    /home/ev/thrift/thrift-0.10.0/lib/java/build.xml:99: Unable to find a javac compiler;
    com.sun.tools.javac.Main is not on the classpath.
    Perhaps JAVA_HOME does not point to the JDK.
    It is currently set to "/usr/lib/jvm/java-8-openjdk-amd64/jre"

在安装的过程中遇到thrift找不到 `JAVA_HOME` 的错误，而明明我的 `JAVA_HOME` 是配置了的，想到当时我安装 Java 时手动安装，手动配置的目录，可能系统找到了 openjdk 的目录，而没有知道我安装的 Oracle JDK，在查询一番之后在[官网](https://thrift.apache.org/docs/BuildingFromSource) 找到方法，在 configure 时指定 JAVAC 地址 `JAVAC=/usr/local/jdk1.8.0_131/bin/javac` 。指定 JAVAC 地址之后依然 install 不行，所以按照[这个教程](http://einverne.github.io/post/2017/05/usr-local-vs-opt.html) 重新安装了 JDK，然后OK。

## 基本语法
Thrift类型系统包括预定义基本类型，用户自定义结构体，容器类型，异常和服务定义

### 基本类型
thrift 支持的基本类型

    bool：布尔类型(true or value)，占一个字节
    byte：有符号字节
    i16: 16位有符号整型
    i32: 32位有符号整型
    i64: 64位有符号整型
    double：64位浮点数
    string：未知编码或者二进制的字符串
    binary: 二进制数据

注意，thrift不支持无符号整型，因为很多目标语言不存在无符号整型（如java）。

### 容器类型

Thrift提供了3种容器类型：

    List<T>：一系列T类型的元素组成的有序表，元素可以重复
    Set<T>：一系列T类型的元素组成的无序表，元素不可以重复
    Map<t1,t2>：key/value对（key的类型是t1且key唯一，value类型是t2）。

容器中的元素类型可以是除了service意外的任何合法thrift类型（包括结构体和异常）。

### 结构体和异常

Thrift结构体在概念上同C语言结构体类型----一种将相关属性聚集（封装）在一起的方式。在面向对象语言中，thrift结构体被转换成类。在弱类型语言、动态语言中，表现为“结构/结构体”。

异常在语法和功能上类似于结构体，只不过异常使用关键字exception而不是struct关键字声明。但它在语义上不同于结构体----当定义一个RPC服务时，开发者可能需要声明一个远程方法抛出一个异常。

### 服务
服务的定义方法在语法上等同于面向对象语言中定义接口。

这部分可以在 Demo 中 hello.thrift 中看到

## IDL 定义

使用 thrift 命令生成 IDL

    thrift -r --gen java src/main/resources/hello.thrift


## 协议
协议和编解码是一个网络应用程序的核心问题之一，客户端和服务器通过约定的协议来传输消息(数据)，通过特定的格式来编解码字节流，并转化成业务消息，提供给上层框架调用。

Thrift 把协议和编解码整合在一起。抽象类TProtocol定义了协议和编解码的顶层接口。TProtocol关联了一个TTransport传输对象，而不是提供一个类似getTransport()的接口。

TProtocol主要做了两个事情:

1. 关联TTransport对象
2. 定义一系列读写消息的编解码接口，包括两类，一类是复杂数据结构比如readMessageBegin, readMessageEnd,  writeMessageBegin, writMessageEnd.还有一类是基本数据结构，比如readI32, writeI32, readString, writeString

所谓协议就是客户端和服务器端约定传输什么数据，如何解析传输的数据。对于一个RPC调用的协议来说，要传输的数据主要有:

调用方

1. 方法的名称，包括类的名称和方法的名称
2. 方法的参数，包括类型和参数值
3. 一些附加的数据，比如附件，超时事件，自定义的控制信息等等

返回方

1. 调用的返回码
2. 返回值
3. 异常信息

TProtocol定义了基本的协议信息，将内存中数据映射成可以传输的机制，包括传输什么数据，如何解析传输的数据的基本方法。

Transport层提供了一个简单的网络读写抽象层。

Server将以上所有特性集成在一起：

1. 创建一个transport对象
2. 为transport对象创建输入输出protocol
3. 基于输入输出protocol创建processor
4. 等待连接请求并将之交给processor处理

thrift文件内容可能会随着时间变化的。如果已经存在的消息类型不再符合设计要求，比如，新的设计要在message格式中添加一个额外字段，但你仍想使用以前的thrift文件产生的处理代码。如果想要达到这个目的，只需：

- 不要修改已存在域的整数编号
- 新添加的域必须是optional的，以便格式兼容。对于一些语言，如果要为optional的字段赋值，需要特殊处理
- 非required域可以删除，前提是它的整数编号不会被其他域使用。对于删除的字段，名字前面可添加“OBSOLETE_”以防止其他字段使用它的整数编号
- thrift文件应该是unix格式的（windows下的换行符与unix不同，可能会导致你的程序编译不过），如果是在window下编写的，可使用dos2unix转化为unix格式。






## swift-generator-cli 使用

swift-generator-cli 是 Facebook swift 项目中的一个子项目，该工具用来将 thrift 接口定义转变成 swift 的注解定义

将最新的 Generator 拷贝到 /tmp 目录

    mvn org.apache.maven.plugins:maven-dependency-plugin:2.8:get -DremoteRepositories=central::default::http://repo1.maven.apache.org/maven2 -Dartifact=com.facebook.swift:swift-generator-cli:RELEASE:jar:standalone -Ddest=/tmp/

帮助

    java -jar /tmp/swift-generator-cli-0.23.1-standalone.jar                                                    
    Usage: SwiftGenerator [options] Thrift IDL input files
      Options:
        -default_package
           Use this package if there is no package specified in the IDL for java
        -generate_beans
           Generate thrift types as mutable beans
           Default: false
        -generate_included_files
           Generate code for included IDL files as well as specified IDL files
           Default: false
        -include_paths
           Colon-separated list of paths to search for include files
           Default: []
        -out
           Output directory
           Default: /home/mi/Git/thrift-swift-demo/gen-swift
        -override_package
           Force generation of code in a specific package
        -tweak
           Enable specific code generation tweaks
           Default: []
        -use_java_namespace
           Use 'java' namespace instead of 'java.swift' namespace
           Default: false

定义 thrift 文件

    namespace java.swift swift

    service BookService {
        void ping();
    }

生成 swift 定义文件

    java -jar /tmp/swift-generator-cli-0.23.1-standalone.jar -out src/main/java src/main/java/swift/book.thrift

该 swift-generator-cli 命令能够实现将 thrift 定义转到 swift 定义文件。


## Q&A

Q: @Override is not allowed when implementing interface method

A: If your project has multiple modules, also check that every module uses language level 6 or above, or use the project's language level (see Project Settings > Modules > xxx > Language level).
   
   You may need to reload your project once it is modified.
   

Q: thrift 数据传输协议
A: 
    
    TBinaryProtocol : 二进制格式. 
    TCompactProtocol : 压缩格式 
    TJSONProtocol : JSON格式 
    TSimpleJSONProtocol : 提供JSON只写协议, 生成的文件很容易通过脚本语言解析 
    tips:客户端和服务端的协议要一致


## reference

- <http://thrift.apache.org/>
- <http://thrift.apache.org/docs/install/debian>
- <http://blog.csdn.net/iter_zc/article/details/39697569>
- <http://www.baeldung.com/apache-thrift>
- <http://diwakergupta.github.io/thrift-missing-guide/>