


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