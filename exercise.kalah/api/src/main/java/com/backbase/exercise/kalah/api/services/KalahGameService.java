package com.backbase.exercise.kalah.api.services;

import java.util.UUID;

import com.backbase.exercise.kalah.data.entities.KalahGameDTO;
import com.backbase.exercise.kalah.data.entities.KalahPlayerDTO;

/**
 * Game service.
 *
 * @author tbansal
 */
public interface KalahGameService {

	/**
	 * Enter to game.
	 * It should create a new Player also.
	 *
	 * @return the game
	 */
	KalahGameDTO enterToGame();

	/**
	 * Find game by id.
	 *
	 * @param id game id
	 * @return found game
	 */
	KalahGameDTO findById(UUID id);

	/**
	 * Find by player id.
	 *
	 * @param playerId player id
	 * @return found game
	 */
	KalahGameDTO findByPlayerId(UUID playerId);

	/**
	 * Find opponent by player id.
	 *
	 * @param playerId player id
	 * @return opponent or null if there is not
	 */
	KalahPlayerDTO findGameOpponentByPlayerId(UUID playerId);

	/**
	 * Save game.
	 *
	 * @param game game which will be updated
	 * @return saved game
	 */
	KalahGameDTO save(KalahGameDTO game);

	/**
	 * Play the game.
	 *
	 * @param playerId player id
	 * @param position   position from which stones where taken.
	 * @return changed game
	 */
	KalahGameDTO play(UUID playerId, Integer position);
}
