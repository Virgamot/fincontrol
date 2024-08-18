package ru.fincontrol.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.fincontrol.model.SecurityRate;
import ru.fincontrol.requester.MoexSecurityRateClient;

@Service
@Slf4j
@RequiredArgsConstructor
public class SecuritiesService {

    private final MoexSecurityRateClient securityRateClient;

    public SecurityRate getSecurity(String ticket){
        String securityInfo = securityRateClient.getSecurity(ticket);
        log.info(securityInfo);
        return SecurityRate.builder().ticket(ticket).value(securityInfo).build();
    }
}
