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
import edu.wpi.first.wpilibj.kinematics.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class RobotContainer{

    TrajectoryFollowing pathfollower;
    Joystick pad;
    JoystickButton aPad;

    public RobotContainer(){
        pathfollower = new TrajectoryFollowing();
        pad = new Joystick(0);
        aPad = new JoystickButton(pad, 1);
    }

    public Command getAutonomousCommand(){
        
        RamseteCommand ramseteCommand = new RamseteCommand(pathfollower.getTraj(), pathfollower::getPose, new RamseteController(2, 0.7), new SimpleMotorFeedforward(Constants.kVolts, Constants.kVSPM, Constants.kVSSPM), pathfollower.getKinematics(), pathfollower::getSpeeds, new PIDController(Constants.kPDriveVelocity, 0, 0), new PIDController(Constants.kPDriveVelocity, 0, 0), pathfollower::tankDriveVolts, pathfollower);//add ref to differential drive object in trajectoryfollowing);

        return ramseteCommand.andThen(() -> pathfollower.tankDriveVolts(0, 0));
    }
    public double leftSpeed(){
        return pad.getRawAxis(1);
    }
    public double rightSpeed(){
        return pad.getRawAxis(5);
    }
    public double climbPower(){
        return pad.getRawAxis(3);
    }
    public boolean aButton(){
        return pad.getRawButton(1);
    }
    public boolean bButton(){
        return pad.getRawButton(2);
    }

    public boolean xButton(){
        return pad.getRawButton(4);
    }

    public boolean yButton(){
        return pad.getRawButton(3);
    }

}

