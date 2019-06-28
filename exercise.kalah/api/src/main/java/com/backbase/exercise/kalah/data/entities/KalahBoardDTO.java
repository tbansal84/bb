package com.backbase.exercise.kalah.data.entities;

import java.util.List;
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
public class KalahBoardDTO {
	private UUID id;

	private Integer home;

	private List<KalahPitDTO> pits;

	public KalahBoardDTO(Integer pitsCount, Integer stonesCount) {
	}

	public void incrementHome(int i) {
		home += i;
	}

	public Integer getPitStonesCount(int opponentReversPitIndex) {
		return null;
	}

	public void emptyPit(int opponentReversPitIndex) {

	}

}
