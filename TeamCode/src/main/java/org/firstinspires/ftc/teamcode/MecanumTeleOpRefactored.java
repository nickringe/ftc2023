package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class MecanumTeleOpRefactored extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        DcMotor hookMotor = hardwareMap.dcMotor.get("hookMotor");
        Servo airplaneServo = hardwareMap.servo.get("airplaneServo");

        //to track whether or not we want to cap the robot's drive speed at 25%
        boolean capPower25 = false;

        //Go to build team
        //Ask them to tell you exactly which motors and which Servos
        //Control each button on both gamepads

        //Take the paper with you that has the buttons mapped out.
        //Write on that paper

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            // Check if power should be capped
            if (capPower25) {
                // Cap the motor powers at 25%
                double maxPower = 0.25;
                frontLeftPower = Math.max(-maxPower, Math.min(maxPower, frontLeftPower));
                backLeftPower = Math.max(-maxPower, Math.min(maxPower, backLeftPower));
                frontRightPower = Math.max(-maxPower, Math.min(maxPower, frontRightPower));
                backRightPower = Math.max(-maxPower, Math.min(maxPower, backRightPower));
            }

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

            // Check if dpad down button is pressed to cap power
            if (gamepad1.dpad_down) {
                capPower25 = true;
            } else if (gamepad1.dpad_up) {
                // If dpad up button is pressed, revert to original functionality
                capPower25 = false;
            }

            //consider refactoring buttons to a similar logic
            //this allows them to start/stop when button is pushed

            // Hook motor extends upwards towards the rigging
            if (gamepad2.right_trigger > 0) {
                hookMotor.setPower(0.5); // Extends the hook upwards
            } else {
                hookMotor.setPower(0); // Stops the motor when the trigger is released
            }

            /* This is what the old code looked like, for reference:

            if (gamepad2.right_trigger > 0) {
                hookMotor.setPower(0.5); //extends the hook upwards
                sleep(1000); //for 1 second
                hookMotor.setPower(0); //then stops
            }
             */
            
            //added to slow down the loop - common practice to reduce
            //likelyhood of erratic behavior
            idle();
        }
    }

}
