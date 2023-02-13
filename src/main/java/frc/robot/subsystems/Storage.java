// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Storage extends SubsystemBase {

  public TalonFX top;
  public TalonFX bottom; 
  public DigitalInput topSensor;
  public DigitalInput bottomSensor; 
  /** Creates a new Storage. */
  public Storage() {
    top = new TalonFX(0);
    bottom = new TalonFX(11);
    topSensor = new DigitalInput(1) ; 
    bottomSensor = new DigitalInput(2);
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("top sensor state", topSensor.get());
    SmartDashboard.putBoolean("bottom sensor state", bottomSensor.get());
  }
}
