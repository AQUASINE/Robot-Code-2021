package frc.robot.path;

public class PathState {
    private double time;
    private double velocity;
    private double acceleration;
    private PoseData pose;
    private double curvature;

    public PathState(double time, double velocity, double acceleration, PoseData pose, double curvature) {
        this.time = time;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.pose = pose;
        this.curvature = curvature;
    }

    /**
     * @return double return the time
     */
    public double getTime() {
        return time;
    }

    /**
     * @return double return the velocity
     */
    public double getVelocity() {
        return velocity;
    }

    /**
     * @return double return the acceleration
     */
    public double getAcceleration() {
        return acceleration;
    }

    /**
     * @return PoseData return the pose
     */
    public PoseData getPose() {
        return pose;
    }

    /**
     * @return double return the curvature
     */
    public double getCurvature() {
        return curvature;
    }
}