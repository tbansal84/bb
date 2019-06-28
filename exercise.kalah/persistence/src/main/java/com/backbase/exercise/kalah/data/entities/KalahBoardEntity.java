package com.backbase.exercise.kalah.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
/**
 * @author TBansal
 *
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class KalahBoardEntity extends BaseEntity {

	@Column(name = "home", nullable = false)
	private Integer home;

	@Column(name = "pits", nullable = false)
	private Integer[] pits;

}
