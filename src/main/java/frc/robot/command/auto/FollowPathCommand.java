package frc.robot.command.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.path.PathDataModel;
import frc.robot.path.PathState;
import frc.robot.subsystem.DriveSubsystem;

public class FollowPathCommand extends CommandBase {
    private DriveSubsystem drive;
    private PathDataModel path;

    private int currentStep;
    private double currentTime;
    private int NUM_PATH_STEPS;

    private static final double DT = 0.05;

    public FollowPathCommand(DriveSubsystem drive, PathDataModel path) {
        this.drive = drive;
        this.path = path;
        currentStep = 0;
        currentTime = 0;
        NUM_PATH_STEPS = path.getPathStates().length;
        addRequirements(drive);
    }

    @Override
    public void initialize() { }

    @Override
    public void execute() {
        PathState pathState = getStep(currentStep);
        if(pathState.getTime() < currentTime) {
            currentStep++;
            if(currentStep >= NUM_PATH_STEPS) return;
            pathState = getStep(currentStep);
        }
        double angleState = drive.getGyroAngle();
        double angleTarget = pathState.getPose().getRotation().get("radians");
        double angleError = angleTarget - angleState;

        double vel = pathState.getVelocity();

        double vel_r = vel + Math.tan(angleError) * DriveSubsystem.BASE_WIDTH / 2;
        double vel_l = vel - Math.tan(angleError) * DriveSubsystem.BASE_WIDTH / 2;

        drive.setRight(vel_r);
        drive.setLeft(vel_l);
        currentTime += DT;
    }

    @Override
    public boolean isFinished() {
        return currentStep < NUM_PATH_STEPS;
    }

    private PathState getStep(int num) {
        return path.getPathStates()[num];
    }

    private static PathState interpolate(PathState state1, PathState state2, double currentTime) {
        double time = currentTime;
        return null; //TODO: Implement this method
    };

}
