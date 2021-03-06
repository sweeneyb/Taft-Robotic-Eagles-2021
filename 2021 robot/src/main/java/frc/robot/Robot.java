// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import static edu.wpi.first.wpilibj.Timer.delay;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.PWMSparkMax;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

  private CANSparkMax leftMotor = new CANSparkMax(1, MotorType.kBrushless);
  private CANSparkMax rightMotor = new CANSparkMax(2, MotorType.kBrushless);

  private DifferentialDrive drivechain = new DifferentialDrive(leftMotor, rightMotor);

  private Joystick joy1 = new Joystick(0);

  private PWMSparkMax leftshooter = new PWMSparkMax(2);
  private PWMSparkMax rightshooter = new PWMSparkMax(3);

  //private double startTime;

  @Override
  public void robotInit() {
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    //startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    /*double DELAY_TIME = 1;
    
    double time = Timer.getFPGATimestamp();

    if (time - startTime < 3) {
    leftMotor.set(0);
    rightMotor.set(0.6);
  }

  delay(DELAY_TIME);

  if ((time - startTime >3) && (time - startTime < 6)) {
    leftMotor.set(0.6);
    rightMotor.set(0);
  }

  delay(DELAY_TIME);

  if ((time - startTime > 6) && (time -startTime < 11 )) {
    leftMotor.set(0.6);
    rightMotor.set(0);
  }

  /*if (time - startTime < 13) {
    leftMotor.set(0.7);
    rightMotor.set(0.1);
  }

  if (time - startTime <14) {
    leftMotor.set(0.3);
    rightMotor.set(0.6);    
  }

    else {
    leftMotor.set(0);
    rightMotor.set(0);
    }*/
  }

  @Override
  public void teleopInit() {
    leftshooter.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    boolean shooterspeed = joy1.getRawButton(2);

    double flywheel = 0.4;
    double flywheelstop = 0;
    
    double speed = -joy1.getRawAxis(1) * 0.6;
    double turn = -joy1.getRawAxis(0) * 0.3;

    double left = speed + turn;
    double right = speed - turn;

    drivechain.tankDrive(-left, right);

    if (shooterspeed) {
      leftshooter.set(flywheel);
      rightshooter.set(flywheel);
    }

    else {
      leftshooter.set(flywheelstop);
      rightshooter.set(flywheelstop);
    }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
