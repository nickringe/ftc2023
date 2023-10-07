package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
//@Autonomous
public class FirstOpModeTest extends LinearOpMode {

    private DcMotor rightFrontWheel;
    private DcMotor rightBackWheel;
    private DcMotor leftFrontWheel;
    private DcMotor leftBackWheel;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;
    private DcMotor verticalArm;

    @Override
    public void runOpMode() throws InterruptedException {

        rightFrontWheel = hardwareMap.get(DcMotor.class, "rightFrontWheel");
        rightBackWheel = hardwareMap.get(DcMotor.class, "rightBackWheel");
        leftFrontWheel = hardwareMap.get(DcMotor.class, "leftFrontWheel");
        leftBackWheel = hardwareMap.get(DcMotor.class, "leftBackWheel");
        sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        servoTest = hardwareMap.get(Servo.class, "servoTest");
        verticalArm = hardwareMap.get(DcMotor.class, "VerticalArm");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();

            setAllWheelPowerTo75(rightBackWheel, rightFrontWheel, leftBackWheel, leftFrontWheel);
            sleep(2000);
            setAllWheelPowerTo0(rightFrontWheel, rightBackWheel, leftBackWheel, leftFrontWheel);

        }
    }
    public void setAllWheelPowerTo75 (DcMotor dc, DcMotor dc2, DcMotor dc3, DcMotor dc4) {
        dc.setPower(0.75);
        dc2.setPower(0.75);
        dc3.setPower(0.75);
        dc4.setPower(0.75);
        return;
    }
    public void setAllWheelPowerTo0 (DcMotor dc, DcMotor dc2, DcMotor dc3, DcMotor dc4) {
        dc.setPower(0);
        dc2.setPower(0);
        dc3.setPower(0);
        dc4.setPower(0);
        return;
    }
}
