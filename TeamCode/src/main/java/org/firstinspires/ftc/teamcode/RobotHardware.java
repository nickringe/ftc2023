package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.openftc.easyopencv.OpenCvInternalCamera;

public class RobotHardware {
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    public OpenCvInternalCamera webcam;

    // ... other hardware components

    public RobotHardware(HardwareMap hardwareMap) {
        leftMotor = hardwareMap.get(DcMotor.class, "left_motor");
        rightMotor = hardwareMap.get(DcMotor.class, "right_motor");
        webcam = hardwareMap.get(OpenCvInternalCamera .class, "yourWebcamName");
        // ... initialize other hardware components
    }

    // Getter methods for accessing hardware components

    public DcMotor getLeftMotor() {
        return leftMotor;
    }

    public DcMotor getRightMotor() {
        return rightMotor;
    }

    public OpenCvInternalCamera getWebcam() {return webcam; }
    // ... other getter methods for other hardware components

}

//How to use this in our OpModes:
/*
public class YourOpMode extends LinearOpMode {
    private RobotHardware robotHardware;

    @Override
    public void runOpMode() {
        // Instantiate RobotHardware and initialize hardware
        robotHardware = new RobotHardware(hardwareMap);

        // ... other initialization logic

        waitForStart();

        // ... your main OpMode logic
    }
}
*/