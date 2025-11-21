package com.msa.firm.service;

import com.msa.firm.repository.FirmRepository;
import com.msa.firm.domain.Firm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FirmService {
    private final FirmRepository firmRepository;

    public List<Firm> findAll() {
        return firmRepository.findAll();
    }

    public Firm findById(Long firmId) {
        return firmRepository.findById(firmId).orElse(null);
    }

    @Transactional
    public Firm create(Firm firm) {
        return firmRepository.save(firm);
    }

    @Transactional
    public Firm update(Long firmId, Firm firm) {
        Firm existingFirm = firmRepository.findById(firmId).orElse(null);
        existingFirm.setFirmName(firm.getFirmName());
        return firmRepository.save(existingFirm);
    }

    @Transactional
    public void delete(Long firmId) {
        firmRepository.deleteById(firmId);
    }
}
