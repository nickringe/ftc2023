package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
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
public class BAbestCase extends LinearOpMode {
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
        telemetry.addData("String selectedRectangle", selectedRectangle);
        telemetry.update();

        //this tells the program where we are starting on the field
        //and where which direction we are facing
        Pose2d startingPose = new Pose2d(-36, 63.5, Math.toRadians(270));

        waitForStart();


        while (opModeIsActive()) {

            if (selectedRectangle == "left") {
                //make a trajectory that goes to the left spike mark
                drive.followTrajectory(drive.trajectoryBuilder(startingPose)
                        .forward(12)
                        .build());
                sleep(500);
                drive.turn(Math.toRadians(45));
                Pose2d leftPose2 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(leftPose2)
                        .forward(6)
                        .build());
                sleep(500);
                Pose2d leftPose3 = drive.getPoseEstimate();
                //spin the intake motors backwards very gently to release pixel
                intakeMotorLeft.setPower(0.2);
                intakeMotorRight.setPower(-0.2);
                sleep(250);

                //make a trajectory that goes to the left side of the pixel board
                drive.followTrajectory(drive.trajectoryBuilder(leftPose3)
                        .back(6)
                        .build());
                drive.turn(Math.toRadians(-45));
                Pose2d leftPose4 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(leftPose4)
                        .strafeRight(18)
                        .build());
                sleep(250);
                Pose2d leftPose5 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(leftPose5)
                        .forward(32)
                        .build());
                drive.turn(Math.toRadians(90));
                sleep(250);
                Pose2d leftPose6 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(leftPose6)
                        .forward(100)
                        .strafeLeft(30)
                        .build());
                //motor and servo logic to drop pixel on board
                    //TODO motor and servo add logic here

                //strafe to the side and park
                Pose2d leftPose7 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(leftPose7)
                        .strafeRight(30)
                        .forward(14)
                        .build());
            } else if (selectedRectangle == "center") {
                //make a trajectory that goes to the center spike mark
                drive.followTrajectory(drive.trajectoryBuilder(startingPose)
                        .forward(20)
                        .strafeRight(18)
                        .build());

                sleep(250);
                drive.turn(Math.toRadians(40));
                        //.forward(20)
                        //.strafeRight(18)
                       // .turn(Math.toRadians(40))
                        .forward(14)
                        .back(12)
                        .turn(Math.toRadians(-40))
                        .forward(32)
                        .turn(Math.toRadians(90))
                        .forward(100)
                        .strafeLeft(24)
                        .strafeRight(24)
                        .forward(14)
                        .build();
                //spin the intake motors backwards very gently to release pixel
                //make a trajectory that goes to the center of the pixel board
                //strafe to the side and park
            } else {
                //if selectedRectangle isn't left or center, it's "right"
                //make a trajectory that goes to the right spike mark
                drive.followTrajectory(drive.trajectoryBuilder(startingPose)
                        .forward(26)
                        .build());
                sleep(500);
                Pose2d rightPose2 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose2)
                        .strafeRight(10)
                        .build());
                sleep(500);
                //TODO  spin the intake motors backwards very gently to release pixel
                Pose2d rightPose3 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose3)
                        .back(12)
                        .build());
                sleep(500);
                Pose2d rightPose4 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose4)
                        .strafeLeft(12)
                        .build());
                sleep(500);
                Pose2d rightPose5 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose5)
                        .forward(38)
                        .build());
                sleep(500);
                drive.turn(Math.toRadians(90));
                Pose2d rightPose6 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose6)
                        .forward(82)
                        .build());
                sleep(500);
                Pose2d rightPose7 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose7)
                        .strafeLeft(24)
                        .build());
                sleep(500);
                Pose2d rightPose8 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose8)
                        .strafeLeft(24)
                        .build());
                sleep(500);
                Pose2d rightPose9 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose9)
                        .forward(12)
                        .build());
                sleep(500);


                //make a trajectory that goes to the right side of the pixel board
                //strafe to the side and park
            }

            //Stop the motors
            drive.setMotorPowers(0,0,0,0);


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