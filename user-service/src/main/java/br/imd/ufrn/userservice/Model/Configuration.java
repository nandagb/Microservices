package br.imd.ufrn.userservice.Model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("user-service")
public class Configuration {
    private int max;
    private int min;

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return this.max;
    }

    public int getMin() {
        return this.min;
    }
}
