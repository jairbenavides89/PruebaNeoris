package com.prueba.jairbenavides.transaction.transaction_service.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.prueba.jairbenavides.transaction.transaction_service.services.TransactionService;

/**
 * Controlador REST para generar reportes de transacciones.
 * Permite consultar transacciones por cuenta y por rango de fechas.
 */
@RestController
@RequestMapping("/reportes")
public class ReportsController {

    @Autowired
    private TransactionService service;

    /**
     * Obtiene las transacciones de una cuenta específica dentro de un rango de fechas.
     *
     * @param accountId  ID de la cuenta a consultar
     * @param fechaDesde fecha de inicio del rango (formato ISO: yyyy-MM-dd)
     * @param fechaHasta fecha de fin del rango (formato ISO: yyyy-MM-dd)
     * @return lista de transacciones dentro del rango especificado
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<?> listTransactions(
            @PathVariable Long accountId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta) {

        // Convertir LocalDate a java.util.Date para búsqueda por rangos en base de datos
        Date startDate = Date.from(fechaDesde.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDateTime endDateTime = fechaHasta.atTime(23, 59, 59);
        Date endDate = Date.from(endDateTime.atZone(ZoneId.systemDefault()).toInstant());

        return ResponseEntity.ok(service.findByAccountAndTransactionDateBetween(accountId, startDate, endDate));
    }
}
