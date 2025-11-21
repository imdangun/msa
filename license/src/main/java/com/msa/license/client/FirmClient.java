package com.msa.license.client;

import com.msa.license.client.dto.FirmDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="firm")
public interface FirmClient {
    @GetMapping("/firm")
    List<FirmDto> getAllFirms();

    @GetMapping("/firm/{firmId}")
    FirmDto getFirmById(@PathVariable Long firmId);

    @PostMapping("/firm")
    FirmDto createFirm(@RequestBody FirmDto firmDto);

    @PutMapping("/firm/{firmId}")
    FirmDto updateFirm(@PathVariable Long firmId, @RequestBody FirmDto firmDto);

    @DeleteMapping("/firm/{firmId}")
    void deleteFirm(@PathVariable Long firmId);
}
