package org.codefirst.newrelic.plugins.tomcat.models;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ThreadInfo {
    private int currentThreadsBusy;
    private int currentThreadCount;
    private int maxThreads;

    public ThreadInfo(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        this.currentThreadsBusy = Integer.parseInt(attributes.getNamedItem("currentThreadsBusy").getTextContent());
        this.currentThreadCount = Integer.parseInt(attributes.getNamedItem("currentThreadCount").getTextContent());
        this.maxThreads = Integer.parseInt(attributes.getNamedItem("maxThreads").getTextContent());
    }

    public int getCurrentThreadsBusy() {
        return currentThreadsBusy;
    }

    public int getCurrentThreadCount() {
        return currentThreadCount;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

}
