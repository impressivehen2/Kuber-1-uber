package com.impressivehen.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/*
http://hotpot-service:3333:
Inside kubernetes visible on default ports and service name
No matter how many pods have been created service will always be available on that URL
Requests are load balanced between all pods out of the box.
 */
@FeignClient(name = "hotpot-service", url = "http://hotpot-service:3333")
//@FeignClient(name = "hotpot-service", url = "http://localhost:3333")
public interface HotpotClient {

    @RequestMapping(method = RequestMethod.GET, value = "/hotpot/menu")
    Map<String, Double> getMenu();
}

