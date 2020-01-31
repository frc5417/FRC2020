/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class Drive extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  /*
  CANSparkMax driveSlaveL = new CANSparkMax(Constants.slaveLeftMotor, MotorType.kBrushless);
  CANSparkMax driveMasterR = new CANSparkMax(Constants.masterRightMotor, MotorType.kBrushless);
  CANSparkMax driveSlaveR = new CANSparkMax(Constants.slaveRightMotor, MotorType.kBrushless);
  CANSparkMax driveMasterL = new CANSparkMax(Constants.masterLeftMotor, MotorType.kBrushless);
  */

  TalonSRX driveMasterRight = new TalonSRX(Constants.masterRightMotor);
  TalonSRX driveMasterLeft = new TalonSRX(Constants.masterLeftMotor);
  VictorSPX driveSlaveRight = new VictorSPX(Constants.slaveRightMotor);
  VictorSPX driveSlaveLeft = new VictorSPX(Constants.slaveLeftMotor);
  

  public Drive(){
  


    driveMasterRight.setNeutralMode(NeutralMode.Coast);
    driveMasterLeft.setNeutralMode(NeutralMode.Coast);
    driveSlaveRight.setNeutralMode(NeutralMode.Coast);
    driveSlaveLeft.setNeutralMode(NeutralMode.Coast);
/*
    driveSlaveL.follow(driveMasterL);
    driveSlaveR.follow(driveMasterR);
    */
    
  }
  /*
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    Drive.setDefaultCommand(new TankDrive());

  }
  */

  public void SetPower(double leftPower, double rightPower){
    driveMasterLeft.set(ControlMode.PercentOutput, .5*leftPower);
    driveMasterRight.set(ControlMode.PercentOutput, .5*-rightPower);
    driveSlaveLeft.set(ControlMode.PercentOutput, .5*leftPower);
    driveSlaveRight.set(ControlMode.PercentOutput, .5*-rightPower);

    /*
    driveMasterL.set(leftPower);
    driveMasterR.set(-rightPower);
    */
  }
}