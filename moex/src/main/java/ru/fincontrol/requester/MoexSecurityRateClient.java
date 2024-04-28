package ru.fincontrol.requester;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "moex",url = "https://iss.moex.com/iss/engines/stock/markets/shares/securities")
public interface MoexSecurityRateClient {

    @GetMapping("/{ticket}?iss.meta=off")
    String getSecurity(@PathVariable("ticket") String ticket);
}
