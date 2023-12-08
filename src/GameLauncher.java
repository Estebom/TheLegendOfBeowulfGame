import javax.swing.*;
import javax.sound.sampled.*;
import java.io.IOException;

public class GameLauncher {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        SwingUtilities.invokeLater(() -> MainDisplay.getInstance());

    }
}
