package frc.robot.command.auto;

import frc.robot.path.PathDataModel;
import frc.robot.path.PathState;
import frc.robot.subsystem.DriveSubsystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Testable
public class FollowPathCommandTest {
    private PathDataModel pathDataModel;
    private FollowPathCommand command;
    private DriveSubsystem mockDriveSubsystem;

    public FollowPathCommandTest() {}

    @BeforeEach
    public void setUp() {
        mockDriveSubsystem = mock(DriveSubsystem.class);
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("\\src\\test\\java\\frc\\robot\\path\\CircuitPathFixture.wpilib.json");
        System.out.println("FollowPathCommandTest.setUp(): filePath -> " + filePath);
        pathDataModel = new PathDataModel(PathDataModel.readFromInputStream(filePath));
        command = new FollowPathCommand(mockDriveSubsystem, pathDataModel);
    }

    @Test
    @DisplayName("Test to make sure that the command doesn't error out on execution")
    public void testCommandExecute() {
        command.execute();
        command.execute();
        System.out.println(command.currentTime + " " + command.currentStep);
    }

    @Test
    @DisplayName("Test Interpolate function")
    public void testInterpolate() {
        PathState pathState1 = pathDataModel.getPathStates()[10];
        PathState pathState2 = pathDataModel.getPathStates()[11];
        double currentTime = (pathState2.getTime() + pathState1.getTime()) / 2;
        PathState pathState3 = FollowPathCommand.interpolate(pathState1, pathState2, currentTime);
        assertEquals(pathState2.getVelocity() + pathState1.getVelocity(), pathState3.getVelocity() * 2);
        assertTrue((pathState2.getPose().getRotation().get("radians") + pathState1.getPose().getRotation().get("radians")) / 2 - pathState3.getPose().getRotation().get("radians") < 0.000001);
    }
}
