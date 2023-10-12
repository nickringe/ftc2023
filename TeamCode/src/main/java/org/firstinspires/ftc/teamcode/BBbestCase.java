package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.vision.BlueDetectionPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous
public class BBbestCase extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize hardware, motors, servos etc
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        DcMotor extendArm = hardwareMap.dcMotor.get("extendArm");
        DcMotor swivelArm = hardwareMap.dcMotor.get(("swivelArm"));
        DcMotor intakeMotorLeft = hardwareMap.dcMotor.get("intakeMotorLeft");
        DcMotor intakeMotorRight = hardwareMap.dcMotor.get("intakeMotorRight");
        Servo airplaneServo = hardwareMap.servo.get("airplaneServo");
        OpenCvInternalCamera webcam = hardwareMap.get(OpenCvInternalCamera.class, "webcam");
        SampleMecanumDrive drive;

        // Initialize drive variable
        drive = new SampleMecanumDrive(hardwareMap);

        // Initialize the BlueDetectionPipeline
        BlueDetectionPipeline bluePipeline = new BlueDetectionPipeline(telemetry);
        webcam.setPipeline(bluePipeline);
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                // Configure camera settings if needed
                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                // Handle the error based on the errorCode
                telemetry.addData("Error", "Camera failed to open with error code: " + errorCode);
                telemetry.update();
                System.out.println("Webcam error code: " + errorCode);
            }
        });


        // Access the selected rectangle using redPipeline.getSelectedRectangle()
        String selectedRectangle = bluePipeline.getSelectedRectangle();
        telemetry.addData("Selected Rectangle", bluePipeline.getSelectedRectangle());
        telemetry.update();
        waitForStart();


        while (opModeIsActive()) {


    //^^^^^^^^^^^^^^^^^^PUT YOUR CODE ABOVE THIS LINE^^^^^^^^^^^^^^^//

            /*
           OPTION A - Write single commands at a time

          //Move Forward 12 inches
            drive.followTrajectory(drive.trajectoryBuilder()
                    .forward(12)
                    .build());

          //Move backward 6 inches
            drive.followTrajectory(drive.trajectoryBuilder()
                    .back(6)
                    .build());

          //Turn left 135 degrees
            drive.followTrajectory(drive.trajectoryBuilder()
                    .turn(Math.toRadians(-135))
                    .build());

          //Strafe Left 6 inches
            drive.followTrajectory(drive.trajectoryBuilder()
                    .strafeLeft(6)
                    .build());

          //Stop the robot
            drive.setMotorPowers(0, 0, 0, 0);



          OPTION B - Create 'Tracjectory' objects to chain movements together

          //First you create a Trajectory object and give it a name, like this:
            Trajectory raJustParkTrajectory = drive.trajectoryBuilder()
                    .forward(12)
                    .back(6)
                    .turn(Math.toRadians(-135))
                    .strafeLeft(6)
                    .setMotorPowers(0,0,0,0)
                    .build();

         //Now that it's created, you have to actually tell the program to run it:
         drive.followTrajectory(raJustPark);

         //Each separate Trajectory needs to be created first
         //Then you call each one in the order you want the robot to do them in

                drive.followTrajectory(goToSpikeAndDropPixel);
                drive.followTrajectory(moveToScoreBoard);
                drive.followTrajectory(placePixelOnScoreBoard);
                drive.followTrajectory(strafeToTheSideAndPark);

            */
        }

    }
}