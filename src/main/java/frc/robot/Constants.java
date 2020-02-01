package frc.robot;

import edu.wpi.first.wpilibj.kinematics.*;

public class Constants {
    public static final int masterRightMotor = 2; // actual 1
    public static final int slaveRightMotor = 4; // actual 0
    public static final int masterLeftMotor = 1; // actual 14
    public static final int slaveLeftMotor = 3; // actual 15
    public static final int slaveClimb = 5;
    public static final int masterClimb = 6;

    public static final double Kp = .04;
    public static final double min_command = .1;
    public static final double distance_adjust = .05;
    public static final double ticksPerRev = 12.0; // Without Gear Reduction
    public static final double maxVelocity = 9.0; // meters per sec
    public static final double wheelDiameter = 0.1016;
    public static final double driveTrain_width = .4699;

    public static final double targetHeight = 83.25; //inches
    public static final double limelightAngle = 54.79; //on babybot, in degrees
    public static final double limelightHeight = 9; //inches

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
}
