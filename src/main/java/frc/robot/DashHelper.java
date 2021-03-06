package frc.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
//import frc.robot.command.music.MusicCommand;
import frc.robot.subsystem.*;
import java.util.Map;
import com.ctre.phoenix.music.Orchestra;

public class DashHelper {
    public DriveSubsystem drive;
    public ADIS16448_IMU gyro;
    public PowerDistributionPanel pdp;
    public Encoder encoder;
    public UsbCamera camera;
    public double robotSpeed;
    public double turnSpeed;
    public boolean music;
    private static frc.robot.DashHelper dash;
    public NetworkTableEntry maxSpeed;
    public NetworkTableEntry robotTurnSpeed;
    public NetworkTableEntry musicButton;
    public WPI_TalonFX motorLeftBack;
    public NetworkTableEntry light;
    public boolean musicMode;
    public NetworkTableEntry songSelection;
    public boolean initialSongValue;
    public NetworkTableEntry encoderValue;
    public double encoderDistance;
    public Orchestra orchestra;
    public String songToPlay;
    public SendableChooser selectSong;
    public ComplexWidget songChooser;


    public static frc.robot.DashHelper getInstance(){
        // DashHelper is a singleton, only one object can exist
        if(dash == null){
            dash = new frc.robot.DashHelper();
            dash.startDashboard();
        }
        return dash;
    }

    private void startDashboard(){

        initialSongValue = false;
        musicButton = Shuffleboard.getTab("Main").addPersistent("Music Button", music).withWidget(BuiltInWidgets.kToggleButton).getEntry();
        System.out.println(musicButton.getBoolean(false) + " = dashboard music");

        if (musicButton.getBoolean(false )) {
            selectSong = new SendableChooser<>();
            selectSong.addOption("Splatoon Theme", "Splatoon theme");
            selectSong.addOption("Still Alive", "StillAlive");
            selectSong.addOption("Wii Sports", "WiiSports");
            selectSong.addOption("Hopes And Dreams", "HopesAndDreams");
            selectSong.addOption("Monty On The Run", "MontyOnTheRun");
            selectSong.addOption("Nyan Cat", "NyanCat");
            selectSong.addOption("Still Alive (Piano)", "StillAlivePiano" );
            selectSong.addOption("Bad Apple", "BadApple");
            selectSong.addOption("Coconut Mall", "CoconutMall");
            songChooser = Shuffleboard.getTab("Main").add("Select Song", selectSong).withSize(8, 1).withPosition(0, 1).withWidget(BuiltInWidgets.kSplitButtonChooser);
            selectSong.getSelected();
            //musicCommand = Shuffleboard.getTab("Main").add("Music Command", MusicCommand(orchestra, songToPlay)).withWidget(BuiltInWidgets.kCommand).
        }
        if (!musicButton.getBoolean(false )) {
            maxSpeed = Shuffleboard.getTab("Main").addPersistent("Robot Speed", robotSpeed).withWidget(BuiltInWidgets.kNumberSlider)
                .withProperties(Map.of("min", 0, "max", 1)).getEntry();

            robotTurnSpeed = Shuffleboard.getTab("Main").addPersistent("Turn Speed", turnSpeed).withWidget(BuiltInWidgets.kNumberSlider)
                .withProperties(Map.of("min", 0, "max", 1)).getEntry();

        //when this is called, drive is null, so not working

        /*encoderDistance = drive.getEncoderInchesLeftBack();
        encoderValue = Shuffleboard.getTab("Main").add("Encoder Distance", encoderDistance).getEntry(); */
        }


        //songSelection = Shuffleboard.getTab("Main").addPersistent("Song", "Megalovania").getEntry();

        /*light = Shuffleboard.getTab("Main").add("Light", false)
                .withWidget("Boolean Box")
                .withProperties(Map.of("colorWhenTrue", "green", "colorWhenFalse", "red")).getEntry();*/


    }

    public void setUpNyanCat(Orchestra orchestraName) {
        orchestraName.stop();
        orchestraName.loadMusic("NyanCat.chrp");
        orchestraName.play();
    }

    public boolean getMusicMode() {
        musicMode = musicButton.getBoolean(false);
        System.out.println(musicMode);
        return musicMode;
    }


    public void setUpCamera(UsbCamera camera) {
        Shuffleboard.getTab("Main").add("Camera", camera).withWidget(BuiltInWidgets.kCameraStream);
    }

    public void setUpEncoderWidget(double encoderDistance) {
        encoderValue = Shuffleboard.getTab("Main").add("Encoder Distance", encoderDistance)
                .withWidget(BuiltInWidgets.kTextView).getEntry();
    }

    public void setUpGyroWidget(ADIS16448_IMU gyro) {
        Shuffleboard.getTab("Main").add("Gyro", gyro).withWidget(BuiltInWidgets.kGyro);
    }

    public void setUpPDPWidget(PowerDistributionPanel pdp) {
        Shuffleboard.getTab("Main").add("PDP", pdp).withWidget(BuiltInWidgets.kPowerDistributionPanel);
    }

    public void setPokemon() {

    }

    public void setStillAlive() {
        Shuffleboard.getTab("Main").add("Still Alive", initialSongValue).withWidget(BuiltInWidgets.kToggleSwitch);
    }

  /*  public void setUpLightOn(){
        Shuffleboard.getTab("Main").add("Light", false);
    }

    public void setUpLightEnabled(){
        Shuffleboard.getTab("Main").add("Light", true);
    }

    /*public void lightOn(Color color){
        light.setDouble(color.red);
    }
    public void lightEnabled(Color color){
        light.setDouble(color.green);
    }*/
}