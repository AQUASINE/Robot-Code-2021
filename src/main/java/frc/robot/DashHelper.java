package frc.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystem.*;

public class DashHelper {
    public DriveSubsystem drive;
    public ADIS16448_IMU gyro;
    public PowerDistributionPanel pdp;
    public Encoder encoder;
    public UsbCamera camera;
    //private ShuffleboardTab mainTab;
    private static frc.robot.DashHelper dash;

    public static frc.robot.DashHelper getInstance(){
        // DashHelper is a singleton, only one object can exist
        if(dash == null){
            dash = new frc.robot.DashHelper();
            dash.startDashboard();
        }
        return dash;
    }

    private void startDashboard(){

        /*ComplexWidget makeGyro = Shuffleboard.getTab("Main")
                .add("Gyro", gyro)
                .withWidget(BuiltInWidgets.kGyro); */

        //mainTab.add("Gyro", gyro).withWidget(BuiltInWidgets.kGyro);
    }

    public void setUpGyroWidget(ADIS16448_IMU gyro) {
        Shuffleboard.getTab("Main").add("Gyro", gyro).withWidget(BuiltInWidgets.kGyro);
    }

    public void setUpPDPWidget(PowerDistributionPanel pdp) {
        Shuffleboard.getTab("Main").add("PDP", pdp).withWidget(BuiltInWidgets.kPowerDistributionPanel);
    }
}