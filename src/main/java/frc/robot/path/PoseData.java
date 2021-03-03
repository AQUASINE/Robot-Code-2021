package frc.robot.path;

import java.util.HashMap;

public class PoseData {
    private final HashMap<String, Double> translation;
    private final HashMap<String, Double> rotation;

    public PoseData(HashMap<String, Double> translation, HashMap<String, Double> rotation) {
        this.translation = translation;
        this.rotation = rotation;
    }

    /**
     * @return HashMap<String, Double> return the translation
     */
    public HashMap<String, Double> getTranslation() {
        return translation;
    }

    /**
     * @return HashMap<String, Double> return the rotation
     */
    public HashMap<String, Double> getRotation() {
        return rotation;
    }
}
