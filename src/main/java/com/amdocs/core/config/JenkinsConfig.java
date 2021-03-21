package com.amdocs.core.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources("classpath:jenkins.properties")
public interface JenkinsConfig extends Config {
    JenkinsConfig JENKINS_CONFIG = ConfigFactory.create(JenkinsConfig.class);

    @Key("jenkins.user.name")
    String getJenkinsUserName();

    @Key("jenkins.user.password")
    String getJenkinsUserPassword();
}
