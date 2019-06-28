package com.backbase.exercise.kalah.data.entities;

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
public class KalahGameEntity extends BaseEntity {

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "player_1_id")
	private KalahPlayerEntity player1;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "player_2_id")
	private KalahPlayerEntity player2;

	@Column(name = "finished", nullable = false)
	private Boolean finished;

}
