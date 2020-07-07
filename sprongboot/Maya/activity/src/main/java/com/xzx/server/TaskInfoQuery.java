package com.xzx.server;

import org.activiti.dmn.api.Query;
import org.activiti.engine.task.TaskInfo;

public interface TaskInfoQuery<T extends TaskInfoQuery<?, ?>, V extends TaskInfo> extends Query<T, V> {

}
