package com.discstore.payment.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.discstore.payment.data.vo.SaleVO;
import com.discstore.payment.service.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleController {
	
	private final SaleService saleService;
	private final PagedResourcesAssembler<SaleVO> assembler;
	
	@Autowired
	public SaleController(SaleService saleService, PagedResourcesAssembler<SaleVO> assembler) {
		this.saleService = saleService;
		this.assembler = assembler;
	}
	
	@GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public SaleVO findById(@PathVariable("id") Long id) {
		SaleVO saleVO = saleService.findById(id);
		saleVO.add(linkTo(methodOn(SaleController.class).findById(id)).withSelfRel());
		return saleVO;
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page, 
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "saleDate"));
		
		Page<SaleVO> sales = saleService.findAll(pageable);
		sales.stream().forEach(p -> p.add(linkTo(methodOn(SaleController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<SaleVO>> pageModel = assembler.toModel(sales);
		
		return new ResponseEntity<>(pageModel, HttpStatus.OK);
	}
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
				 consumes = {MediaType.APPLICATION_JSON_VALUE})
	public SaleVO create(@RequestBody SaleVO saleVO) {
		SaleVO saleVONew = saleService.create(saleVO);
		saleVONew.add(linkTo(methodOn(SaleController.class).findById(saleVONew.getId())).withSelfRel());
		return saleVONew;
	}
	

}
