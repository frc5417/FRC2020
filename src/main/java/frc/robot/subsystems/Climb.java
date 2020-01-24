package frc.robot.subsystems;

// climb code goes here

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;


public class Climb {
    
    //Creating Solenoid Variable
    public Solenoid solenoid;
    public CANSparkMax testMotor;
    public Compressor airCompressor = new Compressor(1);
    public Joystick joystick = new Joystick(0);


    //Method For Switching Solenoid
    public void solenoidSwitch(){
        solenoid = new Solenoid(2);
        if(joystick.getRawButton(1)){
          solenoid.set(true);
        }
      
    }
  
  public void testMotor(){
    CANSparkMax testMotor = new CANSparkMax(0, MotorType.kBrushless);     // 0 is the default deviceID, but it should be changed through the Windows SparkMax client
    if(joystick.getRawButtonPressed(2)){
    	testMotor.set(.5);  // sets speed of motor
    }
    if (joystick.getRawButtonReleased(2)) {
      testMotor.set(0); // sets speed of motor to 0 
    }
    
  }
}