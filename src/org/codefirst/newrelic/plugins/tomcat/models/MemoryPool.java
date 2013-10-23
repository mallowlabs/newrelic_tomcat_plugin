package org.codefirst.newrelic.plugins.tomcat.models;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class MemoryPool {

    private String name;
    private Long usageUsed;
    private Long usageMax;
    private Long usageCommitted;
    private Long usageInit;

    public MemoryPool(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        this.usageUsed = Long.valueOf(attributes.getNamedItem("usageUsed").getTextContent());
        this.usageMax = Long.valueOf(attributes.getNamedItem("usageMax").getTextContent());
        this.usageCommitted = Long.valueOf(attributes.getNamedItem("usageCommitted").getTextContent());
        this.usageInit = Long.valueOf(attributes.getNamedItem("usageInit").getTextContent());
        this.name = attributes.getNamedItem("name").getTextContent();
    }

    public String getName() {
        return name;
    }

    public Long getUsageUsed() {
        return usageUsed;
    }

    public Long getUsageMax() {
        return usageMax;
    }

    public Long getUsageCommitted() {
        return usageCommitted;
    }

    public Long getUsageInit() {
        return usageInit;
    }

    public Number getPercent() {
        return (((float) usageUsed / (float) usageMax)) * 100L;
    }
}
