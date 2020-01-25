package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.Constants;


public class Limelight extends SubsystemBase {

    // Horizontal Offset variable
    double x;

    // Vertical Offset variable
    double y;

    // Area of the target
    double area;

    // Skew of the robot
    double s;

    // Correct values to move the robot to align with the target, basically the motor power that is sent when a target is seen
    double left_command;
    double right_command;

    // These are the motors on the robot
    TalonSRX driveMasterLeft;
    TalonSRX driveMasterRight;
    VictorSPX driveSlaveLeft;
    VictorSPX driveSlaveRight;

    // Whether target is visible or not
    double v;

    // Height of limelight in inches
    double h1;

    // Height of goal in inches
    double h2;
    
    // Desired distance from goal in inches
    double idealDistance;

    //Actual distance from goal in inches
    double d; 
    
    // Angle of limelight
    double a;
    

    public Limelight(){

    // Will need to change, values to calculate robot stopping distance from target
    h1 = 6;
    h2 = 81.24;
    idealDistance = 12;
    a = 30;


    // Post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);

    // Assign motor ports to the motors
    driveMasterRight = new TalonSRX(Constants.masterRightMotor);
    driveMasterLeft = new TalonSRX(Constants.masterLeftMotor);
    driveSlaveRight = new VictorSPX(Constants.slaveRightMotor);
    driveSlaveLeft = new VictorSPX(Constants.slaveLeftMotor);


    }

    // Allows horizontal offset to be a dynamic value
    public void setX(NetworkTableEntry tx){
        x = tx.getDouble(0.0);
    }
    // Allows 
    public void setArea(NetworkTableEntry ta){
      area = ta.getDouble(0.0);
  
    }
    public void setV(NetworkTableEntry tv){
      v = tv.getDouble(0.0);
    }
    public void printX(){
      System.out.println(x);
    }
    // Program to allow the drivers to auto align based on the target
    public void autoAlign(boolean button){

      // Constants used to calculate motor power for alignment
      Double Kp = Constants.Kp;
      Double KpDistance = 0.075;
      Double area_error = 3 - area;
      Double driving_adjust = Constants.driving_adjust;
      Double min_command = Constants.min_command;

      // Checks to see if button pressec
      if(button) {
        // Set heading error and the steering adjust
        Double heading_error = -x;
        Double steering_adjust = 0.075;
        // Determine power based on the horizontal offset
        if (x > 1.0)
        {
                steering_adjust = Kp*heading_error - min_command;
        }
        else if (x < 1.0)
        {
                steering_adjust = Kp*heading_error + min_command;
        }
  
  
        // Determine distance to stop based on area of image seen
        if (area > .25){
          driving_adjust = KpDistance * area_error + min_command;
        }
        else if (area < .25){
          driving_adjust = KpDistance * area_error - min_command;
  
        }
  
        // Run motors if the target is seen
        if (v == 1){
        driveMasterLeft.set(ControlMode.PercentOutput, steering_adjust - driving_adjust);
        driveSlaveLeft.set(ControlMode.PercentOutput, steering_adjust - driving_adjust);
  
        driveMasterRight.set(ControlMode.PercentOutput, steering_adjust + driving_adjust);
        driveSlaveRight.set(ControlMode.PercentOutput, steering_adjust + driving_adjust);
  
        }
        else
        {
          driveMasterLeft.set(ControlMode.PercentOutput, 0);
          driveSlaveLeft.set(ControlMode.PercentOutput, 0);
    
          driveMasterRight.set(ControlMode.PercentOutput, 0);
          driveSlaveRight.set(ControlMode.PercentOutput, 0);
        } 
    }
    else {
      driveMasterLeft.set(ControlMode.PercentOutput, 0);
      driveSlaveLeft.set(ControlMode.PercentOutput, 0);

      driveMasterRight.set(ControlMode.PercentOutput, 0);
      driveSlaveRight.set(ControlMode.PercentOutput, 0);
    }

  }

}
// atiksh is dumb/test