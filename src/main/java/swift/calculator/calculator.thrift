namespace java.swift swift


enum TOperation {
    ADD = 1,
    SUB = 2,
    MUL = 3,
    DIV = 4
}

exception TDivisionByZeroException {

}

service TCalculatorService {
    i32 calculate(1:i32 num1, 2:i32 num2, 3:TOperation op) throws (1:TDivisionByZeroException divisionByZero);
}

// java -jar /tmp/swift-generator-cli-0.23.1-standalone.jar  src/main/java/swift/calculator.thrift
