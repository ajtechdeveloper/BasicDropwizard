package com.aj.basicdropwizard;

public class BasicDropwizardConfigSettings {
	
    private BasicDropwizardConfiguration config;
    private BasicDropwizardConfigSettings() {
    }

    private static class LazyHolder {
        private static final BasicDropwizardConfigSettings INSTANCE = new BasicDropwizardConfigSettings();
    }

    public static BasicDropwizardConfigSettings getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void init(BasicDropwizardConfiguration config) {
        this.config = config;
    }

    public BasicDropwizardConfiguration getConfig() {
        return config;
    }
}

