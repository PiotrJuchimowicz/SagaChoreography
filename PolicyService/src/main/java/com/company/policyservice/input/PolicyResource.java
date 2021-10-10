package com.company.policyservice.input;

import com.company.PolicyDto;
import com.company.policyservice.application.PolicyManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/policies")
class PolicyResource {

    private final PolicyManager policyManager;

    public PolicyResource(PolicyManager policyManager) {
        this.policyManager = policyManager;
    }

    @PostMapping("/activate")
    void activate(@RequestBody PolicyDto policyDto) {
        this.policyManager.activate(policyDto);
    }

    @PostMapping("/close/{policyNumber}")
    void close(@PathVariable String policyNumber) {
        this.policyManager.close(policyNumber);
    }
}
