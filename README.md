-r 要扫描的完整http请求包路径 指定-r 会发送请求校验安全问题
例:
GET /user/userinfo HTTP/1.1
Host: 127.0.0.1:8081

Cookie: token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpbklkIjoxMDAwMSwicm4iOiJBUEg2OEpSTXdURWFIN29TZEc2eURwa1c3SXA4bkY5TiJ9.u3xuzKmqKBgcPbTHBk3RitWc25sXfmJCP4ekeZQPKo0

-j 完整的jwt信息
-d 指定jwt秘钥爆破字典 如不指定不会执行爆破
-t 扫描线程数 不指定未默认30

提供两种扫描方式
java -jar JWTSCAN.jar -r 要扫描的完整http请求包路径 -d 指定要爆破的字典
java -jar JWTSCAN.jar -j 完整的jwt信息  -d 指定要爆破的字典

例: java -jar JWTSCAN.jar  -j eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpbklkIjoxMDAwMSwicm4iOiJBUEg2OEpSTXdURWFIN29TZEc2eURwa1c3SXA4bkY5TiJ9.u3xuzKmqKBgcPbTHBk3RitWc25sXfmJCP4ekeZQPKo0 -d Key_dictionary.txt
or
java -jar JWTSCAN.jar Original_request.txt -d Key_dictionary.txt