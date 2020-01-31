
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

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;


public class Climb extends SubsystemBase {
    
  	//Initializes Solenoids and compressor pistons
    Solenoid climbPistonR = new Solenoid(0);
    Solenoid climbPistonL = new Solenoid(1);

    CANSparkMax motorR = new CANSparkMax(Constants.masterClimb, MotorType.kBrushless);
    CANSparkMax motorL = new CANSparkMax(Constants.slaveClimb, MotorType.kBrushless);

    int toggle;
    int count;

    public Climb(){
      motorR.setInverted(true);
      motorL.setInverted(true);
      toggle = 0;
      count = 0;

    }

    public void extend(boolean button)
    {
      if(button){
        count+=1;
      }

      switch(count){
        case 0:
          toggle = 0;
          break;
        case 1:
          toggle = 1;
          break;
        case 2:
          toggle = 0;
          count = 0;
          break;
      }
      
      switch(toggle){
        case 0:
          climbPistonL.set(false);
          climbPistonR.set(false);
          break;
        case 1:
          climbPistonL.set(true);
          climbPistonR.set(true);
          break;
      }

    }

    public void latch(double power)
    {
        motorR.set(power);
        motorL.follow(motorR);     

    }



}