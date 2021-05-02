package com.microservice.discstore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.microservice.discstore.data.vo.DiscVO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "disc")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Disc implements Serializable {

	private static final long serialVersionUID = 2215491193185196104L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "artist", nullable = false, length = 255)
	private String artist;
	
	@Column(name = "album", nullable = false, length = 255)
	private String album;
	
	@Column(name = "genre", nullable = false, length = 255)
	private String genre;
	
	@Column(name = "format", nullable = false, length = 255)
	private String format;
	
	@Column(name = "available_quantity", nullable = false, length = 10)
	private Integer available_quantity;
	
	@Column(name = "price", nullable = false, length = 10)
	private Double price;
	
	public static Disc create(DiscVO discVO) {
		return new ModelMapper().map(discVO, Disc.class);
	}

}
