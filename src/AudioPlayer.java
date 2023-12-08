import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private static Clip clip = null;

    public static void startLoopingSound(String filePath) {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }

            File file = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                java.lang.System.err.println("Line not supported");
                return;
            }

            clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void setVolumeForGame(float volumePercent) {
        setVolume(MainDisplay.getGameSoundClip(), volumePercent);
    }

    // Method to set volume for music
    public static void setVolumeForMusic(float volumePercent) {

        setVolume(MainDisplay.getGameSoundClip(), volumePercent);
    }

    public static void setVolume(Clip clip, float volumePercent) {
        if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            try {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                // Convert volumePercent to a value in decibels.
                // Assuming volumePercent is a value between 0.0 and 1.0
                float dB = (float) (Math.log(volumePercent) / Math.log(10.0) * 20.0);
                gainControl.setValue(dB);
                java.lang.System.out.println("Volume set to " + dB + " dB for clip: " + clip); // Debugging line
            } catch (IllegalArgumentException ex) {
                java.lang.System.err.println("Volume adjustment failed: " + ex.getMessage());
            }
        } else {
            java.lang.System.err.println("Volume control not supported for this clip.");
        }
    }

    public static void stopSound() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}
