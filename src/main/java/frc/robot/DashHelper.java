package frc.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;
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
import frc.robot.subsystem.*;

import java.util.Map;

public class DashHelper {
    public DriveSubsystem drive;
    public ADIS16448_IMU gyro;
    public PowerDistributionPanel pdp;
    public Encoder encoder;
    public UsbCamera camera;
    public double robotSpeed;
    private static frc.robot.DashHelper dash;
    public NetworkTableEntry maxSpeed;

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
    }

    public void setUpCamera(UsbCamera camera) {
        Shuffleboard.getTab("Main").add("Camera", camera).withWidget(BuiltInWidgets.kCameraStream);
    }

    public void setUpGyroWidget(ADIS16448_IMU gyro) {
        Shuffleboard.getTab("Main").add("Gyro", gyro).withWidget(BuiltInWidgets.kGyro);
    }

    public void setUpPDPWidget(PowerDistributionPanel pdp) {
        Shuffleboard.getTab("Main").add("PDP", pdp).withWidget(BuiltInWidgets.kPowerDistributionPanel);
    }
}