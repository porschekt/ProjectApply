package drawing;

import game.GameMain;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import logic.Score;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class GameOverScreen extends Canvas {

	private static final Font TITLE_FONT = Font
			.loadFont(ClassLoader.getSystemResource("font/HACKED.ttf").toString(), 70);
	private static final Font SCORE_FONT = Font
			.loadFont(ClassLoader.getSystemResource("font/HACKED.ttf").toString(), 40);
	private AudioClip music = RenderableHolder.gameOverMusic;

	public GameOverScreen() {
		super(SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);

		GraphicsContext gc = this.getGraphicsContext2D();
		music.play();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFill(Color.RED);
		gc.setFont(TITLE_FONT);
		gc.fillText("GAME OVER", SceneManager.SCENE_WIDTH / 2, SceneManager.SCENE_HEIGHT / 4);
		gc.setFont(SCORE_FONT);
		gc.setFill(Color.GREENYELLOW);
		String score = "Your score is : " + Score.score;
		gc.fillText(score, SceneManager.SCENE_WIDTH / 2, SceneManager.SCENE_HEIGHT * 2 / 4);
		gc.setFill(Color.DODGERBLUE);
		gc.fillText("Press Enter to retry", SceneManager.SCENE_WIDTH / 2, SceneManager.SCENE_HEIGHT * 3 / 4);
		this.addKeyEventHandler();
	}

	private void addKeyEventHandler() {
		// TODO Fill Code

		this.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				// System.out.println(event.getCode());
				if (event.getCode() == KeyCode.ENTER) {
					music.stop();
					GameMain.newGame();
				} else if (event.getCode() == KeyCode.ESCAPE) {
					Platform.exit();
				}
			}
		});
	}

}
