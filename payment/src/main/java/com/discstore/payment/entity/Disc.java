package com.discstore.payment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

	private static final long serialVersionUID = 9029779267317052035L;

	@Id
	private Long id;
	
	@Column(name = "available_quantity", nullable = false, length = 10)
	private Integer available_quantity;

}
