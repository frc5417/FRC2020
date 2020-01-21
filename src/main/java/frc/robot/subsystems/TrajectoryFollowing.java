package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.kinematics.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import java.lang.Math;

public class TrajectoryFollowing extends SubsystemBase{
    TalonSRX driveMasterLeft;
    TalonSRX driveMasterRight;
    VictorSPX driveSlaveLeft;
    VictorSPX driveSlaveRight;

    AHRS gyro;

    DifferentialDriveKinematics kinematics;
    DifferentialDriveOdometry odometry;

    public TrajectoryFollowing() {
        
        driveMasterRight = new TalonSRX(Constants.masterRightMotor);
        driveMasterLeft = new TalonSRX(Constants.masterLeftMotor);
        driveSlaveRight = new VictorSPX(Constants.slaveRightMotor);
        driveSlaveLeft = new VictorSPX(Constants.slaveLeftMotor);

        gyro = new AHRS(Port.kMXP);

        kinematics = new DifferentialDriveKinematics(Constants.driveTrain_width);
        odometry = new DifferentialDriveOdometry(getHeading());


        driveSlaveLeft.set(ControlMode.Follower, driveMasterLeft.getDeviceID());
        driveSlaveRight.set(ControlMode.Follower, driveMasterRight.getDeviceID());

        driveMasterRight.setInverted(false);
        driveMasterLeft.setInverted(true);
        
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
        odometry.update(getHeading(), getSpeeds().leftMetersPerSecond, getSpeeds().rightMetersPerSecond);
    }
}
