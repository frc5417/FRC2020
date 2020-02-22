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
import java.lang.Math;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.subsystems.*;

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
  Solenoid shifter;
  Compressor compressor;
  Limelight limelight = new Limelight();
  
  boolean shifterFlag = false;
  boolean toggle = true;
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

    turretMotor.setNeutralMode(NeutralMode.Coast);

    shifter = new Solenoid(0);

    
  }
  

  public void SetPower(double leftPower, double rightPower){
    //if (!((leftPower < .1)&&(leftPower > -.1) || (rightPower < .1)&&(rightPower > -.1))){
    /*  
    driveMasterLeft.set(ControlMode.PercentOutput, -leftPower);
    driveMasterRight.set(ControlMode.PercentOutput, -rightPower);
    driveSlaveLeft.set(ControlMode.PercentOutput, leftPower);
    driveSlaveRight.set(ControlMode.PercentOutput, -rightPower);
    */

    driveMasterL.set(-Math.pow(leftPower, 3));
    driveMasterR.set(-Math.pow(rightPower, 3));
    driveSlaveL.set(-Math.pow(leftPower, 3));
    driveSlaveR.set(-Math.pow(rightPower, 3));
    
    }
    /*else{
      driveMasterLeft.set(ControlMode.PercentOutput, 0);
      driveMasterRight.set(ControlMode.PercentOutput, 0);
      driveSlaveLeft.set(ControlMode.PercentOutput, 0);
      driveSlaveRight.set(ControlMode.PercentOutput, 0);
    }*/
    
    

      
  
  public void autoPower(double leftPower, double rightPower){
    /*
    driveMasterLeft.set(ControlMode.PercentOutput, -leftPower);
    driveMasterRight.set(ControlMode.PercentOutput, -rightPower);
    driveSlaveLeft.set(ControlMode.PercentOutput, leftPower);
    driveSlaveRight.set(ControlMode.PercentOutput, -rightPower);
    */
    driveMasterL.set(leftPower);
    driveMasterR.set(rightPower);
    driveSlaveL.set(leftPower);
    driveSlaveR.set(rightPower);
    /*if(limelight.getY() < 1 && limelight.getY() > -1){
      turretMotor.set(ControlMode.PercentOutput, turretPower);
    }
    else{
      turretMotor.set(ControlMode.PercentOutput, 0);
    }*/

  }
  
  public void shift(boolean button){
    

    if (toggle && button) {  // Only execute once per Button push
      toggle = false;  // Prevents this section of code from being called again until the Button is released and re-pressed
      if (shifterFlag) {  // Decide which way to set the motor this time through (or use this as a motor value instead)
        shifterFlag = false;
        shifter.set(true);
      } else {
        shifterFlag= true;
        shifter.set(false);
      }
    } else if(button == false) { 
        toggle = true; // Button has been released, so this allows a re-press to activate the code above.
    }

      
    } 
    

}