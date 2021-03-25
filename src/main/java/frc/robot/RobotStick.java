package frc.robot;

import org.w3c.dom.ranges.RangeException;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotStick extends Joystick {
    private final int HIGHEST_BUTTON = 12;
    private JoystickButton[] buttons;

    public RobotStick(int port) {
        super(port);
        buttons = new JoystickButton[HIGHEST_BUTTON+1];
        for(int i = 0; i <= HIGHEST_BUTTON; i++) {
            buttons[i] = new JoystickButton(this, i);
        }
    }
    
    public JoystickButton getButton(int button) {
        if(button > HIGHEST_BUTTON || button < 0) {
            throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR, "Button not in range on joystick");
        }
        return buttons[button];
    }
}
