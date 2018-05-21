package game;

import drawing.GameOverScreen;
import drawing.GameScreen;
import drawing.GameWinnerScreen;
import input.CharacterInput;
import javafx.application.Platform;
import logic.GameLogic;
import sharedObject.RenderableHolder;
import soundtrack.BackgroundMusic;
import window.SceneManager;

public class GameMain {

	private static GameScreen gameScreen;
	private static BackgroundMusic gameBgm;
	private static GameLogic gameLogic;
	private static GameOverScreen gameOver;
	private static GameWinnerScreen gameWinner;

	public static void newGame() {
		// TODO fill code

		gameScreen = new GameScreen(SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		gameBgm = new BackgroundMusic();
		gameLogic = new GameLogic(gameScreen);
		SceneManager.gotoSceneOf(gameScreen);
		gameLogic.startGame();
		gameScreen.startAnimation();
		gameBgm.startBackgroundMusic();
	}

	public static void stopGameLogicAndAnimation() {
		// TODO fill code
		gameScreen.stopAnimation();
		gameLogic.stopGame();
		gameBgm.stopBackgroundMusic();

	}

	public static void stopGame() {
		stopGameLogicAndAnimation();
		Platform.runLater(GameMain::displayGameOverResult);
		CharacterInput.clear();
		RenderableHolder.getInstance().clear();

	}

	private static void displayGameOverResult() {
		// TODO fill code
		gameOver = new GameOverScreen();
		SceneManager.gotoSceneOf(gameOver);
	}
	
	private static void displayGameWinnerResult() {
		// TODO fill code
		gameWinner = new GameWinnerScreen();
		SceneManager.gotoSceneOf(gameWinner);
	}

}
