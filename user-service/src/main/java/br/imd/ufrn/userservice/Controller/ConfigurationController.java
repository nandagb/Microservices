package br.imd.ufrn.userservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.imd.ufrn.userservice.Model.Configuration;
import br.imd.ufrn.userservice.Model.LimitConfiguration;

@RestController
public class ConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromConfigurations() {
        LimitConfiguration limitConfiguration = new LimitConfiguration(configuration.getMax(), configuration.getMin());
        return limitConfiguration;
    }
    
}
