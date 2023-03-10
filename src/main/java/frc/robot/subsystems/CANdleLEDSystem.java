// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.led.CANdle;

import edu.wpi.first.math.spline.CubicHermiteSpline;
import edu.wpi.first.networktables.BooleanEntry;
import edu.wpi.first.networktables.BooleanTopic;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CANdleLEDSystem extends SubsystemBase {
  private final CANdle candle = new CANdle(0);
  private final int ledCount = 65;
  /** true for cube, false for cone */
  final BooleanEntry cubeCone;
  boolean prevCubeCone = false;

  NetworkTableInstance inst = NetworkTableInstance.getDefault();
  NetworkTable table = inst.getTable("LEDsData");
  /** Creates a new CANdleLEDSystem. */
  public CANdleLEDSystem() {
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable("LEDs");

    BooleanTopic topic = table.getBooleanTopic("cubeCone");

    cubeCone = topic.getEntry(false);
  }

  public CommandBase setPurple() {
    return this.runOnce(() -> setPrupMethod());
  }
  
  public CommandBase setYellow() {
    return this.runOnce(() -> setYellowMethod());
  }
  public CommandBase setSolidColor(int r, int g, int b) {
    return this.runOnce(() -> candle.setLEDs(r, g, b));
  }
  private void setPrupMethod() {
    setColor(200, 0, 200);
  }
  private void setYellowMethod() {
  //  cand
    setColor(0, 255, 0);
  }
  private void updateLEDCubeCone() {
    if (cubeCone.getAsBoolean()) {
      candle.setLEDs(200, 0, 200);
    }
    else {
      candle.setLEDs(160, 200, 5);
    }
  }

  private void setColor(int r, int g, int b) {
    candle.setLEDs(r, g, b);
  }
  @Override
  public void periodic() {
    if(prevCubeCone != cubeCone.getAsBoolean()){
      updateLEDCubeCone();
    }
    prevCubeCone = cubeCone.getAsBoolean();
   
  }
}
