
package frc.robot.subsystems;

// climb code goes here

/*
1. Arm Starts lowered
2. Press Button to switch solenoid (piston will be removed) so arm can be raised
3. Turn motor so that ratchet starts spinning to lower arm (Holding down a button)
*/

// Reference example code for NEO Motor: https://github.com/REVrobotics/SPARK-MAX-Examples/blob/master/Java/Motor%20Follower/src/main/java/frc/robot/Robot.java
// Reference for Solenoid: https://www.chiefdelphi.com/t/how-to-program-solenoid-and-compressor/101061/5
// Reference for SparkMAX deviceID: http://www.revrobotics.com/sparkmax-users-manual/


//This is the robot code for the CLIMBER

//Importing Packages For Solenoid And Spark(Neo) Motors

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;


public class Climb {
    
  	//Initializes Solenoids and compressor pistons
  	Solenoid climbPiston = new Solenoid(0);
  	Compressor airCompressor = new Compressor();
  
  	Joystick joystick = new Joystick(0);
  

    public CANSparkMax testMotor1;
  	public CANSparkMax testMotor2;

	
  
    //Method For Switching Solenoid
    public void solenoidSwitch(){
          if(joystick.getRawButton(6)){
          climbPiston.set(true);
        }
      
    }
  
  public void testMotor(){
    CANSparkMax motorLeft = new CANSparkMax(0, MotorType.kBrushless);     // 0 is the default deviceID, but it should be changed through the Windows SparkMax client
    CANSparkMax motorRight = new CANSparkMax(1, MotorType.kBrushless);
    
    if(joystick.getRawButtonPressed(4)){
    	motorLeft.set(5);  // sets speed of motor
    }
    if (joystick.getRawButtonReleased(4)) {
      motorLeft.close(); // sets speed of motor to 0 ? 
    }
    
    if(joystick.getRawButtonPressed(5)){
    	motorRight.set(5);  // sets speed of motor
    }
    if (joystick.getRawButtonReleased(5)) {
      motorRight.close(); // sets speed of motor to 0 ? 
    }
    
  }

}