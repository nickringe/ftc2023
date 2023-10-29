package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.vision.RedDetectionPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous
public class RBbestCase extends LinearOpMode {
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

        // Initialize the RedDetectionPipeline
        RedDetectionPipeline redPipeline = new RedDetectionPipeline(telemetry);
        webcam.setPipeline(redPipeline);
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
            }
        });


        // Access the selected rectangle using redPipeline.getSelectedRectangle()
        String selectedRectangle = redPipeline.getSelectedRectangle();
        telemetry.addData("Selected Rectangle", redPipeline.getSelectedRectangle());
        telemetry.addData("String selectedRectangle", selectedRectangle);
        telemetry.update();
        waitForStart();

        Pose2d startingPose= new Pose2d(12, -63.5, Math.toRadians(90));
        while (opModeIsActive()) {

            if (selectedRectangle == "left") {
                //make a trajectory that goes to the left spike mark
                drive.followTrajectory(drive.trajectoryBuilder(startingPose)
                        .forward(17)
                        .build());
                sleep(500);

              drive.turn(Math.toRadians(38));
                sleep(500);

                Pose2d leftPose2 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(leftPose2)
                        .forward(6)
                        .build());
                sleep(500);

                //spin the intake motors backwards very gently to release pixel
                intakeMotorLeft.setPower(0.2);
                intakeMotorRight.setPower(-0.2);
                sleep(500);
                intakeMotorLeft.setPower(0);
                intakeMotorRight.setPower(0);

                //make a trajectory that goes to the left side of the pixel board
                Pose2d leftPose3 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(leftPose3)
                        .back(6)
                        .build());
                sleep(500);

                drive.turn(Math.toRadians(-128));
                sleep(500);

                Pose2d leftPose4 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(leftPose4)
                        .forward(34)
                        .build());
                sleep(500);

                Pose2d leftPose5 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(leftPose5)
                        .strafeLeft(17)
                        .build());
                sleep(500);

                //TODO place the pixel on the board

                //strafe to the side and park
                Pose2d leftPose6 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(leftPose6)
                        .strafeRight(32)
                        .build());
                sleep(500);

                Pose2d leftPose7 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(leftPose7)
                        .forward(12)
                        .build());
                sleep(500);


            } else if (selectedRectangle == "center") {
                //make a trajectory that goes to the center spike mark
                drive.followTrajectory(drive.trajectoryBuilder(startingPose)
                        .forward(20)
                        .build());
                sleep(250);

                Pose2d rightPose2=drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose2)
                        .strafeRight(8)
                        .build());
                sleep(500);

                drive.turn(Math.toRadians(10));
                sleep(500);

                Pose2d rightPose3=drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose3)
                        .forward(8)
                        .build());
                sleep(500);

                //spin the intake motors backwards very gently to release pixel
                intakeMotorLeft.setPower(0.2);
                intakeMotorRight.setPower(-0.2);
                sleep(500);
                intakeMotorLeft.setPower(0);
                intakeMotorRight.setPower(0);

                //make a trajectory that goes to the center of the pixel board
                Pose2d rightPose4=drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose4)
                        .back(8)
                        .build());
                sleep(500);

                drive.turn(Math.toRadians(-100));
                sleep(500);

                Pose2d rightPose5 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose5)
                        .strafeLeft(8)
                        .build());
                sleep(500);

                Pose2d rightPose6=drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose6)
                        .forward(28)
                        .build());
                sleep(500);

                //TODO place the pixel on the board

                //strafe to the side and park
                Pose2d rightPose7 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose7)
                        .strafeRight(24)
                        .build());
                sleep(500);

                Pose2d rightPose9 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose9)
                        .forward(10)
                        .build());
                sleep(500);

            } else {
                //if selectedRectangle isn't left or center, it's "right"
                //make a trajectory that goes to the right spike mark
                drive.followTrajectory(drive.trajectoryBuilder(startingPose)
                        .forward(24.5)
                        .build());
                sleep(250);
                drive.turn(Math.toRadians(-60));
                sleep(500);
                Pose2d rightPose2=drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose2)
                        .forward(3)
                        .build());
                sleep(500);

                Pose2d rightPose3=drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose3)
                        .back(5)
                        .build());
                sleep(500);

                drive .turn(Math.toRadians(60));
                sleep(500);


                //spin the intake motors backwards very gently to release pixel
                intakeMotorLeft.setPower(0.2);
                intakeMotorRight.setPower(-0.2);
                sleep(500);
                intakeMotorLeft.setPower(0);
                intakeMotorRight.setPower(0);

                //make a trajectory that goes to the center of the pixel board
                Pose2d rightPose4=drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose4)
                        .strafeLeft(2)
                        .build());
                sleep(500);

                Pose2d rightPose5 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose5)
                        .forward(30)
                        .build());
                sleep(500);

                Pose2d rightPose6=drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose6)
                        .strafeRight(17)
                        .build());
                sleep(500);

                //TODO place the pixel on the board
                drive.turn(Math.toRadians(-90));
                sleep(500);
                //strafe to the side and park
                Pose2d rightPose7 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose7)
                        .forward(20)
                        .build());
                sleep(500);

                Pose2d rightPose9 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose9)
                        .strafeRight(35)
                        .build());
                sleep(500);

            //Stop the motors
            drive.setMotorPowers(0,0,0,0);

        }

    }
        }

    }
