package com.backbase.exercise.kalah.api.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.backbase.exercise.kalah.api.exceptions.ResourceNotFoundException;
import com.backbase.exercise.kalah.api.services.KalahBoardService;
import com.backbase.exercise.kalah.api.services.KalahPlayerService;
import com.backbase.exercise.kalah.data.entities.KalahBoardDTO;
import com.backbase.exercise.kalah.data.entities.KalahBoardEntity;
import com.backbase.exercise.kalah.data.entities.KalahPlayerDTO;
import com.backbase.exercise.kalah.data.entities.KalahPlayerEntity;
import com.backbase.exercise.kalah.data.repository.KalahPlayerRepo;

/**
 * @author tbansal
 */
@Service
public class KalahPlayerServiceImpl implements KalahPlayerService {

	private final KalahPlayerRepo playerRepo;

	private final KalahBoardService kalahService;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Init based on provided beans.
	 *
	 * @param playerRepo   player repo
	 * @param kalahService kalah service
	 */
	public KalahPlayerServiceImpl(KalahPlayerRepo playerRepo, KalahBoardService kalahService) {
		this.playerRepo = playerRepo;
		this.kalahService = kalahService;
	}

	@Transactional
	@Override
	public KalahPlayerDTO save(KalahPlayerDTO player) {
		Assert.notNull(player, "Provided player shouldn't be null");
		if (player.getKalah() == null) {
			final KalahBoardDTO kalah = kalahService.create();
			player.setKalah(kalah);
		}
		KalahPlayerEntity persisted = playerRepo.save(modelMapper.map(player, KalahPlayerEntity.class));
		return modelMapper.map(persisted, KalahPlayerDTO.class);
	}

	@Override
	public KalahPlayerDTO findById(UUID id) {
		Assert.notNull(id, "Provided uuid shouldn't be null");
		Optional<KalahPlayerEntity> player = playerRepo.findById(id);
		if (!player.isPresent()) {
			throw new ResourceNotFoundException(String.format("Person not found %1s", id));
		}
		return modelMapper.map(player.get(), KalahPlayerDTO.class);
	}
}
