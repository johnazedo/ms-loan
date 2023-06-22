package br.ufrn.imd.microservices.msloan.core.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "paymentClient", url = "${client.payment-url}")
public interface PaymentClient {


}
