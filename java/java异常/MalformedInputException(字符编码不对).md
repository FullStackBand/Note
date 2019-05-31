#指定的字符编码不对，可能会抛出异常 MalformedInputException ，或者读取到了乱码
```text
java.nio.charset.MalformedInputException: Input length = 1
    at java.nio.charset.CoderResult.throwException(CoderResult.java:281)
    at sun.nio.cs.StreamDecoder.implRead(StreamDecoder.java:339)
    at sun.nio.cs.StreamDecoder.read(StreamDecoder.java:178)
    at java.io.InputStreamReader.read(InputStreamReader.java:184)
    at java.io.BufferedReader.fill(BufferedReader.java:161)
    at java.io.BufferedReader.readLine(BufferedReader.java:324)
    at java.io.BufferedReader.readLine(BufferedReader.java:389)
    at com.coin.Test.main(Test.java:79)
```
