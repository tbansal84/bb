package com.backbase.exercise.kalah.api.services;

import com.backbase.exercise.kalah.data.entities.KalahBoardDTO;

/**
 * Kalah service.
 *
 * @author tbansal
 */
public interface KalahBoardService {

	/**
	 * Update the e
	 *
	 * @param kalah kalah to update
	 * @return saved kalah
	 */
	KalahBoardDTO update(KalahBoardDTO kalah);

	/**
	 * Create new Kalah.
	 *
	 * @return created kalah
	 */
	KalahBoardDTO create();
}
