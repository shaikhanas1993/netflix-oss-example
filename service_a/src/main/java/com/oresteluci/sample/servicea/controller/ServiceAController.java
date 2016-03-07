package com.oresteluci.sample.servicea.controller;

import com.oresteluci.sample.servicea.bean.MessageBean;
import com.oresteluci.sample.servicea.service.DiscoveryClientService;
import com.oresteluci.sample.servicea.service.FeignService;
import com.oresteluci.sample.servicea.service.RestTemplateService;
import com.oresteluci.sample.servicea.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Oreste Luci
 */
@RestController
public class ServiceAController {

    @Autowired
    private DiscoveryClientService discoveryClientService;

    @Autowired
    private RestTemplateService restTemplateService;

    @Autowired
    private FeignService feignService;

    @Autowired
    private StreamService streamService;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(
            value = "/test",
            method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public MessageBean test() {
        System.out.println(counter.incrementAndGet() + ". ServiceBController.test");
        return new MessageBean("Service A");
    }

    @RequestMapping(
            value = "/discoveryClient",
            method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<String> discoveryClient() {
        System.out.println(counter.incrementAndGet() + ". ServiceBController.discoveryClient");
        return discoveryClientService.discoveryClient();
    }

    @RequestMapping(
            value = "/restTemplate",
            method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public MessageBean restTemplate() {
        System.out.println(counter.incrementAndGet() + ". ServiceBController.restTemplate");
        return restTemplateService.restTemplate();
    }

    @RequestMapping(
            value = "/restTemplate2",
            method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public @ResponseBody MessageBean restTemplate2() {
        System.out.println(counter.incrementAndGet() + ". ServiceBController.restTemplate2");
        return restTemplateService.restTemplate2();
    }

    @RequestMapping(
            value = "/feign",
            method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public MessageBean feignClient() {
        System.out.println(counter.incrementAndGet() + ". ServiceBController.feignClient");
        return feignService.feignClient();
    }

    @RequestMapping(
            value = "/stream",
            method= RequestMethod.GET
    )
    public String sendStreamMessage() {
        System.out.println(counter.incrementAndGet() + ". ServiceBController.sendStreamMessage");
        streamService.sendMessage();
        return "";
    }
}