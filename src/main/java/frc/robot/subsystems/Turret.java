package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class Turret extends SubsystemBase{

    TalonSRX turret = new TalonSRX(Constants.turretPort);

    public Turret(){
        turret.setNeutralMode(NeutralMode.Coast);
    }

    public void setTurretPower(double power){
        turret.set(ControlMode.PercentOutput, power);
    }
}