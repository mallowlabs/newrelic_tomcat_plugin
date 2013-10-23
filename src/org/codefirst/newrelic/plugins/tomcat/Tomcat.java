package org.codefirst.newrelic.plugins.tomcat;

import org.codefirst.newrelic.plugins.tomcat.models.MemoryPool;
import org.codefirst.newrelic.plugins.tomcat.models.TomcatStatusResult;

import com.newrelic.metrics.publish.Agent;

public class Tomcat extends Agent {
    private String name = "Default";
    private TomcatStatus tomcatStatus;

    public Tomcat(String name, String host, int port, String request, String user, String password, int timeout, String connector) {
        super("org.codefirst.newrelic.plugins.tomcat", "0.0.1");
        this.name = name;
        this.tomcatStatus = new TomcatStatus(host, port, request, user, password, timeout, connector);
    }

    @Override
    public String getComponentHumanLabel() {
        return name;
    }

    @Override
    public void pollCycle() {
        TomcatStatusResult result = this.tomcatStatus.fetch();

        if (result.getMemory() != null) {
            reportMetric("Tomcat/Memory/Total", "percent", result.getMemory().getPercent());
        }

        for (MemoryPool memoryPool : result.getMemoryPools()) {
            reportMetric("Tomcat/Memory/" + memoryPool.getName(), "percent", memoryPool.getPercent());
        }

        if (result.getThreadInfo() != null) {
            reportMetric("Tomcat/Thread/CurrentThreadsBusy", "threads", result.getThreadInfo().getCurrentThreadsBusy());
        }
    }

}
