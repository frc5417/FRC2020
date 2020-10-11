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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.*;
import frc.robot.Commands.*;


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

  public static Limelight limelight = new Limelight();
  public static Joystick pad = new Joystick(0);
  public static Drive drive = new Drive();
  public static Climb climb = new Climb();
  public static Intake intake = new Intake();
  public static RobotContainer robotContainer = new RobotContainer();
  public static Command autonomous;
  public static Command align = new AutoAlign(limelight);
  public static Command tankDrive = new TankDrive(drive);
  public static Command intakeF = new RunIntakeForward(intake);
  public static Command intakeB = new RunIntakeBackwards(intake);
  public static Command climbL = new ClimbExtendLatch(climb);
  public static Command climbU = new ClimbUnLatch(climb);
  //public static Command shift = new Shift(drive);
  public static Command shoot = new Shoot(intake);
  public static TrajectoryFollowing trajectoryFollowing = new TrajectoryFollowing();

  
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
    
    limelight.setX(tx);
    limelight.setY(ty);
    limelight.setArea(ta);
    limelight.setV(tv);

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
    trajectoryFollowing.zeroHeading();
    trajectoryFollowing.resetEncoders();

    autonomous = robotContainer.getAutonomousCommand();
    if (autonomous != null) {
      autonomous.schedule();
    }


  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
    System.out.println(trajectoryFollowing.getPose());
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    intake.count += 20;
    tankDrive.schedule();
    //align.schedule();
    climbL.schedule();
    climbU.schedule();
    //shift.schedule();
    drive.setDefaultCommand(tankDrive);
    robotContainer.aPad.whileHeld(align);
    shoot.schedule();
    //r.xPad.whileHeld(intakeF);
    //r.yPad.whileHeld(intakeB);
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
