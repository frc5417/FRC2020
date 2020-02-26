/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.*;
import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoAlign extends CommandBase {
  private final Limelight l;
  double[] speed = new double[3];
  /**
   * Creates a new AutoAlign.
   */
  public AutoAlign(Limelight subsystem) {
    l = subsystem;
    addRequirements(l);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    speed = Robot.limelight.getSpeeds();

    //commented out code for aligning and driving without turret
    /*Robot.drive.SetPower(speed[0], speed[1]);*/

    Robot.drive.autoPower(speed[0], speed[1]);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Math.abs(Robot.limelight.getX()) < 1 && Math.abs(Robot.limelight.getY()) < 1){
      return true;
    }
    return false;
  }
}
