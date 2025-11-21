package com.msa.license.controller;

import com.msa.license.domanin.License;
import com.msa.license.dto.LicenseWithFirmDto;
import com.msa.license.service.LicenseService;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("license")
@RequiredArgsConstructor
public class LicenseController {
    private final LicenseService licenseService;

    @GetMapping("/withFirm")
    public ResponseEntity<List<LicenseWithFirmDto>> getAllLicenseWithFirm() {
        return ResponseEntity.ok(licenseService.getAllLicenseWithFirm());
    }

    @GetMapping("/{licenseId}/withFirm")
    public ResponseEntity<LicenseWithFirmDto> getLicenseWithFirm(@PathVariable Long licenseId) {
        return ResponseEntity.ok(licenseService.getLicenseWithFirm(licenseId));
    }

    @GetMapping("/withFirm/{firmId}")
    public ResponseEntity<List<LicenseWithFirmDto>> getLicensesByFirm(@PathVariable Long firmId) {
        return ResponseEntity.ok(licenseService.getLicensesByFirm(firmId));
    }

    @GetMapping
    public ResponseEntity<List<License>> getAllLicenses() {
        return ResponseEntity.ok(licenseService.getAllLicenses());
    }

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable Long licenseId) {
        return ResponseEntity.ok(licenseService.getLicense(licenseId));
    }

    @PostMapping
    public ResponseEntity<License> createLicense(@RequestBody License license) {
        License created = licenseService.createLicense(license);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{licenseId}")
    public ResponseEntity<License> updateLicense(
            @PathVariable Long licenseId,
            @RequestBody License license) {
        return ResponseEntity.ok(licenseService.updateLicense(licenseId, license));
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<Void> deleteLicense(@PathVariable Long licenseId) {
        licenseService.deleteLicense(licenseId);
        return ResponseEntity.noContent().build();
    }
}
