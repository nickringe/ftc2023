package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

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
        DistanceSensor distanceSensor = hardwareMap.get(DistanceSensor.class, "distanceSensor");

        //to track whether or not we want to cap the robot's drive speed at 25%
        boolean capPower25 = false;

        double maxSpeed = 1.0;  // Maximum speed when not close to an object
        double minDistance = 12.0;  // Minimum distance to cap the speed (in inches)


        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            // Read distance from the sensor
            double currentDistance = distanceSensor.getDistance(DistanceUnit.INCH);

            // Calculate the speed adjustment based on distance
            double speedAdjustment = (currentDistance > minDistance) ? 1.0 : 0.25;

            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            // Calculate motor powers with normalization and conditional scaling
            double frontLeftPower = (y + x + rx) / (Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0)) * speedAdjustment;
            double backLeftPower = (y - x + rx) / (Math.max(Math.abs(y) - Math.abs(x) - Math.abs(rx), 1.0)) * speedAdjustment;
            double frontRightPower = (y - x - rx) / (Math.max(Math.abs(y) - Math.abs(x) + Math.abs(rx), 1.0)) * speedAdjustment;
            double backRightPower = (y + x - rx) / (Math.max(Math.abs(y) + Math.abs(x) - Math.abs(rx), 1.0)) * speedAdjustment;


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
