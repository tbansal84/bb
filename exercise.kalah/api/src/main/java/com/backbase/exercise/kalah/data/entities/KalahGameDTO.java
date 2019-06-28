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
public class KalahGameDTO {

	private UUID id;

	private KalahPlayerDTO player1;

	private KalahPlayerDTO player2;

	private Boolean finished;

}
