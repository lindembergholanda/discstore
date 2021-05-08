package com.microservice.discstore.service;

import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.microservice.discstore.data.vo.DiscVO;
import com.microservice.discstore.entity.Disc;
import com.microservice.discstore.exception.ResourceNotFoundException;
import com.microservice.discstore.message.DiscSendMessage;
import com.microservice.discstore.repository.DiscRepository;

@Service
public class DiscService {

	private final DiscRepository discRepository;
	private final DiscSendMessage discSendMessage;

	@Autowired
	public DiscService(DiscRepository discRepository, DiscSendMessage discSendMessage) {
		this.discRepository = discRepository;
		this.discSendMessage = discSendMessage;
	}

	public DiscVO create(DiscVO discVO) {
		DiscVO discVoReturn = DiscVO.create(discRepository.save(Disc.create(discVO)));
		discSendMessage.sendMessage(discVoReturn);
		return discVoReturn;
	}

	public Page<DiscVO> findAll(Pageable pageable) {
		var page = discRepository.findAll(pageable);
		return page.map(this::convertToDiscVO);
	}

	private DiscVO convertToDiscVO(Disc disc) {
		return DiscVO.create(disc);
	}

	public DiscVO findById(Long id) {
		var entity = discRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
				ResourceBundle.getBundle("messages").getString("exception.not_found_by_id")));
		return DiscVO.create(entity);
	}
	
	public DiscVO update(DiscVO discVO) {
		final Optional<Disc> optionalDisc = discRepository.findById(discVO.getId());
		
		if(!optionalDisc.isPresent()) {
			new ResourceNotFoundException(ResourceBundle.getBundle("messages").getString("exception.not_found_by_id"));
		}
		
		return DiscVO.create(discRepository.save(Disc.create(discVO)));
	}
	
	public void delete(Long id) {
		var entity = discRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
				ResourceBundle.getBundle("messages").getString("exception.not_found_by_id")));
		discRepository.delete(entity);
	}
}
