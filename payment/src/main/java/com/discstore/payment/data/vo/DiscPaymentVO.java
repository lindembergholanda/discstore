package com.discstore.payment.data.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.discstore.payment.entity.DiscPayment;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","discId","quantity"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DiscPaymentVO extends RepresentationModel<DiscPaymentVO> implements Serializable {
	
	private static final long serialVersionUID = 6137508365263869182L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("discId")
	private Long discId;
	
	@JsonProperty("quantity")
	private Integer quantity;
	
	public static DiscPaymentVO create(DiscPayment discPayment) {
		return new ModelMapper().map(discPayment, DiscPaymentVO.class);
	}
}
