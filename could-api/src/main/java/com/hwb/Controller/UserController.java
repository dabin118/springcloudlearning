package com.hwb.Controller;


import com.hwb.Config.ServerConfig;
import com.hwb.FeignCall.UserServiceByFeign;
import org.apache.catalina.Host;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private ServerConfig serverConfig;
   @Autowired
    UserServiceByFeign userServiceByFeign;

    @Autowired
    RestTemplate restTemplate;



    @RequestMapping("/hello")
    public String hello() {
        return serverConfig.getUrl() + " hello world!";
    }

    @RequestMapping("/sayhello")
    public String sayhello(String name) {
        return serverConfig.getUrl() + ": say hello to" + name;
    }


    @RequestMapping("/threadsleep")
    public String threadsleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverConfig.getUrl() + ": threadsleep()" + millis;
    }


    @RequestMapping("/callhellobyfeign")
    public String helloMan2() {
        String res = userServiceByFeign.hello();
        return res;
    }

    @RequestMapping("/callthreadsleep")
    public String threadsleep2(long millis) {
        String res = userServiceByFeign.threadsleep(millis);
        return res;
    }


    @RequestMapping("/callhello")
    public String helloMan() {

        String res = restTemplate.getForObject("http://APISERVICE-PROVIDER/user/hello", String.class);
        return res;
    }



}
