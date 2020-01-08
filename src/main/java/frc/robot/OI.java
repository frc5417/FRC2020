package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * Add your docs here.
 */
public class OI {
    private XboxController xboxController = new XboxController(0);
    private Hand rightHand = Hand.kRight; private Hand leftHand  = Hand.kLeft;


    public OI() {

        //assigning buttons to things
        JoystickButton aPad = new JoystickButton(xboxController,1);
		JoystickButton bPad = new JoystickButton(xboxController,2);
		JoystickButton xPad = new JoystickButton(xboxController,3);
		JoystickButton yPad = new JoystickButton(xboxController,4);
		
		JoystickButton leftBump = new JoystickButton(xboxController, 5);
		JoystickButton rightBump = new JoystickButton(xboxController, 6);

		JoystickButton startButton = new JoystickButton(xboxController, 7);
		JoystickButton menuButton = new JoystickButton(xboxController, 8);

		JoystickButton leftJoystickButton = new JoystickButton(xboxController, 9);
		JoystickButton rightJoystickButton = new JoystickButton(xboxController, 10);
    
        
        //assigning commands to buttons presses/releases
        //menuButton.whenPressed(new zeroArm());

        // aPad.whileHeld(new setIntakeCargo());
        // bPad.whileHeld(new setIntakeHatch());
        // xPad.whileHeld(new moveArm(constants.scoreCargoHeight));
        // yPad.whileHeld(new moveArm(constants.hatchHeight));
        


    }
}
