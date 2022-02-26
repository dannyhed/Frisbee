package frc.robot; //Make sure you have this otherwise your robot.java file woln't recognize it in the import

public class RobotMap{ //This class needs to have the same name as the file containing it, and you need to call this name in your import
    
    //Joystick
    public static int JOYSTICK = 0; //The default port is 0 but you can change it in the driver station
    //Public means any file can access the value
    //Static means it cant be changed through the program (cause the ports aren't going to change while the robots running)
    //Int means it's an interger


    // Motors
    public static int FRONT_LEFT = 3;
    public static int BACK_LEFT = 4;
    public static int FRONT_RIGHT = 2;
    public static int BACK_RIGHT = 1;

    public static int SHOOTER = 5;

    //Solenoids
    public static int GEAR_UP = 1;
    public static int GEAR_DOWN = 2;
    public static int GRIP_UP = 2;
    public static int GRIP_DOWN = 4;
     
}