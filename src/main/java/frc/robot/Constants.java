package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

public class Constants {
    public static final double DT = 0.02;
    public static final double GYRO_DEAD_ZONE = 1;

    public static final double ksVolts = 0.597;
    public static final double kvVoltSecondsPerMeter = 2.37;
    public static final double kaVoltSecondsSquaredPerMeter = 0.251;

    public static final double kPDriveVel = 9.99;

    public static final double kTrackwidthMeters = 0.57785;
    public static final DifferentialDriveKinematics kDriveKinematics =
        new DifferentialDriveKinematics(kTrackwidthMeters);

    public static final double kMaxSpeedMetersPerSecond = 1;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;

    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;
}