package swift.calculator.controller;

import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.swift.service.ThriftClientManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import swift.calculator.TCalculatorService;
import swift.calculator.TDivisionByZeroException;
import swift.calculator.TOperation;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;
import javax.annotation.PostConstruct;

/**
 * create thrift client in controller
 */
@Api(description = "计算器")
@Controller
public class CalculatorController {

    private TCalculatorService client;

    @PostConstruct
    public void init() {
        ThriftClientManager clientManager = new ThriftClientManager();
        FramedClientConnector connector = new FramedClientConnector(new InetSocketAddress("localhost", 8083));
        try {
            client = clientManager.createClient(connector, TCalculatorService.class).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "加", notes = "加法, return num1 + num2")
    public int hello(@RequestParam int num1,
                     @RequestParam int num2) throws TException, TDivisionByZeroException {
        return client.calculate(num1, num2, TOperation.ADD);
    }

}
