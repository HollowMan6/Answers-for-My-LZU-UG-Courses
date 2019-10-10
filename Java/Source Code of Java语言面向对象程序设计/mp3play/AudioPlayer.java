import java.io.File;
import java.io.InputStream;
import java.net.URL;
public interface AudioPlayer{
  abstract public void play(InputStream in);
  abstract public void play(File file);
  abstract public void play(URL url);
}
