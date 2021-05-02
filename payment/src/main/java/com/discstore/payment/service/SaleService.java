package com.discstore.payment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.discstore.payment.data.vo.SaleVO;
import com.discstore.payment.entity.DiscPayment;
import com.discstore.payment.entity.Sale;
import com.discstore.payment.exception.ResourceNotFoundException;
import com.discstore.payment.repository.DiscPaymentRepository;
import com.discstore.payment.repository.SaleRepository;

@Service
public class SaleService {
	
	private final SaleRepository saleRepository;
	private final DiscPaymentRepository discPaymentRepository;

	@Autowired
	public SaleService(SaleRepository saleRepository, DiscPaymentRepository discPaymentRepository) {
		this.saleRepository = saleRepository;
		this.discPaymentRepository = discPaymentRepository;
	}

	public SaleVO create(SaleVO saleVO) {
		Sale sale = saleRepository.save(Sale.create(saleVO));
		
		List<DiscPayment> discsAdd = new ArrayList<>();
		saleVO.getDiscs().forEach(s -> {
			DiscPayment discPayment = DiscPayment.create(s);
			discPayment.setSale(sale);
			discsAdd.add(discPaymentRepository.save(discPayment));
		});
		sale.setDiscs(discsAdd);
		return SaleVO.create(sale);
	}

	public Page<SaleVO> findAll(Pageable pageable) {
		var page = saleRepository.findAll(pageable);
		return page.map(this::convertToSaleVO);
	}

	private SaleVO convertToSaleVO(Sale sale) {
		return SaleVO.create(sale);
	}
	
	public SaleVO findById(Long id) {
		var entity = saleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
				ResourceBundle.getBundle("messages").getString("exception.not_found_by_id")));
		return SaleVO.create(entity);
	}
}
