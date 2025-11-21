package com.msa.license.service;

import com.msa.license.client.FirmClient;
import com.msa.license.client.dto.FirmDto;
import com.msa.license.domanin.License;
import com.msa.license.dto.LicenseWithFirmDto;
import com.msa.license.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LicenseService {
    private final LicenseRepository licenseRepository;
    private final FirmClient firmClient;

    public List<LicenseWithFirmDto> getAllLicenseWithFirm() {
        List<License> licenses = licenseRepository.findAll();
        return licenses.stream()
                .map(license -> {
                    FirmDto firm = firmClient.getFirmById(license.getFirmId());
                    return convertToDto(license, firm);
                }).collect(Collectors.toList());
    }

    public LicenseWithFirmDto getLicenseWithFirm(Long licenseId) {
        License license = licenseRepository.findById(licenseId).orElse(null);
        FirmDto firm = firmClient.getFirmById(license.getFirmId());
        return convertToDto(license, firm);
    }

    public List<LicenseWithFirmDto> getLicensesByFirm(Long firmId) {
        FirmDto firm = firmClient.getFirmById(firmId);
        List<License> licenses = licenseRepository.findByFirmId(firm.getFirmId());
        return licenses.stream()
                .map(license -> convertToDto(license, firm))
                .collect(Collectors.toList());
    }

    public List<License> getAllLicenses() {
        return licenseRepository.findAll();
    }

    public License getLicense(Long licenseId) {
        return licenseRepository.findById(licenseId).orElse(null);
    }

    @Transactional
    public License createLicense(License license) {
        return licenseRepository.save(license);
    }

    @Transactional
    public License updateLicense(Long licenseId, License updatedLicense) {
        License license = getLicense(licenseId);
        license.setLicenseName(updatedLicense.getLicenseName());
        license.setFirmId(updatedLicense.getFirmId());
        return licenseRepository.save(license);
    }

    @Transactional
    public void deleteLicense(Long licenseId) {
        licenseRepository.deleteById(licenseId);
    }

    private LicenseWithFirmDto convertToDto(License license, FirmDto firmDto) {
        LicenseWithFirmDto dto = new LicenseWithFirmDto();
        dto.setLicenseId(license.getLicenseId());
        dto.setLicenseName(license.getLicenseName());
        dto.setCreatedDate(license.getCreatedDate());
        dto.setFirmId(firmDto.getFirmId());
        dto.setFirmDto(firmDto);
        return dto;
    }
}
