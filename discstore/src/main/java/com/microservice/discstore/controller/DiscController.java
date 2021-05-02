package com.microservice.discstore.controller;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.discstore.data.vo.DiscVO;
import com.microservice.discstore.service.DiscService;



@RestController
@RequestMapping("/disc")
public class DiscController {
	
	private final DiscService discService;
	private final PagedResourcesAssembler<DiscVO> assembler;
	
	@Autowired
	public DiscController(DiscService discService, PagedResourcesAssembler<DiscVO> assembler) {
		this.discService = discService;
		this.assembler = assembler;
	}
	
	@GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public DiscVO findById(@PathVariable("id") Long id) {
		DiscVO discVO = discService.findById(id);
		discVO.add(linkTo(methodOn(DiscController.class).findById(id)).withSelfRel());
		return discVO;
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page, 
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "artist"));
		
		Page<DiscVO> discs = discService.findAll(pageable);
		discs.stream().forEach(p -> p.add(linkTo(methodOn(DiscController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<DiscVO>> pageModel = assembler.toModel(discs);
		
		return new ResponseEntity<>(pageModel, HttpStatus.OK);
	}
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
			     consumes = {MediaType.APPLICATION_JSON_VALUE})
	public DiscVO create(@RequestBody DiscVO discVO) {
		DiscVO discVONew = discService.create(discVO);
		discVONew.add(linkTo(methodOn(DiscController.class).findById(discVONew.getId())).withSelfRel());
		return discVONew;
	}
	
	@PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
			    consumes = {MediaType.APPLICATION_JSON_VALUE})
	public DiscVO update(@RequestBody DiscVO discVO) {
		DiscVO discVONew = discService.update(discVO);
		discVONew.add(linkTo(methodOn(DiscController.class).findById(discVO.getId())).withSelfRel());
		return discVONew;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		discService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}