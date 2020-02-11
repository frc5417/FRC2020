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
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

// import edu.wpi.first.wpilibj.Talon;
// import edu.wpi.first.wpilibj.Victor;
import frc.robot.Constants;
import frc.robot.subsystems.ColorSensor;

public class colorMotor extends SubsystemBase {
  /**
   * Creates a new colorMotor.
   */
  private final VictorSPX m_leftMotor = new VictorSPX(Constants.m_colorMotorPort);

  public colorMotor() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  public void driveFoward(double d) {
    m_leftMotor.set(ControlMode.PercentOutput, d);

  }

  public void returnMessage() {
    if (ColorSensor.numberOfChange < 3) {
      System.out.println("from returnMessage: The color has been changed" + ColorSensor.numberOfChange + " times from"
          + ColorSensor.initialColor);
    } else {
    }
  }

}