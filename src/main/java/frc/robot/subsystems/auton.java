package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


public class auton extends SubsystemBase {

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
    

    public auton(){
    // Open the network table to access the limelight values
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tv = table.getEntry("tv");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry ts = table.getEntry("ts");

    // Read values periodically
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);
    s = ts.getDouble(0.0);
    v = tv.getDouble(0.0);

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
    driveMasterRight = new TalonSRX(2);
    driveMasterLeft = new TalonSRX(1);
    driveSlaveRight = new VictorSPX(4);
    driveSlaveLeft = new VictorSPX(3);


    }
    
    // Program to allow the drivers to auto align based on the target
    public void autoAlign(boolean button){
        
        // P value to allow the motors to move smoothly
        double Kp = 0.2;

        // Minimum power the robots need to move
        double min_command = .3;

        // Check whether the buttons is pressed
        if (button){

            // Check whether the target is visible
            if(v == 1){ 

                // Check the current the robots position in relation to the target
                double heading_error = -x;

                // Value for the robot to turn in relation to the target
                double steering_adjust = 0.0;

                // Uses Horizontal Offset to calculate the steering adjust
                if (x > 1.0)
                {

                    
                    steering_adjust = Kp*heading_error - min_command;
                }
                else if (x < 1.0)
                {
                    steering_adjust = Kp*heading_error + min_command;
                }

                // Adjust motors based on the steering adjust
                left_command += steering_adjust;
                right_command -= steering_adjust;
                // Set slaves
                driveSlaveRight.set(ControlMode.Follower, driveMasterRight.getDeviceID());
                driveSlaveLeft.set(ControlMode.Follower, driveMasterLeft.getDeviceID());

                // Set masters and their power values, stop motors at certain distance
                //do {
                //d = (h1 - h2) / Math.tan(a + y);
                driveMasterRight.set(ControlMode.PercentOutput, right_command);
                driveMasterLeft.set(ControlMode.PercentOutput, left_command);
                //} while(d < idealDistance);
            }
        }

    }

}