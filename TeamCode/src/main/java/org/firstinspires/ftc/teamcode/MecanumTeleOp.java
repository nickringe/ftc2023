package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@TeleOp
public class MecanumTeleOp extends LinearOpMode{

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

                frontLeftMotor.setPower(frontLeftPower);
                backLeftMotor.setPower(backLeftPower);
                frontRightMotor.setPower(frontRightPower);
                backRightMotor.setPower(backRightPower);

                //this code is just an example, I don't remember
                //which buttons we actually chose

                //hook motor extends upwards towards the rigging
                if (gamepad2.right_trigger > 0) {
                    hookMotor.setPower(0.5); //extends the hook upwards
                    sleep(1000); //for 1 second
                    hookMotor.setPower(0); //then stops
                }
                //hook motor comes back down. this is what will pull the robot up
                if (gamepad2.right_bumper) {
                    hookMotor.setDirection(DcMotorSimple.Direction.REVERSE); //set motor to reverse
                    hookMotor.setPower(0.5); //retracts the hook downwards
                    sleep(1000); //for 1 second
                    hookMotor.setPower(0); //then stops
                }
                //servo turns half way to launch the drone, then resets to start
                if (gamepad2.x) {
                    airplaneServo.scaleRange(0,0.5); //only allows servo to go 1/2 way around
                    airplaneServo.setPosition(0.5); //turn servo on, go 1/2 way around
                    airplaneServo.setPosition(0); //reset servo to starting position
                }

            }
        }
    }

