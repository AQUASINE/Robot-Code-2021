package frc.robot.command.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.path.PathDataModel;
import frc.robot.path.PathState;
import frc.robot.path.PoseData;
import frc.robot.subsystem.DriveSubsystem;

import java.util.HashMap;

public class FollowPathCommand extends CommandBase {
    private DriveSubsystem drive;
    private PathDataModel path;

    public int currentStep;
    public double currentTime;
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
            /*
            if(currentStep == NUM_PATH_STEPS - 1) {

            } else {
                pathState = interpolate(getStep(currentStep), getStep(currentStep + 1), currentTime);
            }

             */
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

    public static PathState interpolate(PathState state1, PathState state2, double currentTime) {
        double timeScalar = (currentTime - state1.getTime()) / (state2.getTime() - state1.getTime());

        PoseData state1Pose = state1.getPose();
        PoseData state2Pose = state2.getPose();

        double rotation1 = state1Pose.getRotation().get("radians");
        double rotation2 = state2Pose.getRotation().get("radians");

        HashMap<String, Double> translation1 = state1Pose.getTranslation();
        double x1 = translation1.get("x");
        double y1 = translation1.get("y");

        HashMap<String, Double> translation2 = state2Pose.getTranslation();
        double x2 = translation2.get("x");
        double y2 = translation2.get("y");

        HashMap<String, Double> newTranslation = new HashMap<>();
        newTranslation.put("x", interpolateNums(x1, x2, timeScalar));
        newTranslation.put("y", interpolateNums(y1, y2, timeScalar));

        HashMap<String, Double> newRotation = new HashMap<>();
        newRotation.put("radians", interpolateNums(rotation1, rotation2, timeScalar));

        double newVelocity = interpolateNums(state1.getVelocity(), state2.getVelocity(), timeScalar);
        double newAcceleration = interpolateNums(state1.getAcceleration(), state2.getAcceleration(), timeScalar);
        double newCurvature = interpolateNums(state1.getCurvature(), state2.getCurvature(), timeScalar);

        PoseData newPoseData = new PoseData(newTranslation, newRotation);

        return new PathState(currentTime, newVelocity, newAcceleration, newPoseData, newCurvature);
    }

    public static double interpolateNums(double num1, double num2, double timeScalar) {
        return num1 + (num2 - num1) * timeScalar;
    }

}