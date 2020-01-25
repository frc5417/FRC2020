package frc.robot;

public class Constants {
    public static final int masterRightMotor = 2;
    public static final int slaveRightMotor = 4;
    public static final int masterLeftMotor = 1;
    public static final int slaveLeftMotor = 3;
    public static final int slaveClimb = 5;
    public static final int masterClimb = 6;

    public static final double Kp = .03;
    public static final double min_command = .05;
    public static final double distance_adjust = .05;
    public static final double ticksPerRev = 12.0; // Without Gear Reduction
    public static final double maxVelocity = 9.0; // meters per sec
    public static final double wheelDiameter = 0.1016;
    public static final double driveTrain_width = .4699;
    // ideal distance 90 inches
}
