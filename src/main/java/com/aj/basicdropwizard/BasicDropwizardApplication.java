package com.aj.basicdropwizard;

import com.aj.basicdropwizard.resource.BasicDropwizardHealthCheckResource;
import com.aj.basicdropwizard.resource.PingResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BasicDropwizardApplication extends Application<BasicDropwizardConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(BasicDropwizardApplication.class);

	public static void main(String[] args) throws Exception {
		new BasicDropwizardApplication().run("server", args[0]);
	}

	@Override
	public void run(BasicDropwizardConfiguration config, Environment env)
			throws Exception {
		BasicDropwizardConfigSettings.getInstance().init(config);
        logger.info("Registering RESTful API resources");
		env.jersey().register(new PingResource());
		env.healthChecks().register("BasicDropwizardHealthCheck",
				new BasicDropwizardHealthCheckResource(config));
	}
}
