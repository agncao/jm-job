package com.jm.job.core.handle;


import com.jm.job.core.handle.model.TaskResult;

public interface IJobHandler {

	/**
	 * 作业的接口，需要用户实现
	 * @return
	 * @throws Exception
	 */
	TaskResult execute();
	
}
