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
    
    //Creating Solenoid Variable
    public Solenoid solenoid;
    public CANSparkMax testMotor;
    public Compressor airCompressor = new Compressor(1);
    public Joystick joystick = new Joystick(0);


    //Method For Switching Solenoid
    public void solenoidSwitch(){
        solenoid = new Solenoid(2);
        if(joystick.getRawButton(1)){
          solenoid.set(true);
        }
      
    }
  
  public void testMotor(){
    CANSparkMax testMotor = new CANSparkMax(0, MotorType.kBrushless);     // 0 is the default deviceID, but it should be changed through the Windows SparkMax client
    if(joystick.getRawButtonPressed(2)){
    	testMotor.set(.5);  // sets speed of motor
    }
    if (joystick.getRawButtonReleased(2)) {
      testMotor.close(); // sets speed of motor to 0 
    }
    
  }
}