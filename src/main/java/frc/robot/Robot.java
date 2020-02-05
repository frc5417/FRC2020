/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//hi
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick; 
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.subsystems.Limelight;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.*;
import frc.robot.commands.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */


  public NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  public NetworkTableEntry tv = table.getEntry("tv");
  public NetworkTableEntry tx = table.getEntry("tx");
  public NetworkTableEntry ty = table.getEntry("ty");
  public NetworkTableEntry ta = table.getEntry("ta");
  public NetworkTableEntry ts = table.getEntry("ts");
  public NetworkTableEntry ledMode = table.getEntry("ledMode");

  public static Limelight l = new Limelight();
  public static Joystick pad = new Joystick(0);
  public static Drive d = new Drive();
  public static Climb c = new Climb();
  public static Intake i = new Intake();
  public static RobotContainer r = new RobotContainer();
  public static Command a;
  public static Command align = new AutoAlign(l);
  public static Command drive = new TankDrive(d);
  public static Command intakeF = new RunIntakeForward(i);
  public static Command intakeB = new RunIntakeBackwards(i);
  public static Command climb = new ClimbExtendLatch(c);

  
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    l.setX(tx);
    l.setY(ty);
    l.setArea(ta);
    l.setV(tv);
    //aut.setY(ty);
    //aut.printX();

  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {

    a = r.getAutonomousCommand();
    if (a != null) {
      a.schedule();
    }


  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    drive.schedule();
    //align.schedule();
    climb.schedule();
    d.setDefaultCommand(drive);
    r.aPad.whileHeld(align);
    r.xPad.whileHeld(intakeF);
    r.yPad.whileHeld(intakeB);
    CommandScheduler.getInstance().run();
    
    
    if (pad.getRawButton(1)){
      
      ledMode.setNumber(3);
    }
    else{
      ledMode.setNumber(1);
    }

    /*
    i.runInternalBelt(pad.getRawButtonPressed(3)); 
    i.runFeeder(pad.getRawButtonPressed(3)); //check this to make sure its the right button
    i.runInternalBeltBackwards(pad.getRawButtonPressed(4));
    i.runFeederBackwards(pad.getRawButtonPressed(4)); //check this to make sure its the right button
    }
    */



  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    CommandScheduler.getInstance().run();
  }
}
