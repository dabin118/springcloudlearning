package com.hwb.Controller;


import com.hwb.Config.ServerConfig;
import org.apache.catalina.Host;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello(){
        return serverConfig.getUrl()+" hello world!";
    }

    @RequestMapping("/sayhello")
    public String sayhello(String name){
        return  serverConfig.getUrl()+": say hello to"+name;
    }

    @RequestMapping("/callhello")
    public String helloMan(){

        String res=restTemplate.getForObject("http://API-PROVIDER/user/hello", String.class);
        return  res;
    }
}
