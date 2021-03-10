package frc.robot.path;

import com.google.gson.Gson;
import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import frc.robot.path.PathDataModel;

import java.io.*;

import static frc.robot.path.PathDataModel.readFromInputStream;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class PathDataModelTest {
    // TODO: Create tests for importing path data and seeing if it is formatted correctly
    PathDataModel model;
    String json;

    @BeforeEach
    public void setUp() {
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("\\src\\test\\java\\frc\\robot\\path\\CircuitPathFixture.wpilib.json");
        json = readFromInputStream(filePath);
        model = new PathDataModel(json);
    }

    @Test
    @DisplayName("Ensure that json is successfully loaded")
    public void testJsonNotNull() {
        System.out.println(json == null ? "JSON is null" : json.length());
        assertNotNull(json);
    }

    @Test
    public void testGetPathStates() {
        System.out.println(model.getPathStates()[10].getVelocity());
    }
}