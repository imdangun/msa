package com.msa.license.controller;

import com.msa.license.dto.LicenseResponse;
import com.msa.license.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("license")
@RequiredArgsConstructor
public class LicenseController {
    private final LicenseService licenseService;

    @GetMapping
    public ResponseEntity<List<LicenseResponse>> getAllLicenses() {
        List<LicenseResponse> licenses = licenseService.getAllLicenses();
        return ResponseEntity.ok(licenses);
    }

    @GetMapping
    public ResponseEntity<List<LicenseResponse>> getLicenseById(@PathVariable Long id) {

    }

}
