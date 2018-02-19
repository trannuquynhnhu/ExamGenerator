package edu.mum.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import edu.mum.exam.domain.Level;

@Component
public class LevelFormatter implements Formatter<Level>{	
	
	@Override
	public String print(Level object, Locale locale) {
		return object.name();
	}

	@Override
	public Level parse(String text, Locale arg1) throws ParseException {
		return Level.valueOf(text);
	}

}
