package frc.robot;

import edu.wpi.first.wpilibj.kinematics.*;

public class Constants {
    public static final int masterRightMotor = 1; // actual 1 test 2
    public static final int slaveRightMotor = 17; // actual 17 test 4
    public static final int masterLeftMotor = 14; // actual 14 test 1
    public static final int slaveLeftMotor = 15; // actual 15 test 3
    public static final int LClimb = 13;
    public static final int RClimb = 2;
    public static final int intakeRoller = 11;
    public static final int intakeMotorTop = 10;
    public static final int intakeMotorBottom = 4;
    public static final int shooterMaster = 12;
    public static final int shooterSlave = 3;
    public static final int turretPort = 9;

    public static final double Kp = .05;
    public static final double min_command = .05; //real bot .1
    public static final double distance_adjust = .05;
    public static final double ticksPerRev = 12.0; // Without Gear Reduction
    public static final double maxVelocity = 9.0; // meters per sec
    public static final double wheelDiameter = 0.1016;
    public static final double driveTrain_width = .4699;

    public static final double targetHeight = 83.25; //inches
    public static final double limelightAngle = 54.79; //on babybot (54.79), in degrees
    public static final double limelightHeight = 29; //inches

    public static final double desiredDistance = 60;
    public static final double kVolts = .461;
    public static final double kVSPM = 1.21; // volt seconds per meter
    public static final double kVSSPM = .425; // volt second squared per meter
    public static final double rSquared = .989;
    public static final double trackWidth = .599426775;
    public static final double kPDriveVelocity = 33.3;
    public static final double kD = 16.3;
    public static final double maxVolts = 28;

    public static final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Constants.driveTrain_width);
    // ideal distance 90 inches

    //Shooter Constants
    public static final double shootkP = 9e-20; 
    public static final double shootkI = 7e-52;
    public static final double shootkD = 0; 
    public static final double shootkIz = 0; 
    public static final double shootkFF = 19e-9;  
    public static final double shootkMaxOutput = 1; 
    public static final double shootkMinOutput = -1;
    public static final double shootMaxRPM = 5700;
    public static final double shootsetPointConstant = -4000;
}
