package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class ExtendArmWithPID extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor extendArm; // Replace with your motor instance
        double kP = 0.1; // Proportional constant (adjust based on testing)
        double kI = 0.01; // Integral constant (adjust based on testing)
        double kD = 0.001; // Derivative constant (adjust based on testing)

        double targetPosition = 0;
        double currentPosition;
        double previousError = 0;
        double integral = 0;

        // Initialize and configure your motor
        extendArm = hardwareMap.get(DcMotor.class, "extendArm");
        extendArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extendArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            // Control the extendArm motor based on button presses (assuming gamepad2)
            if (gamepad2.right_bumper) {
                // Extending the arm upwards

                // Set the target position to extend the arm upwards
                targetPosition = 0.0/* your desired target position for extending upwards */;
            } else if (gamepad2.left_bumper) {
                // Retracting the arm downwards

                // Set the target position to retract the arm downwards
                targetPosition = 1000.0/* your desired target position for retracting downwards */;
            }

            // Get current position of the arm
            currentPosition = extendArm.getCurrentPosition();

            // Calculate error
            double error = targetPosition - currentPosition;

            // Proportional term
            double proportional = kP * error;

            // Integral term
            integral += kI * error;

            // Derivative term
            double derivative = kD * (error - previousError);

            // Calculate the output (force) to be applied to the motor
            double output = proportional + integral + derivative;

            // Apply the output to the motor
            extendArm.setPower(output);

            // Update previous error for the next iteration
            previousError = error;
        }

    }
}
