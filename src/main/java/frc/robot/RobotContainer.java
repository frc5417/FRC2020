package frc.robot;

import frc.robot.subsystems.*;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.*;
import java.util.List;

public class RobotContainer{

public Command getAutonomousCommand(){
    TrajectoryConfig trajectoryConfig = new TrajectoryConfig(Constants.maxVelocity, 3).setKinematics(Constants.kinematics);

        Trajectory traj = TrajectoryGenerator.generateTrajectory(new Pose2d(0, 0, new Rotation2d(0)), List.of(new Translation2d(1, 1), new Translation2d(1, 2)), new Pose2d(3, 0, new Rotation2d(0)), trajectoryConfig);
        
        RamseteCommand ramseteCommand = new RamseteCommand(traj, pose, new RamseteController(2, 0.7), new SimpleMotorFeedforward(Constants.kVolts, Constants.kVSPM, Constants.kVSSPM), kinematics, getSpeeds(), new PIDController(Constants.kPDriveVelocity, 0, 0), new PIDController(Constants.kPDriveVelocity, 0, 0), //add ref to differential drive object in trajectoryfollowing);
    }

}

