package com.nike.idgenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nike.idgenerator.sqs.model.Message;

public class LambdaFunctionHandler implements RequestHandler<SQSEvent, String> {
	public LambdaFunctionHandler() {
	}

	@Override
	public String handleRequest(SQSEvent input, Context context) {

		List<String> idsList = new ArrayList<String>();
		SQSMessage sqsMessage = input.getRecords().get(0);
		System.out.println("Event Received from SQS ---->" + sqsMessage);
		String messageBody = sqsMessage.getBody();
		System.out.println("Message Received from SQS ---->" + messageBody);
		ObjectMapper mapper = new ObjectMapper();
		try {
			Message message = mapper.readValue(messageBody, Message.class);
			for (int i = 1; i <= message.getCount(); i++) {
				idsList.add(message.getPrefix() + (message.getStartFrom() + i) + message.getSuffix());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("IDs Genereated are -->" + idsList.toString());
		return null;
	}

}
