package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotHardware {
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    // ... other hardware components

    public RobotHardware(HardwareMap hardwareMap) {
        leftMotor = hardwareMap.get(DcMotor.class, "left_motor");
        rightMotor = hardwareMap.get(DcMotor.class, "right_motor");

        // ... initialize other hardware components
    }

    // Getter methods for accessing hardware components

    public DcMotor getLeftMotor() {
        return leftMotor;
    }

    public DcMotor getRightMotor() {
        return rightMotor;
    }

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