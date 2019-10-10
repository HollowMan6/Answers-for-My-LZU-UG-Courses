import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class Mp3Player  implements  AudioPlayer {
  static private final byte[] AUDIO_BUFER;
  private AudioInputStream audioStream;
  private AudioFormat decodedFormat;
  private AudioInputStream decodedAudioStream;
  private SourceDataLine line;
  static  {
    AUDIO_BUFER = new byte[4096];
  }
  public Mp3Player() {
  }
  public void play(InputStream input) {
    if (input == null) {
       System.out.println("input is null");
       return;
    }
    try {
      init(input);
      play();
    }
    catch (Exception e) {
      System.err.println("error:"+e);
    }
  }
  public void play(File file) {
    if (file == null) {
      System.out.println("file is null");
      return;
    }
    try {
      play(new FileInputStream(file));
    }
    catch (FileNotFoundException e) {
      System.out.println("error: "+e);
    }
  }
  public void play(URL url) {
    if (url == null) {
      System.out.println("url is null");
      return;
    }
    try {
      play(url.openStream());
    }
    catch (IOException e) {
      System.err.println("url open error:"+e);
    }
  }
  protected void init(InputStream input) throws UnsupportedAudioFileException, IOException {
    initAudioStream(input);
    initDecodedFormat();
    initDecodedAudioStream();
  }
  protected void initAudioStream(InputStream input) throws UnsupportedAudioFileException, IOException {
    audioStream = AudioSystem.getAudioInputStream(input);
  }
protected void initDecodedFormat() throws UnsupportedAudioFileException, IOException {
        AudioFormat baseFormat = audioStream.getFormat();
        decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
baseFormat.getChannels(), baseFormat.getChannels() * 2,
baseFormat.getSampleRate(), false);
    }
  protected void initDecodedAudioStream() {
    decodedAudioStream = AudioSystem.getAudioInputStream(decodedFormat, audioStream);
  }

  protected SourceDataLine getSourceDataLine() throws UnsupportedAudioFileException, IOException,
                                                LineUnavailableException {
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
        line.open(decodedFormat);
        return line;
    }
  public void stop()throws UnsupportedAudioFileException, IOException, LineUnavailableException{
     if(line!=null) {
        line.drain();
        line.stop();
        line.close();
        decodedAudioStream.close();
        audioStream.close();
     }
  }
  protected void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//    SourceDataLine line;
    int readLenth;
    line = getSourceDataLine();
    line.start();
    readLenth = 0;
    while (readLenth != -1) {
      readLenth = decodedAudioStream.read(AUDIO_BUFER, 0, AUDIO_BUFER.length);
      if (readLenth != -1) {
        line.write(AUDIO_BUFER, 0, readLenth);
      }
    }
    line.drain();
    line.stop();
    line.close();
    decodedAudioStream.close();
    audioStream.close();
  }
}
