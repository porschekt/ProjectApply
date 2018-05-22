package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

import drawing.GameScreen;
import game.GameMain;
import sharedObject.RenderableHolder;
import window.SceneManager;

public class GameLogic {

	private Queue<Bullet> pendingBullet;

	private List<Entity> gameObjectContainer;
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;

	private int gameOverCountdown = 24;
	private int gameWinCountdown = 24;

	private int maxEnemyCap;
	public static int currentEnemyNum;
	public static boolean isBossAlive;
	public static boolean isBigAlive;
	public static boolean killedBoss;
	private int stageLevel;

	private long nextItemsSpawnTime;

	private GameScreen canvas;
	private boolean isGameRunning;

	private Player player;
	private EBig ebig;
	private EBoss eboss;
	private EBug ebug;
	private Enemy enemy;

	public GameLogic(GameScreen canvas) {
		this.gameObjectContainer = new ArrayList<Entity>();
		this.maxEnemyCap = 5; // default enemy capacity
		GameLogic.currentEnemyNum = 0;
		stageLevel = 1;
		GameLogic.isBossAlive = false;
		GameLogic.isBigAlive = false;
		killedBoss=false;
		

		RenderableHolder.getInstance().add(new Background());
		RenderableHolder.getInstance().add(new Score());
		player = new Player(this);
		addNewObject(player);

		spawnEnemy();

		this.canvas = canvas;
		nextItemsSpawnTime = System.nanoTime() + ThreadLocalRandom.current().nextLong(5000000000l, 15000000000l);
		pendingBullet = new ConcurrentLinkedQueue<>();

	}
	
	protected void addNewObject(Entity entity) {
		gameObjectContainer.add(entity);
		RenderableHolder.getInstance().add(entity);
	}
	
	protected void winGame() {
		this.isGameRunning = false;
		this.gameObjectContainer.clear();
		this.pendingBullet.clear();
	}

	public void startGame() {
		this.isGameRunning = true;
		new Thread(this::gameLoop, "Game Loop Thread").start();
	}

	public void stopGame() {
		this.isGameRunning = false;
		this.gameObjectContainer.clear();
		this.pendingBullet.clear();

	}

	private void gameLoop() {
		long lastLoopStartTime = System.nanoTime();
		while (isGameRunning) {
			long elapsedTime = System.nanoTime() - lastLoopStartTime;
			if (elapsedTime >= LOOP_TIME) {
				lastLoopStartTime += LOOP_TIME;

				updateGame();
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateGame() {
		// TODO fill code
		spawnEnemy();
		spawnItems();

		while (!pendingBullet.isEmpty()) {
			gameObjectContainer.add(pendingBullet.poll());

		}
		// System.out.println("Number of gameObject\t" + gameObjectContainer.size());

		for (Entity i : gameObjectContainer) {
			i.update();
		}
		for (Entity i : gameObjectContainer) {
			for (Entity j : gameObjectContainer) {
				if (i != j && ((CollidableEntity) i).collideWith((CollidableEntity) j)) {
					((CollidableEntity) i).onCollision((CollidableEntity) j);
				}
			}
		}
		int i = 0;
		while (i < gameObjectContainer.size()) {
			if (gameObjectContainer.get(i).isDestroyed()) {
				gameObjectContainer.remove(i);
			} else {
				i++;
			}
		}
		if (player.isDestroyed()) {
			gameOverCountdown--;
		}
		if(killedBoss) {
			gameWinCountdown--;
		}
		if (gameWinCountdown == 0) {
			GameMain.winGame();
		}
		if (gameOverCountdown == 0) {
			GameMain.loseGame();
		}

	}

	public void addPendingBullet(Bullet a) {

		pendingBullet.add(a);

		canvas.addPendingBullet(a);
	}

	private void spawnEnemy() {
		Random r = new Random();
		this.maxEnemyCap = 5 + stageLevel;
		// check score to spawn boss first
		//if didn't check it will spawn a lot of boss
		if (Score.score == 200 && !isBigAlive) {
			ebig = new EBig(this);
			addNewObject(ebig);
		}
		if (Score.score == 10 && !isBossAlive) {
			eboss = new EBoss(this);
			addNewObject(eboss);
		}
		
		if (Score.score >= 100 * stageLevel * 1.5) {
			stageLevel++;
		}

		// System.out.println("cap" + this.maxEnemyCap + " current " +
		// this.currentEnemyNum);

		if (GameLogic.currentEnemyNum < this.maxEnemyCap) {
			int chance = r.nextInt(100) - 1000 / (Score.score + 1); // difficulty factor , +1 to prevent zero when start
																	// new game
			// System.out.println(" chance " + chance);
			if (chance < 40) {
				ebug = new EBug(ThreadLocalRandom.current()
						.nextDouble(SceneManager.SCENE_WIDTH - RenderableHolder.eBug.getWidth()));
				addNewObject(ebug);
			} else if (chance < 70) {
				addNewObject(new ESquid(this, ThreadLocalRandom.current()
						.nextDouble(SceneManager.SCENE_WIDTH - RenderableHolder.eEyeball.getWidth())));
			} else if (chance < 85) {
				addNewObject(new EEyeball(this, ThreadLocalRandom.current()
						.nextDouble(SceneManager.SCENE_WIDTH - RenderableHolder.eEyeball.getWidth())));
			} else  {
				addNewObject(new EWing(this, ThreadLocalRandom.current()
						.nextDouble(SceneManager.SCENE_WIDTH - RenderableHolder.eEyeball.getWidth())));
			}
			
		}

	}

	private void spawnItems() {
		long now = System.nanoTime();
		if (this.nextItemsSpawnTime <= now) {
			long rand = ThreadLocalRandom.current().nextLong(5000000000l, 15000000000l); // random the time next Box
																							// will come out
			// System.out.println("\t\tNext Box in " + rand / 1000000000l + " seconds.");
			this.nextItemsSpawnTime = now + rand;

			double gachaPull = ThreadLocalRandom.current().nextDouble(100);
			// System.out.println("\t\tGacha: " + gachaPull);
			if (gachaPull <= 32.5) {
				addNewObject(new TripleGunBox(ThreadLocalRandom.current()
						.nextDouble(SceneManager.SCENE_WIDTH - RenderableHolder.randomBox.getWidth())));
			} else if (gachaPull <= 55) {
				addNewObject(new MissileBox(ThreadLocalRandom.current()
						.nextDouble(SceneManager.SCENE_WIDTH - RenderableHolder.randomBox.getWidth())));
			} else {
				addNewObject(new HPBox(ThreadLocalRandom.current()
						.nextDouble(SceneManager.SCENE_WIDTH - RenderableHolder.randomBox.getWidth())));
			}
		}

	}
}
