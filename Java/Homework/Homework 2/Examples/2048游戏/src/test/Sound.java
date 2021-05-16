package test;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound{//用于播放背景音乐的类
    private Clip clip;
    public Sound(String filePath) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
	File file = new File(filePath);
	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
	clip = AudioSystem.getClip();
	clip.open(audioInputStream);
    }

    public void play() {
	clip.setFramePosition(0);
	clip.start();
    }

    public void loop() {
	clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
	clip.stop();
    }

}
