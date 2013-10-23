package org.codefirst.newrelic.plugins.tomcat.models;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Memory {
    private Long max;
    private Long total;
    private Long free;

    public Memory(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        this.max = Long.valueOf(attributes.getNamedItem("max").getTextContent());
        this.total = Long.valueOf(attributes.getNamedItem("total").getTextContent());
        this.free = Long.valueOf(attributes.getNamedItem("free").getTextContent());
    }

    public Long getMax() {
        return max;
    }

    public Long getTotal() {
        return total;
    }

    public Long getFree() {
        return free;
    }

    public Number getPercent() {
        return (((float) max - (float) free) / (float) total) * 100L;
    }
}
