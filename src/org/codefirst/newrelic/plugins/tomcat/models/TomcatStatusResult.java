package org.codefirst.newrelic.plugins.tomcat.models;

import java.util.ArrayList;
import java.util.List;

public class TomcatStatusResult {
    private Memory memory;
    private List<MemoryPool> memoryPools = new ArrayList<MemoryPool>();
    private ThreadInfo threadInfo;

    public void addMemoryPool(MemoryPool memoryPool) {
        this.memoryPools.add(memoryPool);
    }

    public MemoryPool[] getMemoryPools() {
        return (MemoryPool[]) memoryPools.toArray(new MemoryPool[memoryPools.size()]);
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public Memory getMemory() {
        return this.memory;
    }

    public void setThreadInfo(ThreadInfo threadInfo) {
        this.threadInfo = threadInfo;
    }

    public ThreadInfo getThreadInfo() {
        return this.threadInfo;
    }
}
