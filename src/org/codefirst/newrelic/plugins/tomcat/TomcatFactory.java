package org.codefirst.newrelic.plugins.tomcat;

import java.util.Map;

import com.newrelic.metrics.publish.Agent;
import com.newrelic.metrics.publish.AgentFactory;

public class TomcatFactory extends AgentFactory {

    public TomcatFactory() {
        super("org.codefirst.newrelic.plugins.tomcat.json");
    }

    @Override
    public Agent createConfiguredAgent(Map<String, Object> properties) {
        String name = (String) properties.get("name");
        String host = (String) properties.get("host");
        int port = ((Long) properties.get("port")).intValue();
        String request = (String) properties.get("request");
        String user = (String) properties.get("user");
        String password = (String) properties.get("password");
        int timeout = ((Long) properties.get("timeout")).intValue();
        String connector = (String) properties.get("connector");

        return new Tomcat(name, host, port, request, user, password, timeout, connector);
    }
}
