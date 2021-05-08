package com.discstore.payment.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.discstore.payment.data.vo.DiscVO;
import com.discstore.payment.entity.Disc;
import com.discstore.payment.repository.DiscRepository;

@Component
public class ProdutoReceiveMessage {
	
	private final DiscRepository discRepository;

	@Autowired
	public ProdutoReceiveMessage(DiscRepository discRepository) {
		this.discRepository = discRepository;
	}
	
	@RabbitListener(queues = {"${discstore.rabbitmq.queue}"})
	public void receive(@Payload DiscVO discVO) {
		discRepository.save(Disc.create(discVO));
	}
}