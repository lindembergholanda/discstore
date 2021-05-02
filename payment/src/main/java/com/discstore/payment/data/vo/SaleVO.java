package com.discstore.payment.data.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.discstore.payment.entity.Sale;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","saleDate","discs","amount"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SaleVO extends RepresentationModel<SaleVO> implements Serializable {
	
	private static final long serialVersionUID = 6020441104009362680L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("saleDate")
	private Date saleDate;
	
	@JsonProperty("discs")
	private List<DiscPaymentVO> discs;
	
	@JsonProperty("amount")
	private Double amount;
	
	public static SaleVO create(Sale sale) {
		return new ModelMapper().map(sale, SaleVO.class);
	}
}
