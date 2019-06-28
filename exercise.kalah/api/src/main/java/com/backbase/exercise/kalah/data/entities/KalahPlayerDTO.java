package com.backbase.exercise.kalah.data.entities;

import java.util.UUID;

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
public class KalahPlayerDTO {

	private UUID id;

	private KalahBoardDTO kalah;

	private Boolean myTurn;

}
