package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	// various image plz check the image first before using (like to find its size /
	// how it looks etc)
	public static Image dragon1, eBig, eBoss, eEyeball, eBug, eSquid, eWing, bullet, backgroundMM, backgroundMM2,
			bossBullet, roundBulletB, roundBulletY, roundBulletR, roundBulletP, missile, exploArr[], background,randomBox, backgroundGS, backgroundGO;
	public static AudioClip bgm, laser, explosion, explosion2, gameOverMusic, mainMenuMusic, missileLaunch;
	public static Font inGameFont, inGameFontSmall;

	public static Image[] dragon;
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
		dragon1 = new Image(ClassLoader.getSystemResource("player/p1.gif").toString());

		dragon = new Image[] { dragon1 }; // if add more dragon

		eBig = new Image(ClassLoader.getSystemResource("enemy/eBig.gif").toString());
		eBoss = new Image(ClassLoader.getSystemResource("enemy/eBoss.gif").toString());
		eEyeball = new Image(ClassLoader.getSystemResource("enemy/eEyeball.gif").toString());
		eBug = new Image(ClassLoader.getSystemResource("enemy/eBug.gif").toString());
		eSquid = new Image(ClassLoader.getSystemResource("enemy/eSquid.gif").toString());
		eWing = new Image(ClassLoader.getSystemResource("enemy/eWing.gif").toString());

		exploArr = new Image[12];
		for (int i = 0; i < 12; i++) {
			exploArr[i] = new Image(ClassLoader.getSystemResource("explosion/explosion/" + i + ".gif").toString());
		}

		bullet = new Image(ClassLoader.getSystemResource("bullet/bullet.png").toString());
		missile = new Image(ClassLoader.getSystemResource("bullet/missile.png").toString());
		bossBullet = new Image(ClassLoader.getSystemResource("bullet/bossBullet.gif").toString());
		roundBulletB = new Image(ClassLoader.getSystemResource("bullet/roundBulletB.png").toString());
		roundBulletY = new Image(ClassLoader.getSystemResource("bullet/roundBulletY.png").toString());
		roundBulletR = new Image(ClassLoader.getSystemResource("bullet/roundBulletR.png").toString());
		roundBulletP = new Image(ClassLoader.getSystemResource("bullet/roundBulletP.png").toString());

		background = new Image(ClassLoader.getSystemResource("background/bg2.png").toString());
		backgroundMM = new Image(ClassLoader.getSystemResource("background/DragonCave.jpg").toString());
//		backgroundMM2 = new Image(ClassLoader.getSystemResource("background/Title_Dragon.png").toString());
		
		randomBox= new Image(ClassLoader.getSystemResource("items/randomBox.png").toString());

		bgm = new AudioClip(ClassLoader.getSystemResource("song/GameScene.mp3").toExternalForm());
		laser = new AudioClip(ClassLoader.getSystemResource("song/laser.wav").toExternalForm());
		laser.setVolume(0.35);
		gameOverMusic = new AudioClip(ClassLoader.getSystemResource("song/GameOver.mp3").toExternalForm());
		mainMenuMusic = new AudioClip(ClassLoader.getSystemResource("song/MainMenu.mp3").toExternalForm());
		explosion = new AudioClip(ClassLoader.getSystemResource("song/Explosion.wav").toExternalForm());
		explosion2 = new AudioClip(ClassLoader.getSystemResource("song/Explosion2.wav").toExternalForm());
		missileLaunch = new AudioClip(ClassLoader.getSystemResource("song/MissileLaunch.wav").toExternalForm());

		explosions = new AudioClip[] { explosion, explosion2 };

		inGameFont = Font.loadFont(ClassLoader.getSystemResource("font/Digital_tech.otf").toString(), 40);
		inGameFontSmall = Font.loadFont(ClassLoader.getSystemResource("font/Digital_tech.otf").toString(), 20);
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
