package frc.robot.command.auto;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.command.auto.DriveLengthConstantCommand;
import frc.robot.subsystem.DriveSubsystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Testable
class DriveLengthConstantCommandTest {

    private DriveLengthConstantCommand command;
    private DriveSubsystem mockDriveSubsystem;
    @BeforeEach
    public void setUp() {
        mockDriveSubsystem = mock(DriveSubsystem.class);
        when(mockDriveSubsystem.getGyroAngle()).thenReturn(0.0);
        when(mockDriveSubsystem.getEncoderValueLeftBack()).thenReturn(0.0);
        command = new DriveLengthConstantCommand(48, mockDriveSubsystem);
    }

    @Test
    @DisplayName("isFinished should return false when initialized with a number greater than zero")
    public void testIsFinishedPositive() {
        command = new DriveLengthConstantCommand(48, mockDriveSubsystem);
        assertFalse(command.isFinished());
    }

    @Test
    @DisplayName("isFinished should return false when initialized with a number less than zero")
    public void testIsFinishedNegative() {
        command = new DriveLengthConstantCommand(-48, mockDriveSubsystem);
        assertFalse(command.isFinished());
    }
    
    
}
