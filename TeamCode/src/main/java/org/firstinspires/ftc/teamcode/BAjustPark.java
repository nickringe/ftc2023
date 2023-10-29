package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous
public class BAjustPark extends LinearOpMode {
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
        SampleMecanumDrive drive;

        // Initialize a timer
        ElapsedTime timer = new ElapsedTime();

        // Initialize drive variable
        drive = new SampleMecanumDrive(hardwareMap);

        // Create a starting pose
        Pose2d startPose = new Pose2d(-36,63.5,270);
        waitForStart();


        while (opModeIsActive()) {

            //set the timer to 0 when we press play
            timer.reset();

            Trajectory BAJustParkTrajectory = drive.trajectoryBuilder(startPose)
                    .forward(2)
                    .build();
            sleep(500);
            Pose2d pose1 = drive.getPoseEstimate();
            Trajectory RBJustParkTrajectory1 = drive.trajectoryBuilder(pose1)
                    .strafeRight(24)
                    .build();
            sleep(500);
            Pose2d pose2 = drive.getPoseEstimate();
            Trajectory RBJustParkTrajectory2 = drive.trajectoryBuilder(pose2)
                    .forward(48)
                    .build();
            sleep(500);
            Pose2d pose3 = drive.getPoseEstimate();
            Trajectory RBJustParkTrajectory3 = drive.trajectoryBuilder(pose3)
                    .strafeLeft(120)
                    .build();
            sleep(500);
            drive.setMotorPowers(0,0,0,0);

        //make sure robot doesn't move
        while (timer.seconds() < 40) {
                idle();
            }

        }

    }
}