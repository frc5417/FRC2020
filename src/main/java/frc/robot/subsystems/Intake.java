
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
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;

//test7
public class Intake extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //feel free to change these names, they might suck

    double intakeSpeed = .5;
    double feederSpeed = .3;
    public int count = 0;
    int ballInternalCount = 0;
    boolean ballInternalToggle = true;
    int ballFeederCount = 0;
    boolean ballFeederToggle = false;
    boolean feederFlag = true;

    WPI_VictorSPX rollerBar = new WPI_VictorSPX(Constants.intakeRoller);//rollerbar               //ask build which ports theyll use
    WPI_VictorSPX internalBelt = new WPI_VictorSPX(Constants.intakeMotorTop);//internal belt thing
    WPI_VictorSPX feeder = new WPI_VictorSPX(Constants.intakeMotorBottom);//the one that puts it in the shooter
    CANSparkMax masterShoot =  new CANSparkMax(Constants.shooterMaster, MotorType.kBrushless);
    CANSparkMax slaveShoot =  new CANSparkMax(Constants.shooterSlave, MotorType.kBrushless);
    DigitalInput ballInternalCounter = new DigitalInput(Constants.ballInternalCounterPort);
    DigitalInput ballFeederCounter = new DigitalInput(Constants.ballFeederCounterPort);
    //Solenoid intakeSolenoid = new Solenoid(1);
    double setPoint;
    
    public Intake(){
      rollerBar.setNeutralMode(NeutralMode.Coast);
      internalBelt.setNeutralMode(NeutralMode.Coast);
      feeder.setNeutralMode(NeutralMode.Coast);
      masterShoot.getPIDController().setFF(Constants.shootkFF);
      //slaveShoot.getPIDController().setFF(Constants.shootkFF);
      masterShoot.getPIDController().setP(Constants.shootkP);
      //slaveShoot.getPIDController().setP(Constants.shootkP);
      masterShoot.getPIDController().setI(Constants.shootkI);
      //slaveShoot.getPIDController().setI(Constants.shootkI);
      setPoint = Constants.shootsetPointConstant*Constants.shootMaxRPM;
      
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

  public void runIntakeSystem(double buttonForward, double buttonBackward, boolean buttonIn, boolean buttonOut){ //runs rollerbar and 
    if(buttonForward != 0 && buttonBackward == 0){
      rollerBar.set(intakeSpeed);
      /*if(ballFeederCounter.get()){
        ballInternalToggle = true;
      }
      if(ballFeederCounter.get() == false){
        if(ballInternalToggle){
          internalBelt.set(0);
          ballInternalToggle = false;
        }
      }*/

      if(ballFeederCounter.get() == false){
        feeder.set(intakeSpeed);
        internalBelt.set(-intakeSpeed);
      }
      else if(ballFeederCounter.get()){
        feeder.set(0);
        internalBelt.set(-intakeSpeed);
        if(ballInternalCounter.get() && ballInternalToggle == false){
          ballInternalToggle = true;
          internalBelt.set(0);
        }
      }
      if(ballInternalToggle && buttonForward != 0){
        ballInternalToggle = false;
        if(ballInternalCounter.get()){
          internalBelt.set(-intakeSpeed);
        }
        else{
          internalBelt.set(0);
        }
      }



    }    
    else if(buttonBackward != 0 && buttonForward == 0){
      rollerBar.set(-intakeSpeed);
      internalBelt.set(intakeSpeed);
      feeder.set(-intakeSpeed);
    }
    else if(buttonIn && buttonOut == false){
      rollerBar.set(intakeSpeed);
    }else if(buttonOut && buttonIn == false){
      rollerBar.set(-intakeSpeed);
    }
    else{
      rollerBar.set(0);
      internalBelt.set(0);
      feeder.set(0);
    }


  }
  public void deployPistons(boolean buttonDeploy, boolean buttonRetract){
    if(buttonDeploy){
      //intakeSolenoid.set(true);
    }
    else if(buttonRetract){
      //intakeSolenoid.set(false);
    }
  }

  public void runRollerBar(boolean buttonIn, boolean buttonOut){
    if(buttonIn && buttonOut == false){
      rollerBar.set(intakeSpeed);
    }else if(buttonOut && buttonIn == false){
      rollerBar.set(-intakeSpeed);
    }
    else{
      rollerBar.set(0);
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

  public void shoot(boolean button){
    if(button){
      
      /*masterShoot.set(-1);
      slaveShoot.set(-1);*/
            
      System.out.println(masterShoot.getEncoder().getVelocity() + " Motor ID: 12");
      //System.out.println(slaveShoot.getEncoder().getVelocity() + " Motor ID: 3");
      masterShoot.getPIDController().setReference(setPoint, ControlType.kVelocity);
      slaveShoot.follow(masterShoot);

      if(masterShoot.getEncoder().getVelocity() <= (Constants.shootsetPointConstant + 500) && masterShoot.getEncoder().getVelocity() >= (Constants.shootsetPointConstant - 500)){
        internalBelt.set(intakeSpeed);
        feeder.follow(internalBelt);
      }
      else{
        internalBelt.set(0);
        feeder.set(0);
      }
    }
    else{
      masterShoot.set(0);
      masterShoot.getPIDController().setReference(0, ControlType.kVelocity);
      //slaveShoot.set(0);
      internalBelt.set(0);
      feeder.set(0);
      count = 0;
    }

  }

  public void AutoShoot(){

      masterShoot.getPIDController().setReference(setPoint, ControlType.kVelocity);
      slaveShoot.follow(masterShoot);

      if(masterShoot.getEncoder().getVelocity() <= (Constants.shootsetPointConstant + 500) && masterShoot.getEncoder().getVelocity() >= (Constants.shootsetPointConstant - 500)){
        internalBelt.set(intakeSpeed);
        feeder.follow(internalBelt);
      }
      else{
        internalBelt.set(0);
        feeder.set(0);
      }
    }
}
