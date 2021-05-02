package com.discstore.payment.data.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.discstore.payment.entity.Disc;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","available_quantity"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DiscVO extends RepresentationModel<DiscVO> implements Serializable {

	private static final long serialVersionUID = 605773598838785429L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("available_quantity")
	private Integer available_quantity;
	
	public static DiscVO create(Disc disc) {
		return new ModelMapper().map(disc, DiscVO.class);
	}
}
