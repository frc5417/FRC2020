package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.*;

public class MySmartDashboard extends Robot{
    @Override
    public void robotInit() {
        SmartDashboard.putNumber("AutoMode", 0);
    }
    @Override
    public void robotPeriodic() {
        if (SmartDashboard.getBoolean("Selection 1", false)) {
        SmartDashboard.putBoolean("Selection 1", false);
        //SmartDashboard.putBoolean("Selection 3", false);
        SmartDashboard.putNumber("AutoMode", 1);
        } 
        else if (SmartDashboard.getBoolean("Selection 2", false)) {
            SmartDashboard.putBoolean("Selection 2", false);
            //SmartDashboard.putBoolean("Selection 3", false);
            SmartDashboard.putNumber("AutoMode", 2);
        }
        else if (SmartDashboard.getBoolean("Selection 3", false)) {
            SmartDashboard.putBoolean("Selection 3", false);
            //SmartDashboard.putBoolean("Selection 2", false);
            SmartDashboard.putNumber("AutoMode", 3);
        }
    }
}