package ru.fincontrol.requester;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "moex",url = "https://iss.moex.com")
public interface MoexSecurityRateClient {

    @GetMapping("/iss/engines/stock/markets/shares/securities/{ticket}?iss.meta=off")
    String getSecurity(@PathVariable("ticket") String ticket);

    @GetMapping("/iss/statistics/engines/futures/markets/indicativerates/securities/{rate}")
    String getExchangeRate(@PathVariable("rate") String rate);
}
