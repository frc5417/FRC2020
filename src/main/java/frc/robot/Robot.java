/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//cody's comment
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
import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.DriverStation;

/*Moved to Color.java
//importing main color libraries
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
//importing color assist libraries
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.util.Color;
*/




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


  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tv = table.getEntry("tv");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry ts = table.getEntry("ts");
  NetworkTableEntry ledMode = table.getEntry("ledMode");
  public static colorMotor colorMotor = new colorMotor();

  public static Limelight l = new Limelight();
  public static Joystick pad = new Joystick(0);
  public static Drive d = new Drive();
  public static Climb c = new Climb();
  public static Intake i = new Intake();
  public static RobotContainer r = new RobotContainer();
  public static Command a;
  public static ColorSensor m_ColorSensor = new ColorSensor();
  public static Command colorMove = new colorMove(colorMotor);
  
  
  /*
  Moved to Color.java

  // Color ralated subsystems
  public static colorMotor m_Drivetrain = new colorMotor();
  private I2C.Port i2cPort = I2C.Port.kOnboard;//sets up the I2C port
  private ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);//sets up a new sensor at the port
  private ColorMatch m_colorMatcher = new ColorMatch();//sets up the color match system

  //initializing commands
  private Command m_Driveforward;
 //creats target colors
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  //for the color change count vairables
  static public int numberOfChange = 0;
  static boolean isColorTheInitial = true; //used to determine if the color has changed or not
  static boolean controlBooleanCode = true; //used as a control when counting the number of changes
  static boolean hasInitialColorBeenSet = false;
  static public String initialColor; //used as a placeholder for what the first color the color sensors see is
  public static Command colorMove = new colorMove(m_Drivetrain);
  */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    /*Moved to Color.java, need to call in init
    //establishes the color targets
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
    */
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
    CommandScheduler.getInstance().run();


    /*Moved to Color.java
    //color related code goes after here
    final Color detectedColor = m_colorSensor.getColor();
    String colorString;
    final ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    
    
  

    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    if (!Robot.hasInitialColorBeenSet) {
      Robot.initialColor = colorString;
      Robot.hasInitialColorBeenSet = true;
    }

    if(!colorString.equals(Robot.initialColor)){
      if(Robot.controlBooleanCode){
        Robot.controlBooleanCode = false;
        Robot.numberOfChange++;
        System.out.println("The color was changed, and the color has changed " + Robot.numberOfChange + " times from " + Robot.initialColor );
        
      }        
  } else{
    Robot.controlBooleanCode = true;
  }
    




    SmartDashboard.putString("Initial Color", Robot.initialColor);
    SmartDashboard.putNumber("Number of changes from the inital color, " + Robot.initialColor, Robot.numberOfChange);
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);

    */

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
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    colorMove.schedule();

    
    /*
    if (pad.getRawButton(1)){
      
      ledMode.setNumber(3);
    }
    else{
      ledMode.setNumber(1);
      //d.SetPower(pad.getRawAxis(1), pad.getRawAxis(5));

    i.runInternalBelt(pad.getRawButtonPressed(3)); 
    i.runFeeder(pad.getRawButtonPressed(3)); //check this to make sure its the right button
    i.runInternalBeltBackwards(pad.getRawButtonPressed(4));
    i.runFeederBackwards(pad.getRawButtonPressed(4)); //check this to make sure its the right button
    }
    */
    CommandScheduler.getInstance().run();



  }


  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
