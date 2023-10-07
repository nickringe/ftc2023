package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

@TeleOp(name = "RedConcentrationScanner", group = "TeleOp")
public class RedConcentrationScanner extends LinearOpMode {

    private OpenCvCamera webcam;
    private RedConcentrationPipeline redConcentrationPipeline;

    // Define regions of interest (ROIs) as rectangles
    private Rect[] rois = {
            new Rect(new Point(50, 50), new Size(100, 100)),
            new Rect(new Point(200, 50), new Size(100, 100)),
            new Rect(new Point(350, 50), new Size(100, 100))
    };

    @Override
    public void runOpMode() {
        initializeHardware();

        // Configure the camera
        configureCamera();

        // Wait for the start button to be pressed
        waitForStart();

        // Main loop
        while (opModeIsActive()) {
            // Your robot logic here
        }

        // Release resources
        webcam.closeCameraDevice();
    }

    private void initializeHardware() {
        // Set up the camera and pipeline
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "webcam"), cameraMonitorViewId);
        redConcentrationPipeline = new RedConcentrationPipeline();
        redConcentrationPipeline.setRegionsOfInterest(rois); // Set the ROIs in the pipeline
        webcam.setPipeline(redConcentrationPipeline);
    }

    private void configureCamera() {
        // Set camera settings (adjust as needed)
        webcam.openCameraDevice();
        webcam.setPipeline(redConcentrationPipeline);
        webcam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
    }

    // Other methods for robot control can be added here

    // Define your custom OpenCvPipeline
    class RedConcentrationPipeline extends OpenCvPipeline {
        private Rect[] regionsOfInterest; // Added variable to store ROIs

        public void setRegionsOfInterest(Rect[] rois) {
            regionsOfInterest = rois;
        }

        @Override
        public Mat processFrame(Mat input) {
            // Your image processing code goes here
            // You can access the webcam feed through the 'input' Mat object

            // For example, you can draw rectangles on the frame
            for (Rect roi : regionsOfInterest) {
                Imgproc.rectangle(input, roi.tl(), roi.br(), new Scalar(0, 255, 0), 2);
            }

            // Return the processed frame
            return input;
        }
    }
}