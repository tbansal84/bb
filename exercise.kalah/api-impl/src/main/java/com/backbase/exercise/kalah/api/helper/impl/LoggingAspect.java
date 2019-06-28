package com.backbase.exercise.kalah.api.helper.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Yengibar Manasyan
 */
@Component
@Aspect
public class LoggingAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

	@Before("within(com.backbase.kalah.service.impl.*)")
	public void logBeforeCall(JoinPoint joinPoint) {
		final StringBuilder msgBuilder = new StringBuilder()
				.append("Start call of method: ")
				.append(joinPoint.getSignature());

		if (joinPoint.getArgs().length > 0) {
			msgBuilder.append(" with arguments: ")
					.append(Arrays.toString(joinPoint.getArgs()));
		}

		LOGGER.debug(msgBuilder.toString());
	}

	@AfterReturning(value = "within(com.backbase.kalah.service.impl.*)", returning = "result")
	public void logAfterCall(JoinPoint joinPoint, Object result) {
		final StringBuilder msgBuilder = new StringBuilder()
				.append("Successfully finished call of method: ")
				.append(joinPoint.getSignature());

		if (joinPoint.getArgs().length > 0) {
			msgBuilder.append(" with arguments: ")
					.append(Arrays.toString(joinPoint.getArgs()));
		}

		if (result != null) {
			msgBuilder.append("\nResult: ")
					.append(result);
		}

		LOGGER.debug(msgBuilder.toString());
	}


}
