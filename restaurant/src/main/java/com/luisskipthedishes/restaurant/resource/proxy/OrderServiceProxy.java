package com.luisskipthedishes.restaurant.resource.proxy;


import com.luisskipthedishes.restaurant.model.Order;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order")
@RibbonClient(name = "order")
public interface OrderServiceProxy {
//    @GetMapping("/order/{id}")
//    public Order getRestaurant(@PathVariable("id") Long id);

}
