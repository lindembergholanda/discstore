package com.microservice.discstore.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.microservice.discstore.data.vo.DiscVO;

@Component
public class DiscSendMessage {
	
	@Value("${discstore.rabbitmq.exchange}")
	String exchange;
	
	@Value("${discstore.rabbitmq.routingkey}")
	String routingkey;
	
	public final RabbitTemplate rabbitTemplate;

	@Autowired
	public DiscSendMessage(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(DiscVO discVO) {
		rabbitTemplate.convertAndSend(exchange, routingkey, discVO);
	}
}
