package frc.robot.command.music;

import com.ctre.phoenix.music.Orchestra;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class MusicCommand extends CommandBase {
    private Orchestra orchestra;
    private String songToPlay;
    private String currentSong = "";
    private boolean isStopped = false;
    private boolean canPlay = false;

    public MusicCommand(Orchestra robotOrchestra, String songName) {
        orchestra = robotOrchestra;
        songToPlay = songName;

        if(currentSong.equals(songName)){
            canPlay = false;
        } else {
            canPlay = true;
            currentSong = songName;
        }
    }

    @Override
    public void initialize() {
        if(canPlay){
            orchestra.stop();
            orchestra.loadMusic(songToPlay);
            orchestra.play();
        }
    }

    @Override
    public void execute() {
        isStopped = !orchestra.isPlaying();
    }

    @Override
    public boolean isFinished() {
        return isStopped;
    }
}