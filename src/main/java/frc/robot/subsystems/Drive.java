/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import java.lang.Math;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Add your docs here.
 */
public class Drive extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  CANSparkMax driveSlaveL = new CANSparkMax(Constants.slaveLeftMotor, MotorType.kBrushless);
  CANSparkMax driveMasterR = new CANSparkMax(Constants.masterRightMotor, MotorType.kBrushless);
  CANSparkMax driveSlaveR = new CANSparkMax(Constants.slaveRightMotor, MotorType.kBrushless);
  CANSparkMax driveMasterL = new CANSparkMax(Constants.masterLeftMotor, MotorType.kBrushless);
  TalonSRX turretMotor = new TalonSRX(9);
  Solenoid shifter = new Solenoid(0);
  Limelight limelight = new Limelight();
  
  int count;
  int toggle;
/*
  TalonSRX driveMasterRight = new TalonSRX(Constants.masterRightMotor);
  TalonSRX driveMasterLeft = new TalonSRX(Constants.masterLeftMotor);
  VictorSPX driveSlaveRight = new VictorSPX(Constants.slaveRightMotor);
  VictorSPX driveSlaveLeft = new VictorSPX(Constants.slaveLeftMotor);
*/

  public Drive(){
  
/*
    driveMasterRight.setNeutralMode(NeutralMode.Coast);
    driveMasterLeft.setNeutralMode(NeutralMode.Coast);
    driveSlaveRight.setNeutralMode(NeutralMode.Coast);
    driveSlaveLeft.setNeutralMode(NeutralMode.Coast);
*/
    driveSlaveL.follow(driveMasterL);
    driveSlaveR.follow(driveMasterR);
    driveMasterL.setInverted(false);
    driveMasterR.setInverted(true);
    driveSlaveR.setInverted(true);
    turretMotor.setNeutralMode(NeutralMode.Coast);

    
  }
  

  public void SetPower(double leftPower, double rightPower){
    //if (!((leftPower < .1)&&(leftPower > -.1) || (rightPower < .1)&&(rightPower > -.1))){
      /*
    driveMasterLeft.set(ControlMode.PercentOutput, -leftPower);
    driveMasterRight.set(ControlMode.PercentOutput, -rightPower);
    driveSlaveLeft.set(ControlMode.PercentOutput, leftPower);
    driveSlaveRight.set(ControlMode.PercentOutput, -rightPower);
    
    
    }
    else{
      driveMasterLeft.set(ControlMode.PercentOutput, 0);
      driveMasterRight.set(ControlMode.PercentOutput, 0);
      driveSlaveLeft.set(ControlMode.PercentOutput, 0);
      driveSlaveRight.set(ControlMode.PercentOutput, 0);
    }
    */
    
      driveMasterL.set(Math.pow(leftPower, 3));
      driveMasterR.set(Math.pow(rightPower, 3));
      driveSlaveL.follow(driveMasterL);
      driveSlaveR.follow(driveMasterR);
      
  }
  public void autoPower(double leftPower, double rightPower, double turretPower){
    /*
    driveMasterLeft.set(ControlMode.PercentOutput, leftPower);
    driveMasterRight.set(ControlMode.PercentOutput, -rightPower);
    driveSlaveLeft.set(ControlMode.PercentOutput, -leftPower);
    driveSlaveRight.set(ControlMode.PercentOutput, -rightPower);
    */
    driveMasterL.set(leftPower);
    driveMasterR.set(rightPower);
    driveSlaveL.follow(driveMasterL);
    driveSlaveR.follow(driveMasterR);
    if(limelight.getY() < 1 && limelight.getY() > -1){
      turretMotor.set(ControlMode.PercentOutput, turretPower);
    }
    else{
      turretMotor.set(ControlMode.PercentOutput, 0);
    }

  }
  
  public void Shift(boolean button){

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
          shifter.set(false);
          break;
        case 1:
          shifter.set(true);
          break;
      }
    } 

}