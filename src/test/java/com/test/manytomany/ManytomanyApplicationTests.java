package com.test.manytomany;

import com.google.gson.GsonBuilder;
import com.test.manytomany.chesspiecerules.CheckAttack;
import com.test.manytomany.controller.BoardController;
import com.test.manytomany.controller.Foo;
import com.test.manytomany.model.ChatMessage;
import com.test.manytomany.model.GamePlay;
import com.test.manytomany.model.MoveStatus;
import com.test.manytomany.model.MoveType;
import com.test.manytomany.model.PlayerBoard.Color;
import com.test.manytomany.model.chat.Chat;
import com.test.manytomany.service.BoardService;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

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
//		pionekDoPrzodu.setGameId("154");
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
//		atakNaPole.setGameId("154");
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

		String path = System.getProperty("user.dir") + "/src/test/resources/json/gameplay.json";
		String eventString = loadJsonFile(path);
		System.out.println(eventString);

		GsonBuilder builder = new GsonBuilder();
		Foo o = builder.create().fromJson(eventString, Foo.class);

		System.out.println(o.getType());

//		if(o instanceof GamePlay){
//			System.out.println("gameplay");
//		} else {
//			System.out.println("nie gameplay");
//		}
//
//		if(o instanceof ChatMessage) {
//			System.out.println("chatmessage");
//		} else {
//			System.out.println("nie chatmessage");
//		}

//		Type fooTypeGamePlay = new TypeToken<Foo<GamePlay>>() {}.getType();
//        Type fooTypeChatMessage = new TypeToken<Foo<ChatMessage>>() {}.getType();

	}

	private static String loadJsonFile(final String path) {
		StringBuilder stringBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
			stream.forEach(s -> stringBuilder.append(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
}
