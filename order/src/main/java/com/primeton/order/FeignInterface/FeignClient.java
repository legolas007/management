package com.primeton.order.FeignInterface;

import com.primeton.order.entity.TBook;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @Author: Usher
 * @Description:
 */
@org.springframework.cloud.openfeign.FeignClient(value = "goods")
public interface FeignClient {
    @GetMapping("/bookManager/bookdao")
    public TBook bookdao(Integer id);
}
