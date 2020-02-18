package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

//importing main color libraries
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
//importing color assist libraries
import edu.wpi.first.wpilibj.I2C;
// import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;


public class ColorSensor extends SubsystemBase{
    // Color ralated subsystems
    public colorMotor m_Drivetrain = new colorMotor();
    private I2C.Port i2cPort = I2C.Port.kOnboard;// sets up the I2C port
    private ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);// sets up a new sensor at the port
    private ColorMatch m_colorMatcher = new ColorMatch();// sets up the color match system

    // creats target colors
    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    // for the color change count vairables
    static public int numberOfChange = 0;
    static boolean isColorTheInitial = true; // used to determine if the color has changed or not
    static boolean controlBooleanCode = true; // used as a control when counting the number of changes
    static boolean hasInitialColorBeenSet = false;
    static public String sensedColor;
    static public String targetColor;
    static public String initialColor; // used as a placeholder for what the first color the color sensors see is
    // public static Command colorMove = new colorMove(m_Drivetrain);

    public ColorSensor() {

        // establishes the color targets
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);

    }

    public void periodic() {                  
        // color related code goes after here
        final Color detectedColor = m_colorSensor.getColor();
        String colorString;
        final ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if (match.color == kBlueTarget) {
            colorString = "Blue";
        } else if (match.color == kRedTarget) {
            colorString = "Red";
        } else if (match.color == kGreenTarget) {
            colorString = "Green";
        } else if (match.color == kYellowTarget) {
            colorString = "Yellow";
        } else {
            colorString = "Unknown";
        }
        //code establishing the initial color that it sees
        if (!hasInitialColorBeenSet) {
            initialColor = colorString;
            hasInitialColorBeenSet = true;
        }
        //code counting the number of times the color changes
        if (!colorString.equals(initialColor)) {
            if (controlBooleanCode) {
                controlBooleanCode = false;
                numberOfChange++;
                System.out.println("The color was changed, and the color has changed " + numberOfChange + " times from "
                        + initialColor);
            }
        } else {
            controlBooleanCode = true;
        }

        sensedColor = colorString;

        SmartDashboard.putString("Initial Color", initialColor);
        SmartDashboard.putNumber("Number of changes from the inital color, " + initialColor, numberOfChange);
        SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
        SmartDashboard.putNumber("Confidence", match.confidence);
        SmartDashboard.putString("Detected Color", colorString);
        
    }

}