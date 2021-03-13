package frc.robot.command.auto;

import frc.robot.path.PathDataModel;
import frc.robot.path.PathState;
import frc.robot.subsystem.DriveSubsystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.annotation.Testable;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@Testable
public strictfp class FollowPathCommandTest {
    private PathDataModel pathDataModel;
    private FollowPathCommand command;
    private DriveSubsystem mockDriveSubsystem;

    public FollowPathCommandTest() {
    }

    @BeforeEach
    public void setUp() {
        mockDriveSubsystem = mock(DriveSubsystem.class);
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("\\src\\test\\java\\frc\\robot\\path\\CircuitPathFixture.wpilib.json");
        System.out.println("FollowPathCommandTest.setUp(): filePath=>" + filePath);
        pathDataModel = new PathDataModel(PathDataModel.readFromInputStream(filePath));
        command = new FollowPathCommand(mockDriveSubsystem, pathDataModel);
    }

    @Test
    @DisplayName("Test to make sure that the command doesn't error out on execution")
    public void testCommandExecute() {
        command.execute();
        command.execute();
        System.out.println("FollowPathCommandTest.testCommandExecute(): currentTime=>" + command.currentTime + ", currentStep=>" + command.currentStep);
    }

    @ParameterizedTest
    @DisplayName("Test Interpolate function")
    @ValueSource(ints = {0, 1, 5, 10, 20, 500})
    public void testInterpolate(int number) {
        PathState pathState1 = pathDataModel.getPathStates()[number];
        PathState pathState2 = pathDataModel.getPathStates()[number + 1];
        double currentTime = (pathState2.getTime() + pathState1.getTime()) / 2;
        PathState pathState3 = FollowPathCommand.interpolate(pathState1, pathState2, currentTime);
        assertEquals(pathState2.getVelocity() + pathState1.getVelocity(), pathState3.getVelocity() * 2);
        assertTrue(
                (pathState2.getPose().getRotation().get("radians") + pathState1.getPose().getRotation().get("radians"))
                        / 2 - pathState3.getPose().getRotation().get("radians") < 0.000001);
    }

    @DisplayName("Test to make sure time values are equal for the interpolated values")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 10, 20, 500})
    public void testTimeAfterSteps(int number) {
        final int NUM_TIME_STEPS = number;
        double currentTime = 0;
        for (int i = 0; i < NUM_TIME_STEPS; i++) {
            command.execute();
            currentTime += FollowPathCommand.DT;
        }
        assertEquals(currentTime, command.getCurrentTime());

        if (command.currentStep < command.getPath().getPathStates().length) {
            PathState currState = command.getStep(command.currentStep);
            PathState nextState = command.getStep(command.currentStep + 1);
            PathState interpolated = FollowPathCommand.interpolate(currState, nextState, command.getCurrentTime());
            double diff = currentTime - interpolated.getTime();
            System.out.println("FollowPathCommandTest.testTimeAfterSteps(): diff=>" + diff);
            assertTrue(Math.abs(diff) < 0.0000001);
        }
    }
}
