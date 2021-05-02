package com.microservice.discstore.data.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.microservice.discstore.entity.Disc;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","artist","album","genre","format","available_quantity","price"})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DiscVO extends RepresentationModel<DiscVO> implements Serializable {

	private static final long serialVersionUID = -5285802577929038805L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("artist")
	private String artist;
	
	@JsonProperty("album")
	private String album;
	
	@JsonProperty("genre")
	private String genre;
	
	@JsonProperty("format")
	private String format;
	
	@JsonProperty("available_quantity")
	private Integer available_quantity;
	
	@JsonProperty("price")
	private Double price;
	
	public static DiscVO create(Disc disc) {
		return new ModelMapper().map(disc, DiscVO.class);
	}
}
