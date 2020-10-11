package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.kinematics.*;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.Constants;
//import jaci.pathfinder.Trajectory;
import edu.wpi.first.wpilibj.geometry.*;
import java.lang.Math;


public class TrajectoryFollowing extends SubsystemBase{
    TalonSRX driveMasterLeft;
    TalonSRX driveMasterRight;
    VictorSPX driveSlaveLeft;
    VictorSPX driveSlaveRight;

    public AHRS gyro;

    DifferentialDriveKinematics kinematics;
    DifferentialDriveOdometry odometry;
    Pose2d pose;
    public Rotation2d gyroAngle;

    Trajectory traj;

    public TrajectoryFollowing() {
        
        driveMasterRight = new TalonSRX(Constants.masterRightMotor);
        driveMasterLeft = new TalonSRX(Constants.masterLeftMotor);
        driveSlaveRight = new VictorSPX(Constants.slaveRightMotor);
        driveSlaveLeft = new VictorSPX(Constants.slaveLeftMotor);

        

        gyro = new AHRS(Port.kMXP);

        kinematics = new DifferentialDriveKinematics(Constants.driveTrain_width);
        
        pose = new Pose2d();

        resetEncoders();
        zeroHeading();

        odometry = new DifferentialDriveOdometry(getHeading()/*, pose*/); //try with and without pose as argument

        driveSlaveLeft.set(ControlMode.Follower, driveMasterLeft.getDeviceID());
        driveSlaveRight.set(ControlMode.Follower, driveMasterRight.getDeviceID());

        driveMasterRight.setInverted(true);
        driveMasterLeft.setInverted(true);

        //TrajectoryConfig trajectoryConfig = new TrajectoryConfig(Constants.maxVelocity, 3).setKinematics(kinematics);

        //traj = TrajectoryGenerator.generateTrajectory(new Pose2d(0, 0, new Rotation2d(0)), List.of(new Translation2d(1, 1), new Translation2d(1, 2)), new Pose2d(3, 0, new Rotation2d(0)), trajectoryConfig);
        
        //RamseteCommand ramseteCommand = new RamseteCommand(traj, pose, new RamseteController(2, 0.7), new SimpleMotorFeedforward(Constants.kVolts, Constants.kVSPM, Constants.kVSSPM), kinematics, getSpeeds(), new PIDController(Constants.kPDriveVelocity, 0, 0), new PIDController(Constants.kPDriveVelocity, 0, 0), thisTraj::tankDriveVolts, thisTraj::);
    }

    public void trajectory(){

    }

    public Rotation2d getHeading(){
        return Rotation2d.fromDegrees(-gyro.getAngle());
    }

    // return speed of each side in meters per sec
    public DifferentialDriveWheelSpeeds getSpeeds(){
        return new DifferentialDriveWheelSpeeds(driveMasterLeft.getSelectedSensorVelocity() / (50 / 6) * 2 * Math.PI / 60, driveMasterRight.getSelectedSensorVelocity() / (50 / 6) * 2 * Math.PI / 60);
    }

    @Override
    public void periodic(){
        pose = odometry.update(getHeading(), driveMasterLeft.getSelectedSensorPosition(), driveMasterRight.getSelectedSensorPosition());
    }

    public void tankDriveVolts(double leftVolts, double rightVolts){
        driveMasterLeft.set(ControlMode.PercentOutput, leftVolts / Constants.maxVolts);
        driveMasterRight.set(ControlMode.PercentOutput, rightVolts / Constants.maxVolts);
    }

    public DifferentialDriveKinematics getKinematics(){
        return this.kinematics;
    }

    public DifferentialDriveOdometry getOdometry(){
        return this.odometry;
    }

    public Pose2d getPose(){
        return odometry.getPoseMeters();
    }

    /*public Trajectory getTraj(){
        return traj;
    }*/

    public void resetOdometry(Pose2d pose){
        resetEncoders();
        odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading().getDegrees()));
    }

    public void resetEncoders(){
        driveMasterLeft.setSelectedSensorPosition(0);
        driveMasterRight.setSelectedSensorPosition(0);
    }

    public double getAverageEcoderDistance(){
        return ((driveMasterLeft.getSelectedSensorPosition() * .319 / 100) + (driveMasterRight.getSelectedSensorPosition() * .319 / 100)) / 2;
    }

    public void zeroHeading(){
        gyro.reset();
    }

    public double getTurnRate(){
        return gyro.getRate();
    }

}
