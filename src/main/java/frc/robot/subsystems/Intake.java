
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class Intake extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //feel free to change these names, they might suck

    double intakeSpeed = .5;
    double feederSpeed = .3;

    WPI_VictorSPX rollerBar = new WPI_VictorSPX(Constants.intakeMotor1);//rollerbar               //ask build which ports theyll use
    WPI_VictorSPX internalBelt = new WPI_VictorSPX(Constants.intakeMotor2);//internal belt thing
    WPI_VictorSPX feeder = new WPI_VictorSPX(Constants.intakeMotor3);//the one that puts it in the shooter
    
    public Intake(){
      rollerBar.setNeutralMode(NeutralMode.Coast);
      internalBelt.setNeutralMode(NeutralMode.Coast);
      feeder.setNeutralMode(NeutralMode.Coast);
    }
    
    // Use Victor.follow() for master/slave stuff}

  public void stopIntake(){//yoinked from 2019 code, is it even useful?
    //turns off all intake motors
    rollerBar.set(ControlMode.PercentOutput,0);
    internalBelt.set(ControlMode.PercentOutput,0);
    feeder.set(ControlMode.PercentOutput,0);
  }

  public void setIntakeSpeed(double speed){ //probably won't be useful mid match but whatever
      intakeSpeed = speed;
  }

  public void runIntakeSystem(boolean button){ //runs rollerbar and 
    if(button){
        rollerBar.set(intakeSpeed);
        internalBelt.follow(rollerBar);
    }else{
      rollerBar.setNeutralMode(NeutralMode.Brake);
      internalBelt.setNeutralMode(NeutralMode.Brake);
    }
  }

  public void runIntakeSystemBackwards(boolean button){
    if(button){
        rollerBar.set(-intakeSpeed);
        internalBelt.follow(rollerBar);
    }else{
      rollerBar.setNeutralMode(NeutralMode.Brake);
      internalBelt.setNeutralMode(NeutralMode.Brake);
    }
  }

  public void runRollerBar(boolean button){
    if(button){
      rollerBar.set(intakeSpeed);
    }else{
      rollerBar.setNeutralMode(NeutralMode.Brake);
    }
  }
  public void runRollerBarBackwards(boolean button){
    if(button){
      rollerBar.set(-intakeSpeed);
    }else{
      rollerBar.setNeutralMode(NeutralMode.Brake);
    }
  }
  public void runInternalBelt(boolean button){
    if(button){
      internalBelt.set(intakeSpeed);
    }else{
      internalBelt.setNeutralMode(NeutralMode.Brake);
    }
  }
  public void runInternalBeltBackwards(boolean button){
    if(button){
      internalBelt.set(-intakeSpeed);
    }else{
      internalBelt.setNeutralMode(NeutralMode.Brake);
    }
  }

  public void runFeeder(boolean button){
       if(button){
           feeder.set(feederSpeed);
       }else{
         feeder.setNeutralMode(NeutralMode.Brake);
       }
   }

   public void runFeederBackwards(boolean button){
    if(button){
      feeder.set(-feederSpeed);
    }else{
      feeder.setNeutralMode(NeutralMode.Brake);
    }
  }
}
