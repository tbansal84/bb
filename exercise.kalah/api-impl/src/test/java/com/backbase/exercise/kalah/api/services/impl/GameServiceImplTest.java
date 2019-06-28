package com.backbase.exercise.kalah.api.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.backbase.exercise.kalah.api.services.KalahBoardService;
import com.backbase.exercise.kalah.api.services.KalahPlayerService;
import com.backbase.exercise.kalah.data.entities.KalahGameDTO;
import com.backbase.exercise.kalah.data.entities.KalahGameEntity;
import com.backbase.exercise.kalah.data.repository.KalahGameRepository;

/**
 * Test class for {@link GameServiceImpl}.
 *
 * @author tbansal
 */
public class GameServiceImplTest {
	@Autowired
	private KalahGameServiceImpl kalahGameServiceImpl;
	@Autowired
	private ModelMapper modelMapper;

	@Mock
	private KalahGameRepository kalahGameRepository;

	@Mock
	private KalahPlayerService kalahPlayerService;

	@Mock
	private KalahBoardService kalahBoardService;

	public GameServiceImplTest() {
	}

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testEnterToGame() throws Exception {
		// TODO write the rest unit tests
	}

	@Test
	public void testFindById() throws Exception {
		KalahGameEntity game = new KalahGameEntity();
		final UUID id = UUID.randomUUID();

		Mockito.when(kalahGameRepository.findById(id)).thenReturn(Optional.of(game));

		final KalahGameDTO returnedGame = kalahGameServiceImpl.findById(id);

		Mockito.verify(kalahGameRepository).findById(id);

		Assert.assertEquals(modelMapper.map(game, KalahGameDTO.class), returnedGame);
	}

	@Test
	public void testSave() throws Exception {
	}

	@Test
	public void testPlay() throws Exception {
	}

}