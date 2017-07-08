package com.aj.basicdropwizard.resource;

import com.aj.basicdropwizard.BasicDropwizardConfiguration;
import com.codahale.metrics.health.HealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicDropwizardHealthCheckResource extends HealthCheck {

    private static final Logger logger = LoggerFactory.getLogger(BasicDropwizardHealthCheckResource.class);

    private static String appName;

    public BasicDropwizardHealthCheckResource(BasicDropwizardConfiguration basicDropwizardConfiguration){
       this.appName = basicDropwizardConfiguration.getAppName();
    }

    @Override
    protected Result check() throws Exception {
        logger.info("App Name is: {}", appName);
        if("BasicDropwizard".equalsIgnoreCase(appName)) {
            return Result.healthy();
        }
        return Result.unhealthy("Basic Dropwizard Service is down");
    }
}