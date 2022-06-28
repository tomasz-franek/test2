package org.example.tokenizer;

import org.example.domain.TaskObject;

public class TaskObjectValidator implements TaskObjectValidatorInterface {

	public boolean validate(TaskObject taskObject){
		if(taskObject != null && !taskObject.getTaskValue().isEmpty()) {
			return taskObject.getTaskValue().matches("^[0-9+*-\\/]*");
		} else {
			return false;
		}
	}
}
