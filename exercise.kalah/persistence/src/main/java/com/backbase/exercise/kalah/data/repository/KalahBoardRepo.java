package com.backbase.exercise.kalah.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backbase.exercise.kalah.data.entities.KalahBoardEntity;

/**
 * Repository for Kalah entities.
 *
 * @author tbansal
 */
public interface KalahBoardRepo extends JpaRepository<KalahBoardEntity, Long> {

}
