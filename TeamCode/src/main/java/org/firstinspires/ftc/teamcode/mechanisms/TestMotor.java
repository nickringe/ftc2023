package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestMotor extends TestItem {

    private double speed;
    private DcMotor motor;

    public TestMotor(String description, double speed, DcMotor motor){
        super(description);
        this.speed = speed;
        this.motor = motor;
    }

    @Override
    public void run(boolean on, Telemetry telemetry) {
    if (on) {
        motor.setPower(speed);
    } else {
        motor.setPower(0);
        }
    telemetry.addData("Encoder", motor.getCurrentPosition());
    }

}
