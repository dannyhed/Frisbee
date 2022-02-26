/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

// <-- This is a comment, using the 2 slashes means the computer will ignore this text when compiling
// This can be very useful to denote what something does or to add additional info

//A lot of this is automagically generated, and to create another just hit alt+shift+p and type "create a new project"
//the option should pop up and when you select use a template for java for a timed robot.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor; // <--These are here to allow their respective parts to work (if you hit 
        //tab when typing the class name it will automatically import then, or you can copy and paste these lines)
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX; //This is here to allow TalonSRX to work
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.RobotMap; //This isn't necessary but having a robotmap makes changing ports easy 

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  // Initalize robot parts here (talons, compressors, joysticks)
  Joystick joy = new Joystick(RobotMap.JOYSTICK);

  TalonSRX backLeft = new TalonSRX(RobotMap.BACK_LEFT);
  VictorSPX frontLeft = new VictorSPX(RobotMap.FRONT_LEFT);
  TalonSRX backRight = new TalonSRX(RobotMap.BACK_RIGHT);
  VictorSPX frontRight = new VictorSPX(RobotMap.FRONT_RIGHT);

  TalonSRX shooter = new TalonSRX(RobotMap.SHOOTER);
 // TalonSRX lift = new TalonSRX(1);
// TalonSRX intake = new TalonSRX(12);

  Compressor comp = new Compressor(0, PneumaticsModuleType.CTREPCM); //Compressors don't need a port because they have their own slot in the power supply

  DoubleSolenoid storage = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
  DoubleSolenoid flinger = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);

  
 // DoubleSolenoid griper = new DoubleSolenoid(RobotMap.GRIP_UP,RobotMap.GRIP_DOWN);

  public int grip = 0;
  float spd = 0.75f;

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    backLeft.follow(frontLeft); //The back talons are set as followers here so that we only have to set the front 
                                                                                              //ones speeds later
    backRight.follow(frontRight);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically (like every .02 seconds) during operator control.
   */
  @Override
  public void teleopPeriodic() {
    frontLeft.set(ControlMode.PercentOutput, spd * joy.getRawAxis(1));
    frontRight.set(ControlMode.PercentOutput, spd * -joy.getRawAxis(5));

    shooter.set(ControlMode.PercentOutput, joy.getRawAxis(3));

    if (joy.getRawButton(6)) {
      flinger.set(Value.kReverse);
    }
    else flinger.set(Value.kForward);

    if (joy.getRawButton(5)) {
      storage.set(Value.kForward);
    }
    else storage.set(Value.kReverse);
    /*
    frontRight.set(ControlMode.PercentOutput, spd * (-joy.getRawAxis(1) - joy.getRawAxis(4)));
    frontLeft.set(ControlMode.PercentOutput, spd * (joy.getRawAxis(1) - joy.getRawAxis(4)));
    /*
    /*
    frontRight.set(ControlMode.PercentOutput, getRight());
    frontLeft.set(ControlMode.PercentOutput, getLeft());
    if(joy.getRawButton(6)){
      gearChange.set(Value.kForward);
      System.out.println("forward");
    }
    else if(joy.getRawButton(7)){
      gearChange.set(Value.kReverse);
      System.out.println("reverse");
    }
    else{
      gearChange.set(Value.kOff);
    }*/
  /*  if(grip > 0){
      grip = grip - 1;
    }
    if(grip < -1){
      grip = grip + 1;
    }

    if(joy.getRawButton(1)){
      if(grip == 0){
        griper.set(Value.kForward);
        grip = -50;
        System.out.println("Foward");
      }
      else if (grip == -1){
        griper.set(Value.kReverse);
        grip = 50;
        System.out.println("Reverse");
      }
    }
    

    if(joy.getRawButton(8)){
      intake.set(ControlMode.PercentOutput, .7);
    }
    else if(joy.getRawButton(9)){
      intake.set(ControlMode.PercentOutput, -.7);
    }
    else{
      intake.set(ControlMode.PercentOutput, 0);
    }

    if(joy.getRawButton(11)){
      lift.setSelectedSensorPosition(0);
    }
    if (joy.getRawButton(3)){
      lift.set(ControlMode.PercentOutput, -0.8);
    }
    else if (joy.getRawButton(2)){
      lift.set(ControlMode.PercentOutput, 0.8);
    }
    else if(joy.getRawButton(4)){
      if(lift.getSelectedSensorPosition()>400){
        lift.set(ControlMode.PercentOutput, 0.3);
      }
      else{
        lift.set(ControlMode.PercentOutput, 0);
      }
    }
    else if(joy.getRawButton(5)){
      if(lift.getSelectedSensorPosition()<40000){
        lift.set(ControlMode.PercentOutput, -0.7);
      }
      else if(lift.getSelectedSensorPosition()>45000){
        lift.set(ControlMode.PercentOutput, 0.7);
      }
      else{
        lift.set(ControlMode.PercentOutput, 0);
      }
    }
    else{
      lift.set(ControlMode.PercentOutput, 0);
    }*/
  }


  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

}
