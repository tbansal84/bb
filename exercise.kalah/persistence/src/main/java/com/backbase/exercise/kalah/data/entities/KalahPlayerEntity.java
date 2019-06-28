package com.backbase.exercise.kalah.data.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author TBansal
 *
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class KalahPlayerEntity extends BaseEntity {

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "kalah_id")
	private KalahBoardEntity kalah;

	@Column(name = "my_turn")
	private Boolean myTurn;
	
	private List<KalahPit> pits;

}
