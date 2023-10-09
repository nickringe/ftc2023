package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.vision.RedDetectionPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name = "RedDetectionAutonomous", group = "Autonomous")
public class RedDetectionAutonomous extends LinearOpMode {

    @Override
    public void runOpMode() {
        // Initialize hardware, motors, servos etc
        //this example shows how to put all of our hardware into a separate class
        //and call it. This cleans up our code and makes it easier to maintain
        RobotHardware myHardware;
        myHardware = new RobotHardware(hardwareMap);

        //making the webcam object -
        OpenCvInternalCamera webcam;

        // Initialize the RedDetectionPipeline with telemetry
        RedDetectionPipeline redPipeline = new RedDetectionPipeline(telemetry);

        // Configure the camera - this line can go in the RobotHardware class
        // This goes away if we're using the RobotHardware class
        webcam = hardwareMap.get(OpenCvInternalCamera.class, "yourWebcamName");

        //If this was in the RobotHardware class, we would call it a little differently
        webcam.setPipeline(redPipeline);
        // myHardware.getWebcam().setPipeline(redPipeline);


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
            }});



        waitForStart();


        while (opModeIsActive()) {


            // Access the selected rectangle using redPipeline.getSelectedRectangle()
            // Implement your autonomous logic based on the selected rectangle
            String selectedRectangle = redPipeline.getSelectedRectangle();
            telemetry.addData("Selected Rectangle", redPipeline.getSelectedRectangle());
            telemetry.update();
        }


    }
}
