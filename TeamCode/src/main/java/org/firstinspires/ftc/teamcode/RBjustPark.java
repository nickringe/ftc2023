package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous
public class RBjustPark extends LinearOpMode {
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

        // Initialize drive variable
        drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(12,-63.5,90);
        waitForStart();


        while (opModeIsActive()) {

            Trajectory RBJustParkTrajectory = drive.trajectoryBuilder(startPose)
                    .forward(5)
                    .strafeRight(55.5)
                    .build();

            drive.followTrajectory(RBJustParkTrajectory);
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