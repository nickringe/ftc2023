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
public class RAbestCase extends LinearOpMode {
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
                System.out.println("Webcam error code: " + errorCode);
            }
        });


        // Access the selected rectangle using redPipeline.getSelectedRectangle()
        String selectedRectangle = redPipeline.getSelectedRectangle();
        telemetry.addData("Selected Rectangle", redPipeline.getSelectedRectangle());
        telemetry.addData("String selectedRectangle", selectedRectangle);
        telemetry.update();
        waitForStart();

Pose2d startingPose = new Pose2d(-36,-63.5,90);
        while (opModeIsActive()) {

            if (selectedRectangle == "left") {
                //make a trajectory that goes to the left spike mark
                drive.followTrajectory(drive.trajectoryBuilder(startingPose)
                        .forward(20)
                        .build());
                sleep(500);

                drive.turn(Math.toRadians(45));
                sleep(500);

                Pose2d leftPose2 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(leftPose2)
                        .forward(4)
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
                        .back(5)
                        .build());
                sleep(500);

                drive.turn(Math.toRadians(-45));
                sleep(500);

                Pose2d leftPose5 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(leftPose5)
                        .forward(32)
                        .build());
                sleep(500);

                drive.turn(Math.toRadians(-90));
                sleep(250);

                Pose2d leftPose6 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(leftPose6)
                        .forward(84)
                        .build());
                sleep(500);

                Pose2d leftPose7 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(leftPose7)
                        .strafeRight(18)
                        .build());
                sleep(500);

                //motor and servo logic to drop pixel on board
                //TODO motor and servo add logic here

                //strafe to the side and park
                Pose2d leftPose8 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(leftPose8)
                        .strafeLeft(20)
                        .build());

                Pose2d leftPose9 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(leftPose9)
                        .forward(14)
                        .build());

            } else if (selectedRectangle == "center") {
                //make a trajectory that goes to the center spike mark
                drive.followTrajectory(drive.trajectoryBuilder(startingPose)
                        .forward(20)
                        .build());
                sleep(500);

                Pose2d rightPose2=drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose2)
                        .strafeLeft(20)
                        .build());
                sleep(500);

                drive.turn(Math.toRadians(-40));
                sleep(500);

                Pose2d rightPose3=drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose3)
                        .forward(14)
                        .build());
                sleep(500);

                //spin the intake motors backwards very gently to release pixel
                intakeMotorLeft.setPower(0.2);
                intakeMotorRight.setPower(-0.2);
                sleep(500);
                intakeMotorLeft.setPower(0);
                intakeMotorRight.setPower(0);
                sleep(500);

                //make a trajectory that goes to the center of the pixel board
                Pose2d rightPose4=drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose4)
                        .back(12)
                        .build());
                sleep(500);

                drive.turn(Math.toRadians(40));
                sleep(500);

                Pose2d rightPose5 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose5)
                        .forward(32)
                        .build());
                sleep(500);

                drive.turn(Math.toRadians(-90));
                sleep(500);

                Pose2d rightPose6=drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose6)
                        .forward(100)
                        .build());
                sleep(500);

                Pose2d rightPose7=drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose7)
                        .strafeRight(24)
                        .build());
                sleep(500);

                //motor and servo logic to drop pixel on board
                //TODO motor and servo add logic here

                //strafe to the side and park
                Pose2d rightPose8=drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose8)
                        .strafeLeft(24)
                        .build());
                sleep(500);

                Pose2d rightPose9=drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose9)
                        .forward(14)
                        .build());
                sleep(500);

            } else {
                //if selectedRectangle isn't left or center, it's "right"
                //make a trajectory that goes to the right spike mark
                drive.followTrajectory(drive.trajectoryBuilder(startingPose)
                        .forward(22)
                        .build());
                sleep(500);

                drive.turn(Math.toRadians(-60));
                sleep(500);

                Pose2d rightPose2 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose2)
                        .forward(3)
                        .build());
                sleep(500);

                //spin the intake motors backwards very gently to release pixel
                intakeMotorLeft.setPower(0.2);
                intakeMotorRight.setPower(-0.2);
                sleep(250);
                intakeMotorLeft.setPower(0);
                intakeMotorRight.setPower(0);
                sleep(500);

                //make a trajectory that goes to the right side of the pixel board
                Pose2d rightPose5 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose5)
                        .back(6)
                        .build());
                sleep(500);

                drive.turn(Math.toRadians(60));
                sleep(500);

                Pose2d rightPose6 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose6)
                        .forward(38)
                        .build());
                sleep(500);

                drive.turn(Math.toRadians(-90));
                sleep(500);

                Pose2d rightPose7 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose7)
                        .forward(82)
                        .build());
                sleep(500);

                Pose2d rightPose8 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose8)
                        .strafeRight(39)
                        .build());
                sleep(500);

                Pose2d rightPose10 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose10)
                        .forward(5)
                        .build());
                sleep(500);

                //motor and servo logic to drop pixel on board
                //TODO motor and servo add logic here

                //strafe to the side and park
                Pose2d rightPose11 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose11)
                        .back(5)
                        .build());
                sleep(500);

                Pose2d rightPose12 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose12)
                        .strafeLeft(30)
                        .build());
                sleep(500);

                Pose2d rightPose13 = drive.getPoseEstimate();
                drive.followTrajectory(drive.trajectoryBuilder(rightPose13)
                        .forward(20)
                        .build());
                sleep(500);
            }

            //Stop the motors
            drive.setMotorPowers(0,0,0,0);

        }

    }
}