package com.coinmarketcap.controller;

import com.coinmarketcap.service.CoinCapSerive;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CoinCapController {
    @Autowired
    private CoinCapSerive coinCapSerive;

    @Operation(summary = "Listing latest")
    @GetMapping("/listing/latest")
    public ResponseEntity<?> listingLatest(){
            return  coinCapSerive.listingLatest();

    }
    @Operation(summary = "Listing historical")
    @GetMapping("/listing/historical")
    public ResponseEntity<?> listingHistorical(){
        return  coinCapSerive.listingHistorical();

    }
}
