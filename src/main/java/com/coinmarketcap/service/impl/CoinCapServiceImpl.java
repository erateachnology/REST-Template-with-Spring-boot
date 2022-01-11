package com.coinmarketcap.service.impl;

import com.coinmarketcap.service.CoinCapSerive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

@Service
public class CoinCapServiceImpl implements CoinCapSerive {
    private static final String LISTING_LATEST = "Listing latest: {}";
    private final Logger logger = LoggerFactory.getLogger(CoinCapServiceImpl.class);

    @Value("${coin.cap.listing.latest}")
    private String listingLatestUrl;

    @Value("${coin.cap.listing.historical}")
    private String listingHistoricaltUrl;

    @Value("${coin.cap.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public ResponseEntity<?> listingLatest() {
        try{
            return restTemplate.exchange(
                    listingLatestUrl, HttpMethod.GET, getRequestEntity(),Object.class);
        }catch (HttpClientErrorException | HttpServerErrorException | UnknownHttpStatusCodeException e){
            logger.error(LISTING_LATEST + e.getMessage());
            return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        }
    }

    @Override
    public ResponseEntity<?> listingHistorical() {
        try{
            return restTemplate.exchange(
                    listingHistoricaltUrl, HttpMethod.GET, getRequestEntity(),Object.class);
        }catch (HttpClientErrorException | HttpServerErrorException | UnknownHttpStatusCodeException e){
            logger.error(LISTING_LATEST + e.getMessage());
            return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        }
    }

    public HttpEntity<Void> getRequestEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", apiKey);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        return  requestEntity;
    }
}
