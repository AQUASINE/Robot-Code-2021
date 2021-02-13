package frc.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.WidgetType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.subsystem.*;
import frc.robot.Robot;



public class DashHelper {
    private ShuffleboardTab mainDash;
    public static NetworkTableEntry sbSpeedTest;
    public static NetworkTableEntry sbGyroWidget;
    public static NetworkTableEntry sbEncoderDistance;
    public static NetworkTableEntry sbRedValue, sbGreenValue, sbBlueValue;
    public static NetworkTableEntry sbTimer;
    public static NetworkTableEntry kP;
    public static NetworkTableEntry kI;
    public static NetworkTableEntry kD;
    public static NetworkTableEntry sbServoOpen;
    private static DashHelper dash;
    public ADIS16448_IMU gyro;
    public PowerDistributionPanel pdp;
    public DriveSubsystem drive;
    public String widgetName;


    public static DashHelper getInstance(){
        // DashHelper is a singleton, only one object can exist
        if(dash == null){
            dash = new DashHelper();
            dash.startDash();
        }
        return dash;
    }

    private DashHelper(){
        // Control the dashboard stuff to initialize only once
    }

    private void startDash(){
        mainDash = Shuffleboard.getTab("Main");

        //UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        //camera.setVideoMode(VideoMode.PixelFormat.kMJPEG, 800, 600, 20 );
        //camera.setExposureAuto();
        //mainDash.add("Camera", camera);

        /*sbSpeedTest = mainDash.add("Speed", Constants.kSpeed).getEntry();
        sbTimer = mainDash.add("Timer", 0).getEntry();
        sbEncoderDistance = mainDash.add("Encoder", 0).getEntry();*/

/*      sbRedValue = mainDash.add("Red Value", 0).getEntry();
        sbGreenValue = mainDash.add("Green Value", 0).getEntry();
        sbBlueValue = mainDash.add("Blue Value", 0).getEntry();
*/
        sbServoOpen = mainDash.add("Servo open", false).getEntry();

        kP = mainDash.add("P", 0.015).getEntry();
        kI = mainDash.add("I", 0).getEntry();
        kD = mainDash.add("D", 0).getEntry();

        SmartDashboard.putNumber("2", 2);

        //SmartDashboard.putNumber("gyro angle", );
        //Shuffleboard.selectTab("Main");
        //Shuffleboard.startRecording();

    }

    public void setEncoder(double distance){
        sbEncoderDistance.setDouble(distance);
    }

    //public void setUpEncoder()

    public void setColor(Color color){
        sbRedValue.setDouble(color.red);
        sbGreenValue.setDouble(color.green);
        sbBlueValue.setDouble(color.blue);
    }

    public void setUpGyroWidget(ADIS16448_IMU gyro){
        //mainDash.add("Gyro", gyro).withWidget(BuiltInWidgets.kGyro);
        SmartDashboard.putNumber("Gyro", gyro.getAngle());
        //System.out.println("Tried to set up gyro");
    }

    public void setUpEncoderWidget(DriveSubsystem drive){
        mainDash.add("Encoder", drive.getEncoderValueLeftBack()).withWidget(BuiltInWidgets.kEncoder);
        //System.out.println("Tried to set up encoder");
    }

    /*public void setUpCamera(CameraServer camera){
        mainDash.add("Camera", camera)
    }*/

    //not using mecanum
    /*public void setUpMechDriveWidget(MecanumDrive mechDrive){
        mainDash.add("Mecanum Drive", mechDrive).withWidget(BuiltInWidgets.kMecanumDrive);
    }*/

    public void setUpPDPWidget(PowerDistributionPanel pdp){
        mainDash.add("PDP", pdp).withWidget(BuiltInWidgets.kPowerDistributionPanel);
        //System.out.println("Tried to set up pdp widget");
    }

    public void setTimer(Timer timer){
        sbTimer.setDouble(timer.get());
    }

}