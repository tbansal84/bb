package com.backbase.exercise.kalah.data.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class KalahPitDTO {

	private UUID id;

	private Integer position;
	private Integer noOfStones;

}
