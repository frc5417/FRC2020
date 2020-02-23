

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

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;


public class Climb extends SubsystemBase {
    
  	//Initializes Solenoids and compressor pistons

    CANSparkMax motorR = new CANSparkMax(Constants.RClimb, MotorType.kBrushless);
    CANSparkMax motorL = new CANSparkMax(Constants.LClimb, MotorType.kBrushless);

    boolean toggle;
    int count;
    double climbPower;

    public Climb(){
      motorR.setInverted(false);
      motorL.setInverted(true);
      toggle = true;
      count = 0;
      climbPower = -.5;

    }
/*
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
    */

    public void latch(boolean buttonDirectionUp, boolean buttonDirectionDown, double buttonLeft, double buttonRight)
    {
      /*
      if(buttonLeftUp && buttonLeftDown == 0){
        motorL.set(-.5);
        System.out.println("latch function");
      }
      else if(buttonLeftDown != 0 && buttonLeftUp == false){
        motorL.set(.5);
      }
      else{
        motorL.set(0);
      }
      if(buttonRightUp && buttonRightDown == 0){
        motorR.set(-.5);
        System.out.println("latch function");
      }
      else if(buttonRightDown != 0 && buttonRightUp == false){
        motorR.set(.5);
      }
      else{
        motorR.set(0);
      }
      */
      if(buttonDirectionUp){
        climbPower = .5;
      }
      else if(buttonDirectionDown){
        climbPower = -.5;
      }
      if(buttonRight != 0){
        motorR.set(climbPower);
      }
      else{
        motorR.set(0);
      }
      if(buttonLeft != 0){
        motorL.set(climbPower);
      }
      else{
        motorL.set(0);
      }

    }
    public void unLatch(double Power)
    {
      if(Power > 0){
        motorR.set(Power);
        motorL.set(Power);
        System.out.println("unlatch function");
      }
      else{
        motorR.set(0);
        motorL.set(0);
      }
    }



}
