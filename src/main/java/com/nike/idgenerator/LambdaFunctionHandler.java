package com.nike.idgenerator;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

public class LambdaFunctionHandler implements RequestHandler<SQSEvent, String> {
	public LambdaFunctionHandler() {
	}

	
	
	@Override
	public String handleRequest(SQSEvent input, Context context) {
		System.out.println("Event Received rom SQS ---->" + input.getRecords().get(0));
		return null;
	}

}
