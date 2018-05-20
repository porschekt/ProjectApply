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
import sharedObject.RenderableHolder;
import window.SceneManager;

public class MainMenu extends Canvas {
	private static final Font TITLE_FONT = Font
			.loadFont(ClassLoader.getSystemResource("font/Mouse.otf").toString(), 50);
	private static final Font MENU_FONT = Font
			.loadFont(ClassLoader.getSystemResource("font/Mouse.otf").toString(), 35);
	private AudioClip music = RenderableHolder.mainMenuMusic;

	public MainMenu() {
		super(SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);

		music.play();
		
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.backgroundMM, 0, 0, SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
//		gc.drawImage(RenderableHolder.backgroundMM2, SceneManager.SCENE_WIDTH/2-100, SceneManager.SCENE_HEIGHT/2-170 , SceneManager.SCENE_WIDTH/3, SceneManager.SCENE_HEIGHT/3);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFill(Color.DARKRED);
		gc.setFont(TITLE_FONT);
		gc.fillText("DRAGON WING", SceneManager.SCENE_WIDTH/2, SceneManager.SCENE_HEIGHT/4);
		gc.setFont(MENU_FONT);
		gc.fillText("Press Enter to Start", SceneManager.SCENE_WIDTH/2, SceneManager.SCENE_HEIGHT * 3 / 4+35);

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
