package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public class RenderableHolder {//the picture class maker and we use this to make instead o
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	// various image plz check the image first before using (like to find its size /
	// how it looks etc)
	public static Image dragon, eSemiBoss, eBoss, eGhost, eSpiriteFire, eMachine, eTree, bullet, backgroundMM, backgroundW,
			bossBullet, roundBulletB, roundBulletY, roundBulletR, roundBulletP, powerAttack, exploArr[], background, randomBox;
	public static AudioClip bgm, laser, explosion, explosion2, gameOverMusic, mainMenuMusic,  powerAttackLaunch, gameWinnerMusic;
	public static Font inGameFont, inGameFontSmall;

	public static AudioClip[] explosions;

	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = Collections.synchronizedList(new ArrayList<>());
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ()) {
				return 1;
			}
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		dragon = new Image(ClassLoader.getSystemResource("player/p1.gif").toString());
		//player picture

		eSemiBoss = new Image(ClassLoader.getSystemResource("enemy/eSemiBoss.gif").toString());
		eBoss = new Image(ClassLoader.getSystemResource("enemy/eBoss.gif").toString());
		eGhost = new Image(ClassLoader.getSystemResource("enemy/eGhost.gif").toString());
		eSpiriteFire = new Image(ClassLoader.getSystemResource("enemy/eSpiriteFire.gif").toString());
		eMachine = new Image(ClassLoader.getSystemResource("enemy/eMachine.gif").toString());
		eTree = new Image(ClassLoader.getSystemResource("enemy/eTree.gif").toString());
		//mob picture
		exploArr = new Image[12];
		for (int i = 0; i < 12; i++) {
			exploArr[i] = new Image(ClassLoader.getSystemResource("explosion/explosion/" + i + ".gif").toString());
		}
		//explore loop from sprite sheet that was cut before

		bullet = new Image(ClassLoader.getSystemResource("bullet/bullet.png").toString());
		powerAttack = new Image(ClassLoader.getSystemResource("bullet/fireball.gif").toString());
		bossBullet = new Image(ClassLoader.getSystemResource("bullet/bossBullet.gif").toString());
		roundBulletB = new Image(ClassLoader.getSystemResource("bullet/roundBulletB.png").toString());
		roundBulletY = new Image(ClassLoader.getSystemResource("bullet/roundBulletY.png").toString());
		roundBulletR = new Image(ClassLoader.getSystemResource("bullet/roundBulletR.png").toString());
		roundBulletP = new Image(ClassLoader.getSystemResource("bullet/roundBulletP.png").toString());
		//bullet picture
		background = new Image(ClassLoader.getSystemResource("background/bg2.png").toString());
		backgroundMM = new Image(ClassLoader.getSystemResource("background/DragonCave.jpg").toString());
		backgroundW = new Image(ClassLoader.getSystemResource("background/Cave.jpg").toString());
		//background of 3 screen
		randomBox= new Image(ClassLoader.getSystemResource("items/randomBox.png").toString());
		//item box picture
		bgm = new AudioClip(ClassLoader.getSystemResource("song/GameScene.mp3").toExternalForm());
		laser = new AudioClip(ClassLoader.getSystemResource("song/Fire_Ball.mp3").toExternalForm());
		laser.setVolume(0.35);
		gameWinnerMusic = new AudioClip(ClassLoader.getSystemResource("song/Winner.mp3").toExternalForm());
		gameOverMusic = new AudioClip(ClassLoader.getSystemResource("song/GameOver.mp3").toExternalForm());
		mainMenuMusic = new AudioClip(ClassLoader.getSystemResource("song/MainMenu.mp3").toExternalForm());
		explosion = new AudioClip(ClassLoader.getSystemResource("song/Explosion.wav").toExternalForm());
		explosion2 = new AudioClip(ClassLoader.getSystemResource("song/Explosion2.wav").toExternalForm());
		powerAttackLaunch = new AudioClip(ClassLoader.getSystemResource("song/PowerAttack.mp3").toExternalForm());

		explosions = new AudioClip[] { explosion, explosion2 };
		//sound effect
		inGameFont = Font.loadFont(ClassLoader.getSystemResource("font/Winner.otf").toString(), 40);
		inGameFontSmall = Font.loadFont(ClassLoader.getSystemResource("font/Winner.otf").toString(), 20);
	}

	public void add(IRenderable entity) {
		// System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}

	public void clear() {
		entities.clear();
	}

}
