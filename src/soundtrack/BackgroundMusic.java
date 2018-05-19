package soundtrack;



import javafx.concurrent.Task;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import sharedObject.RenderableHolder;

public class BackgroundMusic {
	private Thread bgmLoop;
	private AudioClip bgm;
	final Task task;
	
	public BackgroundMusic() {
		task = new Task() {
			@Override
			protected Object call() throws Exception {
				bgm = RenderableHolder.bgm;
				bgm.setVolume(1.5);
				bgm.setCycleCount(MediaPlayer.INDEFINITE);
				bgm.play();
				return null;
			}
		};
	}

	public void startBackgroundMusic() {
		bgmLoop = new Thread(task,"Game BGM Thread");
		bgmLoop.start();
	}
	
	public void stopBackgroundMusic() {
		bgm.stop();
	}

}
