package com.coinmarketcap.service;

import org.springframework.http.ResponseEntity;

public interface CoinCapSerive {
    ResponseEntity<?> listingLatest();
    ResponseEntity<?> listingHistorical();
}
