package com.netflix.conductor.client.spring.autoconfigure;

import com.netflix.conductor.sdk.workflow.executor.task.AnnotatedWorkerExecutor;

@FunctionalInterface
public interface AnnotationWorkerExecutorCustomizer {
    void customize(AnnotatedWorkerExecutor annotatedWorkerExecutor);
}
