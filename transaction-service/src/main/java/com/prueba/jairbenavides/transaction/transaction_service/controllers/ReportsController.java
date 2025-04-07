package com.prueba.jairbenavides.transaction.transaction_service.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.jairbenavides.transaction.transaction_service.services.TransactionService;

@RestController
@RequestMapping("/reportes")
public class ReportsController {

    @Autowired
    private TransactionService service;

    @GetMapping("/{accountId}")
    public ResponseEntity<?> listTransactions(@PathVariable Long accountId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta) {

        Date startDate = Date.from(fechaDesde.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDateTime endDateTime = fechaHasta.atTime(23, 59, 59);
        Date endDate = Date.from(endDateTime.atZone(ZoneId.systemDefault()).toInstant());

        return ResponseEntity.ok(service.findByAccountAndTransactionDateBetween(accountId, startDate, endDate));
    }

}
