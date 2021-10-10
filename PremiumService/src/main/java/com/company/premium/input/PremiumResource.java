package com.company.premium.input;

import com.company.PremiumDto;
import com.company.premium.application.PremiumManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class PremiumResource {

    private final PremiumManager orderManager;

    PremiumResource(PremiumManager premiumManager) {
        this.orderManager = premiumManager;
    }

    List<PremiumDto> getAll(@RequestParam String policyNumber) {
        return this.orderManager.getByPolicy(policyNumber);
    }
}
