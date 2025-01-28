// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.sim.SparkMaxSim;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  private SparkMax pivot = new SparkMax(50, MotorType.kBrushless);

  private RelativeEncoder pivotEnc = pivot.getEncoder();

  private SparkMaxSim pivotSim;

  public Arm() {
    if (Constants.currentMode == Constants.Mode.SIM) {
      pivotSim = new SparkMaxSim(pivot, DCMotor.getNEO(1));
    }
  }

  public void raise() {
    pivot.set(.5);
  }

  public void lower() {
    pivot.set(-.5);
  }

  public void setMotor(double speed) {
    pivot.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("pivotPos", pivotEnc.getPosition());
  }

  @Override
  public void simulationPeriodic() {
    double velo = pivot.get() * 1.0;
    double voltage = RoboRioSim.getVInVoltage();
    pivotSim.iterate(velo, voltage, .2);
  }
}
