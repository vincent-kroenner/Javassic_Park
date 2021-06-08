package Tools;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class Music {
    //Attributs
    private static MediaPlayer mediaPlayer;
    private static double volume = 0.5;

    //Méthodes
    public static void startMainMenuMusic() {
        if (mediaPlayer != null) mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(new Media(Paths.get(Path.MAIN_MENU_THEME).toUri().toString()));
        mediaPlayer.setCycleCount(-1);
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();
    }

    public static void startGameMusic() {
        mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(new Media(Paths.get(Path.GAME_THEME).toUri().toString()));
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();
    }

    public static void setVolume(double volume) {
        Music.volume = volume;
        if (mediaPlayer != null) mediaPlayer.setVolume(volume);
    }


}