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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.subsystem.*;

import java.util.Map;

public class DashHelper {
    public DriveSubsystem drive;
    public ADIS16448_IMU gyro;
    public PowerDistributionPanel pdp;
    public Encoder encoder;
    public UsbCamera camera;
    public double robotSpeed;
    public boolean music;
    private static frc.robot.DashHelper dash;
    public NetworkTableEntry maxSpeed;
    public NetworkTableEntry musicButton;
    public WPI_TalonFX motorLeftBack;
    public NetworkTableEntry light;

    public static frc.robot.DashHelper getInstance(){
        // DashHelper is a singleton, only one object can exist
        if(dash == null){
            dash = new frc.robot.DashHelper();
            dash.startDashboard();
        }
        return dash;
    }

    private void startDashboard(){
        maxSpeed = Shuffleboard.getTab("Main").addPersistent("Robot Speed", robotSpeed).withWidget(BuiltInWidgets.kNumberSlider)
                .withProperties(Map.of("min", 0, "max", 1)).getEntry();

        musicButton = Shuffleboard.getTab("Main").addPersistent("Music Button", music).withWidget(BuiltInWidgets.kToggleButton).getEntry();

        /*light = Shuffleboard.getTab("Main").add("Light", false)
                .withWidget("Boolean Box")
                .withProperties(Map.of("colorWhenTrue", "green", "colorWhenFalse", "red")).getEntry();*/


    }

    /*public void setUpEncoderWidget(double encoderDistance) {
        Shuffleboard.getTab("Main").add("Encoder", encoderDistance);
    }*/

    public void setUpCamera(UsbCamera camera) {
        Shuffleboard.getTab("Main").add("Camera", camera).withWidget(BuiltInWidgets.kCameraStream);
    }

    public void setUpEncoderWidget(double encoderDistance) {
        Shuffleboard.getTab("Main").add("Encoder Distance", encoderDistance).getEntry();
    }

    public void setUpGyroWidget(ADIS16448_IMU gyro) {
        Shuffleboard.getTab("Main").add("Gyro", gyro).withWidget(BuiltInWidgets.kGyro);
    }

    public void setUpPDPWidget(PowerDistributionPanel pdp) {
        Shuffleboard.getTab("Main").add("PDP", pdp).withWidget(BuiltInWidgets.kPowerDistributionPanel);
    }

    public void setUpLightOn(){
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