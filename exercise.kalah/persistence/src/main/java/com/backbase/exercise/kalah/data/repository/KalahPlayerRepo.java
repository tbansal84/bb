package com.backbase.exercise.kalah.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backbase.exercise.kalah.data.entities.KalahPlayerEntity;

/**
 * Player repo.
 *
 * @author tbansal
 */
public interface KalahPlayerRepo extends JpaRepository<KalahPlayerEntity, UUID> {

}
