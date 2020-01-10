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
    double x;
    double y;
    double area;
    double s;
    double left_command;
    double right_command;
    TalonSRX driveMasterLeft;
    TalonSRX driveMasterRight;
    VictorSPX driveSlaveLeft;
    VictorSPX driveSlaveRight;
    double v;
    public auton(){
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tv = table.getEntry("tv");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry ts = table.getEntry("ts");œ

    //read values periodically
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);
    s = ts.getDouble(0.0);
    v = tv.getDouble(0.0);

    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);

    driveMasterLeft = new TalonSRX(2);
    driveMasterRight = new TalonSRX(1);
    driveSlaveLeft = new VictorSPX(4);
    driveSlaveRight = new VictorSPX(3);


    }
    
    public void autoAlign(boolean button){
        
        double Kp = 0.2;
        double min_command = .3;
        if (button == true){
            if(v == 1){ 
                double heading_error = -x;
                double steering_adjust = 0.0f;
                if (x > 1.0)
                {
                    steering_adjust = Kp*heading_error - min_command;
                }
                else if (x < 1.0)
                {
                    steering_adjust = Kp*heading_error + min_command;
                }
                left_command += steering_adjust;
                right_command -= steering_adjust;

                driveSlaveRight.set(ControlMode.Follower, driveMasterRight.getDeviceID());
                driveSlaveLeft.set(ControlMode.Follower, driveMasterLeft.getDeviceID());

                driveMasterLeft.set(ControlMode.PercentOutput, right_command);
                driveMasterRight.set(ControlMode.PercentOutput, left_command);
            }
        }

    }
}