// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.BooleanPublisher;
import edu.wpi.first.networktables.BooleanTopic;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.InstantCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SetCubeCone extends InstantCommand {
  boolean cubeCone;
  /**
   * command to set networktables cube vs cone
   * @param cubeCone true for cube, false for cone
   */
  public SetCubeCone(boolean _cubeCone) {
    // Use addRequirements() here to declare subsystem dependencies.
    cubeCone = _cubeCone;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("setting to " + cubeCone);
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable("LEDs");

    BooleanTopic topic = table.getBooleanTopic("cubeCone");
    
    BooleanPublisher pub = topic.publish();
    pub.set(cubeCone);
  }
}
