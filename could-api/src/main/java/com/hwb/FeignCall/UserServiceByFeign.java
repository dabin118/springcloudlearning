package com.hwb.FeignCall;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


@Component
@FeignClient("APISERVICE-PROVIDER")
public interface UserServiceByFeign {
    @RequestMapping(path = "/user/hello")
    public String hello();

    @RequestMapping("/user/sayhello")
    public String sayhello(String name);

    @RequestMapping("/user/threadsleep")
    public String threadsleep(long millis);
}
