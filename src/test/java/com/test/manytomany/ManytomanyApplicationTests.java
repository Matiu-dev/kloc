package com.test.manytomany;

import com.test.manytomany.chesspiecerules.CheckAttack;
import com.test.manytomany.controller.BoardController;
import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.MoveStatus;
import com.test.manytomany.model.MoveType;
import com.test.manytomany.model.PlayerBoard.Color;
import com.test.manytomany.service.BoardService;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ManytomanyApplicationTests {

	@Autowired
	private BoardService boardService;

	private static GamePlay pionekDoPrzodu;
	private static GamePlay atakNaPole;

	@BeforeAll
	static void initialize() {

		String[][] pionekDoPrzoduFOB = {
				null,
				{null, "♖", "♙", "", "", "", "", "♟", "♜"},
				{null, "♘", "♙", "", "", "", "", "♟", "♞"},
				{null, "♗", "♙", "", "", "", "", "♟", "♝"},
				{null, "♕", "♙", "", "", "", "", "♟", "♛"},
				{null, "♔", "♙", "", "", "", "", "♟", "♚"},
				{null, "♗", "♙", "", "", "", "", "♟", "♝"},
				{null, "♘", "♙", "", "", "", "", "♟", "♞"},
				{null, "♖", "♙", "", "", "", "", "♟", "♜"},
		};
		pionekDoPrzodu = new GamePlay();
		pionekDoPrzodu.setGameId("154");
		pionekDoPrzodu.setBoardId("303");
		pionekDoPrzodu.setPlayerId("1");
		pionekDoPrzodu.setCoordinateOld("57");
		pionekDoPrzodu.setFigureNameOld("♟");
		pionekDoPrzodu.setCoordinateNew("56");
		pionekDoPrzodu.setFigureNameNew("");
		pionekDoPrzodu.setMoveType(MoveType.BASIC);
		pionekDoPrzodu.setGameResult(null);
		pionekDoPrzodu.setNextMoveColor(Color.WHITE);
		pionekDoPrzodu.setFiguresOnBoard(pionekDoPrzoduFOB);

		String[][] atakNaPoleFOB = {
				null,
				{null, "♖", "♙", "", "", "", "", "♟", "♜"},
				{null, "♘", "♙", "", "", "", "", "♟", "♞"},
				{null, "♗", "♙", "", "", "", "", "♟", "♝"},
				{null, "♕", "♙", "", "", "", "", "♟", "♛"},
				{null, "♔", "♙", "", "", "", "", "♟", "♚"},
				{null, "♗", "♙", "", "", "", "", "♟", "♝"},
				{null, "♘", "♙", "", "", "", "", "♟", "♞"},
				{null, "♖", "♙", "", "", "", "", "♟", "♜"},
		};
		atakNaPole = new GamePlay();
		atakNaPole.setGameId("154");
		atakNaPole.setBoardId("303");
		atakNaPole.setPlayerId("1");
		atakNaPole.setCoordinateOld("57");
		atakNaPole.setFigureNameOld("♟");
		atakNaPole.setCoordinateNew("56");
		atakNaPole.setFigureNameNew("♟");
		atakNaPole.setMoveType(MoveType.BASIC);
		atakNaPole.setGameResult(null);
		atakNaPole.setNextMoveColor(Color.WHITE);
		atakNaPole.setFiguresOnBoard(atakNaPoleFOB);

		boolean[] castling = new boolean[3];
		for(int i = 0; i < 2; i++) {
			castling[i]= true;
		}
		atakNaPole.setCastling(castling);
	}

	@Test
	void contextLoads() throws Exception {

//		BoardService boardService = new BoardService();
//		assertEquals(MoveStatus.OK, boardService.makeAMove(pionekDoPrzodu).getMoveStatus());

//		CheckAttack checkAttack = new CheckAttack();
//		assertEquals(false, checkAttack.checkAttackOnWhiteKingByBlackFigures(atakNaPole, "51"));
	}

}
