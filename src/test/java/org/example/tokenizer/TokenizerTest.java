package org.example.tokenizer;

import org.example.domain.TaskObject;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class TokenizerTest {
	Tokenizer tokenizer;
	TaskObject taskObject;
	@Before
	public void init(){
		tokenizer = new Tokenizer();
		taskObject = new TaskObject();
	}

	@Test
	public void tokenizeTaskObject_emptyString_emptyTokenList(){
		taskObject.setTaskValue("");
		List<Token> tokenList = tokenizer.tokenizeTaskObject(taskObject);
		assertThat(tokenList.size(), CoreMatchers.is(0));
	}

	@Test
	public void tokenizeTaskObject_correctExpression_correctTokenList(){
		taskObject.setTaskValue("100-100");
		List<Token> tokenList = tokenizer.tokenizeTaskObject(taskObject);
		assertThat(tokenList.size(), CoreMatchers.is(3));
	}

	@Test
	public void tokenizeTaskObject_correctLongerExpression_correctTokenList(){
		taskObject.setTaskValue("-100+200*3/8");
		List<Token> tokenList = tokenizer.tokenizeTaskObject(taskObject);
		assertThat(tokenList.size(), CoreMatchers.is(8));
		assertThat(tokenList.get(0), CoreMatchers.is(new Token(TokenType.OPERATION,"-")));
		assertThat(tokenList.get(1), CoreMatchers.is(new Token(TokenType.VALUE,"100")));
		assertThat(tokenList.get(2), CoreMatchers.is(new Token(TokenType.OPERATION,"+")));
		assertThat(tokenList.get(3), CoreMatchers.is(new Token(TokenType.VALUE,"200")));
		assertThat(tokenList.get(4), CoreMatchers.is(new Token(TokenType.OPERATION,"*")));
		assertThat(tokenList.get(5), CoreMatchers.is(new Token(TokenType.VALUE,"3")));
		assertThat(tokenList.get(6), CoreMatchers.is(new Token(TokenType.OPERATION,"/")));
		assertThat(tokenList.get(7), CoreMatchers.is(new Token(TokenType.VALUE,"8")));
	}
}
