import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class System {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        SwingUtilities.invokeLater(() -> MainDisplay.getInstance());

    }
}
