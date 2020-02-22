/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class colorMove extends CommandBase {
  private final colorMotor m_colorMotor;
  //private int timeCounter = 0;
  
  static public String objective;

  /**
   * Creates a new colorMove.
   */
  public colorMove(colorMotor colormotor) {
    m_colorMotor = colormotor;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   /*   TIME BASED COUNTING, DONT WORRY
   timeCounter = timeCounter + 400;
    System.out.println(timeCounter);

    if (timeCounter < 1000){
      m_colorMotor.driveFoward(0.3);
      System.out.println("Motor Is Moving");
    } else {
      m_colorMotor.driveFoward(0);
      System.out.println("Motor has exceeded the amount that it needs to go");
    }

  }*/

if (objective.equals("Rotational")) {  
  if (ColorSensor.numberOfChange < 3){
    m_colorMotor.driveForward(0.3);
    System.out.println("Motor Is Moving in Rotational");
   } else {
    m_colorMotor.driveForward(0);
    System.out.println("Motor has exceeded the amount that it needs to go in Rotational");    
   }
  
}

if (objective.equals("Positional")) {
    if (!ColorSensor.targetColor.equals(ColorSensor.sensedColor)) {
      System.out.println("Positional - color is not equal");
      m_colorMotor.driveForward(0.3);
      
    } else {
      System.out.println("Positional - color IS equal");
      m_colorMotor.driveForward(0);
      
    }
  }
  
/*
  if (!ColorSensor.targetColor.equals(Robot.behindColor)) {
    m_colorMotor.driveForward(0.2);
  } else {
    m_colorMotor.driveForward(0);
  }
*/
}

  



  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}