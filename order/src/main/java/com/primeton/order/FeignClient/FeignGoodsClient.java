package com.primeton.order.FeignClient;

import com.primeton.order.entity.TBook;
import com.primeton.order.vo.JsonBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Usher
 * @Description:
 */
@EnableFeignClients(value = "goods")
public interface FeignGoodsClient {
    @DeleteMapping("/deleteBook/{id}")
     JsonBean deleteBook(@PathVariable("id") Integer id);

    @GetMapping("/findBookById{id}")
    JsonBean findBookById(@PathVariable("id")Integer id);
}
