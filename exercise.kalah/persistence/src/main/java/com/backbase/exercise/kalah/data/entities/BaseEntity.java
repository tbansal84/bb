package com.backbase.exercise.kalah.data.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author TBansal
 *
 */
@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

	@Id
	@Column(name = "id")
	private UUID id;

	private LocalDateTime created_at;
	private String created_by;

}
