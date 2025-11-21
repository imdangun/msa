package com.msa.firm.controller;

import com.msa.firm.domain.Firm;
import com.msa.firm.service.FirmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firm")
@RequiredArgsConstructor
public class FirmController {
    private final FirmService firmService;

    @GetMapping
    public ResponseEntity<List<Firm>> getAllFirms() {
        return ResponseEntity.ok(firmService.findAll());
    }

    @GetMapping("/{firmId}")
    public ResponseEntity<Firm> getFirm(@PathVariable Long firmId) {
        return ResponseEntity.ok(firmService.findById(firmId));
    }

    @PostMapping
    public ResponseEntity<Firm> createFirm(@RequestBody Firm firm) {
        Firm created = firmService.create(firm);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{firmId}")
    public ResponseEntity<Firm> updateFirm(
            @PathVariable Long firmId,
            @RequestBody Firm firm) {
        Firm updated = firmService.update(firmId, firm);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{firmId}")
    public ResponseEntity<Void> deleteFirm(@PathVariable Long firmId) {
        firmService.delete(firmId);
        return ResponseEntity.noContent().build();
    }
}
