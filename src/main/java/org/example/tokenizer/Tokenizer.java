package org.example.tokenizer;

import org.example.domain.TaskObject;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

	public List<Token> tokenizeTaskObject(TaskObject taskObject){
		if((!taskObject.getTaskValue().isEmpty())){
			String taskValue = taskObject.getTaskValue();
			return createTokenList(taskValue);
		}
		return new ArrayList<>();
	}

	private List<Token> createTokenList(String taskValue) {
		List<Token> tokens = new ArrayList<>();
		StringBuilder numberValue = new StringBuilder();
		for (int i = 0; i < taskValue.length(); i++) {
			char currentChar = taskValue.charAt(i);
			if(Character.isDigit(currentChar)){
				numberValue.append(currentChar);
			}else{
				addNumberValue(tokens, numberValue);
				numberValue.setLength(0);
				addOperationToken(tokens, "" + currentChar);
			}
		}
		addNumberValue(tokens, numberValue);
		return tokens;
	}

	private void addOperationToken(List<Token> tokens, String s) {
		tokens.add(createToken(TokenType.OPERATION, s));
	}

	private void addNumberValue(List<Token> tokens, StringBuilder numberValue) {
		if (numberValue.length() > 0) {
			tokens.add(createToken(TokenType.VALUE, numberValue.toString()));
		}
	}

	private Token createToken(TokenType tokenType, String value) {
		return new Token(tokenType,value);
	}
}
