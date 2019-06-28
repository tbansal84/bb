package com.backbase.exercise.kalah.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backbase.exercise.kalah.data.entities.KalahGameEntity;

/**
 * JPA repo for Game entity.
 *
 * @author tbansal
 */
public interface KalahGameRepository extends JpaRepository<KalahGameEntity, UUID> {

	KalahGameEntity findFirstByPlayer1IsNotNullAndPlayer2IsNull();

	KalahGameEntity findFirstByPlayer1IdOrPlayer2Id(UUID firstId, UUID secondId);
}
