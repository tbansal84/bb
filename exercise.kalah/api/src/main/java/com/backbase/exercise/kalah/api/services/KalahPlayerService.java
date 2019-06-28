package com.backbase.exercise.kalah.api.services;

import java.util.UUID;

import com.backbase.exercise.kalah.data.entities.KalahPlayerDTO;

/**
 * Player service.
 *
 * @author tbansal
 */
public interface KalahPlayerService {

	/**
	 * Create or update a Player.
	 *
	 * @param player player to create or update
	 * @return saved player
	 */
	KalahPlayerDTO save(KalahPlayerDTO player);

	/**
	 * Find by id.
	 *
	 * @param id Player id
	 * @return found Player
	 */
	KalahPlayerDTO findById(UUID id);
}
