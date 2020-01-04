/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
/**
 * Add your docs here.
 */
public class drive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  
  TalonSRX driveMasterLeft = new TalonSRX(1);
  TalonSRX driveSlaveLeft1 = new TalonSRX(2);
  VictorSPX driveSlaveLeft2 = new VictorSPX(3);
  TalonSRX driveMasterRight = new TalonSRX(4);
  TalonSRX driveSlaveRight1 = new TalonSRX(5);
  TalonSRX driveSlaveRight2 = new TalonSRX(6);
  

  public drive(){
    /*
    driveSlaveLeft1.set(ControlMode.Follower, driveMasterLeft.getDeviceID());
    driveSlaveLeft2.set(ControlMode.Follower, driveMasterLeft.getDeviceID());

    driveSlaveRight1.set(ControlMode.Follower, driveMasterRight.getDeviceID());
    driveSlaveRight2.set(ControlMode.Follower, driveMasterRight.getDeviceID());

    driveMasterRight.setNeutralMode(NeutralMode.Coast);
    driveMasterLeft.setNeutralMode(NeutralMode.Coast);
    */
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

  }

  public void SetPower(double leftPower, double rightPower){
    driveMasterLeft.set(ControlMode.PercentOutput, -leftPower);
    driveMasterRight.set(ControlMode.PercentOutput, rightPower);
  }
}