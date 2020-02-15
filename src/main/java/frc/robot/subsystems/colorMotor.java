/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

// import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import com.ctre.phoenix.motorcontrol.can.VictorSPX;
// import edu.wpi.first.wpilibj.Talon;
// import edu.wpi.first.wpilibj.Victor;
import frc.robot.Constants;
import frc.robot.subsystems.ColorSensor;

public class colorMotor extends SubsystemBase {
  /**
   * Creates a new colorMotor.
   */

 
  private final TalonSRX m_leftMotor = new TalonSRX(Constants.m_colorMotorPort);
  private final TalonSRX m_rightMotor = new TalonSRX(Constants.m_simulatedMotorPort);
  

  public colorMotor() {
    System.out.println("The color motor has been initialized"); 
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //Robot.colorButton.whenPressed(new colorMove(m_colorMotor));

  }

  public void driveForward(double speed) {
    System.out.println("DriveForward is running");
    m_leftMotor.set(ControlMode.PercentOutput, speed);
    m_rightMotor.set(ControlMode.PercentOutput, speed);

  }

  public void returnMessage() {
    if (ColorSensor.numberOfChange < 2) {
      System.out.println("from returnMessage: The color has been changed" + ColorSensor.numberOfChange + " times from"
          + ColorSensor.initialColor);
    } if (ColorSensor.numberOfChange<3) {
      
    } else {
      
    }
   }

}