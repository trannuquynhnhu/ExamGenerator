package edu.mum.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import edu.mum.exam.domain.QuestionType;

@Component
public class QuestionTypeFormatter implements Formatter<QuestionType>{
	@Autowired
	MessageSource messageSource;
	
	@Override
	public String print(QuestionType object, Locale locale) {
//		String key = object.getClass().getName() + "." + object.name();
//		return messageSource.getMessage(key, null, locale);
		return object.name();
	}

	@Override
	public QuestionType parse(String text, Locale locale) throws ParseException {		
		return QuestionType.valueOf(text);
	}

}
