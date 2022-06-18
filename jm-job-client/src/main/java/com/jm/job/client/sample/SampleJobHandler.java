package com.jm.job.client.sample;

import com.jm.job.core.handle.IJobHandler;
import com.jm.job.core.handle.annotation.JmJobHandle;
import com.jm.job.core.handle.model.TaskResult;

@JmJobHandle
public class SampleJobHandler implements IJobHandler {
    @Override
    public TaskResult execute() {
        return null;
    }
}
