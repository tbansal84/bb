package com.backbase.exercise.kalah.data.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;

import org.springframework.data.annotation.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class KalahPit {
	@Id
	private UUID id;
	private Integer position;
	private Integer noOfStones;

}
