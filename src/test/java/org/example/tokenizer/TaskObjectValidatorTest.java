package org.example.tokenizer;

import org.example.domain.TaskObject;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class TaskObjectValidatorTest {
	TaskObjectValidator taskObjectValidator;
	TaskObject taskObject;

	@Before
	public void init() {
		taskObjectValidator = new TaskObjectValidator();
		taskObject = new TaskObject();
	}

	@Test
	public void validateTaskObject_emptyString_false() {
		taskObject.setTaskValue("");
		assertThat(taskObjectValidator.validate(taskObject), CoreMatchers.is(false));
	}

	@Test
	public void validateTaskObject_spacesInString_false() {
		taskObject.setTaskValue("  3*3");
		assertThat(taskObjectValidator.validate(taskObject), CoreMatchers.is(false));
		taskObject.setTaskValue( "3 *3");
		assertThat(taskObjectValidator.validate(taskObject), CoreMatchers.is(false));
		taskObject.setTaskValue("3*3 ");
		assertThat(taskObjectValidator.validate(taskObject), CoreMatchers.is(false));
	}

	@Test
	public void validateTaskObject_letters_false() {
		taskObject.setTaskValue("a*3");
		assertThat(taskObjectValidator.validate(taskObject), CoreMatchers.is(false));
	}

	@Test
	public void validateTaskObject_operationAdd_true() {
		taskObject.setTaskValue("3+3");
		assertThat(taskObjectValidator.validate(taskObject), CoreMatchers.is(true));
	}

	@Test
	public void validateTaskObject_operationSubtract_true() {
		taskObject.setTaskValue("3444-3333");
		assertThat(taskObjectValidator.validate(taskObject), CoreMatchers.is(true));
	}

	@Test
	public void validateTaskObject_operationMultiply_true() {
		taskObject.setTaskValue("3*8");
		assertThat(taskObjectValidator.validate(taskObject), CoreMatchers.is(true));
	}

	@Test
	public void validateTaskObject_operationDivide_true() {
		taskObject.setTaskValue("44/6");
		assertThat(taskObjectValidator.validate(taskObject), CoreMatchers.is(true));
	}

}
