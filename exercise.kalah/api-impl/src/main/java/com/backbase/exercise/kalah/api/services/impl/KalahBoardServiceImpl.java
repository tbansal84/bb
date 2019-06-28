package com.backbase.exercise.kalah.api.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.backbase.exercise.kalah.api.services.KalahBoardService;
import com.backbase.exercise.kalah.data.entities.KalahBoardDTO;
import com.backbase.exercise.kalah.data.entities.KalahBoardEntity;
import com.backbase.exercise.kalah.data.repository.KalahBoardRepo;

/**
 * Kalah service impl.
 *
 * @author tbansal
 */
@Service
public class KalahBoardServiceImpl implements KalahBoardService {

	private final KalahBoardRepo kalahRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Value("${kalah.pits.count}")
	private Integer pitsCount;

	@Value("${kalah.stones.count}")
	private Integer stonesCount;

	/**
	 * Initialize based on provided beans.
	 *
	 * @param kalahRepo kalah repo
	 */
	public KalahBoardServiceImpl(KalahBoardRepo kalahRepo) {
		this.kalahRepo = kalahRepo;
	}

	@Transactional
	@Override
	public KalahBoardDTO update(KalahBoardDTO kalah) {
		Assert.notNull(kalah, "Provided kalah shouldn't be null");
		KalahBoardEntity persisted = kalahRepo.save(modelMapper.map(kalah, KalahBoardEntity.class));
		return modelMapper.map(persisted, KalahBoardDTO.class);
	}

	@Transactional
	@Override
	public KalahBoardDTO create() {
		final KalahBoardDTO kalahBoard = new KalahBoardDTO(pitsCount, stonesCount);
		KalahBoardEntity persisted = kalahRepo.save(modelMapper.map(kalahBoard, KalahBoardEntity.class));
		return modelMapper.map(persisted, KalahBoardDTO.class);
	}
}
