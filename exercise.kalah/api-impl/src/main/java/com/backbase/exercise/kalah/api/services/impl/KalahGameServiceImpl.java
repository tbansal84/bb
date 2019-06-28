package com.backbase.exercise.kalah.api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.backbase.exercise.kalah.api.exceptions.EmptyPitException;
import com.backbase.exercise.kalah.api.exceptions.ResourceNotFoundException;
import com.backbase.exercise.kalah.api.exceptions.RightToPlayException;
import com.backbase.exercise.kalah.api.services.KalahGameService;
import com.backbase.exercise.kalah.api.services.KalahBoardService;
import com.backbase.exercise.kalah.api.services.KalahPlayerService;
import com.backbase.exercise.kalah.data.entities.KalahGameDTO;
import com.backbase.exercise.kalah.data.entities.KalahGameEntity;
import com.backbase.exercise.kalah.data.entities.KalahPitDTO;
import com.backbase.exercise.kalah.data.entities.KalahBoardDTO;
import com.backbase.exercise.kalah.data.entities.KalahPlayerDTO;
import com.backbase.exercise.kalah.data.repository.KalahGameRepository;

/**
 * Game service implementation.
 *
 * @author tbansal
 */
@Service
public class KalahGameServiceImpl implements KalahGameService {
	@Autowired
	private KalahGameRepository kalahGameRepository;
	@Autowired
	private KalahPlayerService kalahPlayerService;
	@Autowired
	private KalahBoardService kalahBoardService;

	@Autowired
	private ModelMapper modelMapper;

	@Value("${kalah.pits.count}")
	private Integer pitsCount;

	/**
	 * Shows weather player turn should be changed or not.
	 *
	 * @param position current position
	 * @param stones   stones in that position
	 * @return {@code true} if turn should be changed, otherwise {@code false}
	 */
	private boolean isTurnPlayer(Integer position, Integer stones) {
		// one full iteration is double size of one player Kalah plus 2 as home for each
		// player
		final int oneFullIteration = pitsCount * 2 + 2;
		int i = 0;
		while (oneFullIteration * i < stones) {
			if (stones - oneFullIteration * i == pitsCount - position) {
				return false;
			}

			i++;
		}

		return true;
	}

	/**
	 * Play in players Kalah.
	 *
	 * @param position      position from which should start game
	 * @param stones        stones count
	 * @param kalahBoard    players Kalah
	 * @param opponentKalah opponents Kalah
	 * @param isPlayerSide  shows weather you are in players side
	 */
	private Integer playInKalah(Integer position, Integer stones, KalahBoardDTO kalahBoard, KalahBoardDTO opponentKalah,
			boolean isPlayerSide) {
		final List<KalahPitDTO> pits = kalahBoard.getPits();

		while (stones > 0 && position <= pitsCount) {
			// by each iteration stones count should decrement by 1
			stones--;

			if (position.equals(pitsCount)) {
				kalahBoard.incrementHome(1);
			} else {
				final int opponentReversPitIndex = pitsCount - 1 - position;
				final Integer opponentStonesCount = opponentKalah.getPitStonesCount(opponentReversPitIndex);

				if (isPlayerSide && stones == 0 && getNoOfStonesInPosition(position, pits) == 0
						&& opponentStonesCount > 0) {
					opponentKalah.emptyPit(opponentReversPitIndex);
					kalahBoard.incrementHome(opponentStonesCount + 1);
				} else {
					incrementStoneCountInPit(kalahBoard, position);
				}
			}

			position++;
		}

		return stones;
	}

	private void incrementStoneCountInPit(KalahBoardDTO board, int position) {
		Optional<KalahPitDTO> targetPit = board.getPits().stream().filter(p -> p.getPosition().equals(position))
				.findAny();
		targetPit.ifPresent(p -> p.setNoOfStones(p.getNoOfStones() + 1));
	}

	@Transactional
	@Override
	public KalahGameDTO enterToGame() {
		// Create new Player every time when someone want to start new game
		final KalahPlayerDTO player = new KalahPlayerDTO();

		final KalahGameDTO existingGame = modelMapper
				.map(kalahGameRepository.findFirstByPlayer1IsNotNullAndPlayer2IsNull(), KalahGameDTO.class);
		final KalahGameDTO game;
		if (existingGame == null) {
			game = new KalahGameDTO();
			player.setMyTurn(true);
			final KalahPlayerDTO createdPlayer = kalahPlayerService.save(player);
			game.setPlayer1(createdPlayer);
		} else {
			player.setMyTurn(false);
			game = existingGame;
			final KalahPlayerDTO createdPlayer = kalahPlayerService.save(player);
			game.setPlayer2(createdPlayer);
		}
		KalahGameEntity gameEntity = modelMapper.map(game, KalahGameEntity.class);
		return modelMapper.map(kalahGameRepository.save(gameEntity), KalahGameDTO.class);
	}

	@Override
	public KalahGameDTO findById(UUID id) {
		Assert.notNull(id, "Provided id shouldn't be null");

		final Optional<KalahGameEntity> game = kalahGameRepository.findById(id);
		if (!game.isPresent()) {
			throw new ResourceNotFoundException("Not found game with id: " + id);
		}
		return modelMapper.map(game.get(), KalahGameDTO.class);
	}

	@Override
	public KalahGameDTO findByPlayerId(UUID playerId) {
		Assert.notNull(playerId, "Provided playerId shouldn't be null");

		final KalahGameDTO game = modelMapper
				.map(kalahGameRepository.findFirstByPlayer1IdOrPlayer2Id(playerId, playerId), KalahGameDTO.class);
		if (game == null) {
			throw new ResourceNotFoundException("Couldn't find entity by player id: " + playerId);
		}

		return game;
	}

	@Override
	public KalahPlayerDTO findGameOpponentByPlayerId(UUID playerId) {
		Assert.notNull(playerId, "Provided id shouldn't be null");
		final KalahGameDTO game = findByPlayerId(playerId);

		return game.getPlayer1().getId().equals(playerId) ? game.getPlayer2() : game.getPlayer1();
	}

	@Transactional
	@Override
	public KalahGameDTO save(KalahGameDTO game) {
		Assert.notNull(game, "Provided game shouldn't be null");
		return modelMapper.map(kalahGameRepository.save(modelMapper.map(game, KalahGameEntity.class)),
				KalahGameDTO.class);
	}

	private Integer getNoOfStonesInPosition(int postion, List<KalahPitDTO> pits) {
		Optional<KalahPitDTO> targetPit = pits.stream().filter(p -> p.getPosition().equals(postion)).findAny();
		return targetPit.isPresent() ? targetPit.get().getPosition() : null;
	}

	@Transactional
	@Override
	public KalahGameDTO play(UUID playerId, Integer position) {
		Assert.notNull(position, "Provided position shouldn't be null");
		Assert.isTrue(position >= 0 && position < pitsCount,
				"Provided position shouldn't be grate then " + (pitsCount - 1));
		Assert.notNull(playerId, "Provided playerId shouldn't be null");

		final KalahPlayerDTO player = kalahPlayerService.findById(playerId);
		if (player == null) {
			throw new ResourceNotFoundException("Not found player with id: " + playerId);
		}

		if (!player.getMyTurn()) {
			throw new RightToPlayException("It's not player turn with id: " + playerId);
		}

		final KalahBoardDTO kalah = player.getKalah();

		// Find opponent
		final KalahPlayerDTO opponent = findGameOpponentByPlayerId(player.getId());
		if (opponent == null) {
			throw new ResourceNotFoundException("Not found opponent for player with id: " + playerId);
		}

		List<KalahPitDTO> pits = kalah.getPits();
		Integer stones = getNoOfStonesInPosition(position, pits);
		if (stones == 0) {
			throw new EmptyPitException(String.format("Pit with position: %d is empty", position));
		}

		// empty pit from which stones are taken
		kalah.emptyPit(position);

		// turn player or not
		final boolean turnPlayer = isTurnPlayer(position, stones);
		final KalahBoardDTO opponentKalah = opponent.getKalah();

		while (stones != 0) {
			stones = playInKalah(position + 1, stones, kalah, opponentKalah, true);

			// start play from 0/first pit
			position = 0;
			stones = playInKalah(position, stones, opponentKalah, kalah, false);
		}

		if (turnPlayer) {
			// give turn to opponent
			player.setMyTurn(false);
			kalahPlayerService.save(player);

			opponent.setMyTurn(true);
			kalahPlayerService.save(opponent);
		}

		KalahGameDTO game = findByPlayerId(player.getId());

		if (kalah.getPits().stream().allMatch(p -> p.getNoOfStones() == 0)
				|| opponentKalah.getPits().stream().allMatch(p -> p.getNoOfStones() == 0)) {
			putStonesToHome(kalah);
			putStonesToHome(opponentKalah);
			game.setFinished(true);
			game = save(game);
		}

		kalahBoardService.update(kalah);
		kalahBoardService.update(opponentKalah);

		return game;
	}

	/**
	 * Take stones from pits and put them to home.
	 *
	 * @param kalah kalah
	 */
	private void putStonesToHome(KalahBoardDTO kalah) {
		final List<KalahPitDTO> pits = kalah.getPits();
		pits.forEach(p -> {
			kalah.setHome(kalah.getHome() + p.getNoOfStones());
			p.setNoOfStones(0);
		});
	}

}
