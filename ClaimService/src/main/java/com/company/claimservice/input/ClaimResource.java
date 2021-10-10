package com.company.claimservice.input;

import com.company.ClaimDto;
import com.company.claimservice.application.ClaimManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ClaimResource {

    private final ClaimManager claimManager;

    ClaimResource(ClaimManager claimManager) {
        this.claimManager = claimManager;
    }

    @GetMapping
    ClaimDto get(@RequestParam String policyNumber) {
        return this.claimManager.get(policyNumber);
    }
}
