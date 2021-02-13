package frc.robot.command.auto;

import frc.robot.subsystem.DriveSubsystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class RotateConstantCommandTest {
    private RotateConstantCommand command;
    private DriveSubsystem mockDriveSubsystem;
    @BeforeEach
    public void setUp(){
        mockDriveSubsystem=mock(DriveSubsystem.class);
        when(mockDriveSubsystem.getGyroAngle()).thenReturn(0.0);
        command=new RotateConstantCommand(90, mockDriveSubsystem);
    }
    @Test
    @DisplayName("isFinished should return false when initialised with a positive number")
    public void testIsFinishedFalseWhenPositiveStart(){
        command=new RotateConstantCommand(90, mockDriveSubsystem);
        assertFalse(command.isFinished());
    }
    @Test
    @DisplayName("isFinished should return false when initialised with a negative number")
    public void testIsFinishedFalseWhenNegativeStart() {
        command = new RotateConstantCommand(-90, mockDriveSubsystem);
        assertFalse(command.isFinished());
    }
    @Test
    @DisplayName("isFinished should return true when initialised with a positive number")
    public void testIsFinishedTrueWhenPositiveEnd() {
        command=new RotateConstantCommand(90, mockDriveSubsystem);
        command.initialize();
        when(mockDriveSubsystem.getGyroAngle()).thenReturn(91.0);
        command.execute();
        assertTrue(command.isFinished());
    }
    @Test
    @DisplayName("isFinished should return true when initialised with a positive number")
    public void testIsFinishedTrueWhenNegativeEnd() {
        command = new RotateConstantCommand(-90, mockDriveSubsystem);
        command.initialize();
        when(mockDriveSubsystem.getGyroAngle()).thenReturn(-91.0);
        command.execute();
        assertTrue(command.isFinished());
    }
}
