package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 12)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -63.5, 0)) //start pose here
                                //****************RB BEST -Left- ********************//

                               // .turn(Math.toRadians(90))
                                //.forward(17)
                               // .turn(Math.toRadians(38))
//                                .forward(6)
//                                .back(6)
//                                .turn(Math.toRadians(-128))
//                                .forward(34)
//                                .strafeLeft(17)
//                                .strafeRight(32)
//                                .forward(12)

                                //****************RB BEST -Right- ********************//
                         //       .turn(Math.toRadians(90))
                               // .forward(24.5)
                              //  .turn(Math.toRadians(-60))
                              //  .forward(3)
                                //.back(5)
                               // .turn(Math.toRadians(60))
                               // .strafeLeft(2)
                               // .forward(30)
//                                .strafeRight(17)
//                                .turn(Math.toRadians(-90))
                                //.forward(20)
                                //.strafeRight(35)
                               // .forward(3)
                                //.back(5)
//                                .strafeRight(12)
//                                .forward(15)

                                //****************RB BEST -Center- ********************//
//                                .turn(Math.toRadians(90))
//                                .forward(20)
//                                .strafeRight(8)
//                                .turn(Math.toRadians(10))
//                                .forward(8)
//                                .back(8)
//                                .turn(Math.toRadians(-100))
//                                .strafeLeft(8)
//                                .forward(28)
//                                .strafeRight(24)
//                                .forward(10)

                                //****************RA BEST -Left- ********************//
//                                .turn(Math.toRadians(90))
//                                .forward(20)
//                                .turn(Math.toRadians(45))
//                                .forward(4)
//                                .back(5)
//                                .turn(Math.toRadians(-45))
//                                .forward(32)
//                                .turn(Math.toRadians(-90))
//                                .forward(84)
//                                .strafeRight(18)
//                                .strafeLeft(20)
//                                .forward(14)

                                //****************RA BEST -Right- ********************//
//                                .turn(Math.toRadians(90))
//                                .forward(22)
//                                .turn(Math.toRadians(-60))
//                                .forward(3)
//                                .back(6)
//                                .turn(Math.toRadians(60))
//                                .forward(38)
//                                .turn(Math.toRadians(-90))
//                                .forward(82)
//                                .strafeRight(39)
//                                .forward(5)
//                                .back(5)
//                                .strafeLeft(30)
//                                .forward(20)

                                //****************RA BEST -Center- ********************//
//                                .turn(Math.toRadians(90))
//                                .forward(20)
//                                .strafeLeft(20)
//                                .turn(Math.toRadians(-40))
//                                .forward(14)
//                                .back(12)
//                                .turn(Math.toRadians(40))
//                                .forward(32)
//                                .turn(Math.toRadians(-90))
//                                .forward(100)
//                                .strafeRight(24)
//                                .strafeLeft(24)
//                                .forward(14)

                                //****************BB BEST -Right- ********************//
//                                .turn(Math.toRadians(270))
//                                .forward(24.5)
//                                .turn(Math.toRadians(-60))
//                                .back(5)
//                                .turn(Math.toRadians(150))
//                                .strafeRight(13)
//                                .forward(28)
//                                .strafeRight(17)
//                                .forward(12)
//                  //****************BB BEST -Center- ********************//
//                                .turn(Math.toRadians(270))
//                                .forward(20)
//                                .strafeLeft(8)
//                                .turn(Math.toRadians(-10))
//                                .forward(8)
//                                .back(8)
//                                .turn(Math.toRadians(100))
//                                .strafeRight(8)
//                                .forward(28)
//                                .strafeRight(24)
//                                .forward(10)
                    //****************BB BEST -Left- ********************//
//                                .turn(Math.toRadians(270))
//                                .forward(14)
//                                .turn(Math.toRadians(38))
//                                .forward(6)
//                                .back(6)
//                                .turn(Math.toRadians(50))
//                                .forward(34)
//                                .strafeRight(6)
//                                .strafeLeft(18)
//                                .forward(12)

                    //****************BA BEST -Right- ********************//
//                                .turn(Math.toRadians(270))
//                                .forward(26)
//                                .strafeRight(10)
//                                .back(12)
//                                .strafeLeft(12)
//                                .forward(38)
//                                .turn(Math.toRadians(90))
//                                .forward(82)
//                                .strafeLeft(24)
//                                .strafeLeft(24)
//                                .forward(12)

                    //****************BA BEST -Center- ********************//
//                                .turn(Math.toRadians(270))
//                                .forward(20)
//                                .strafeRight(20)
//                                .turn(Math.toRadians(40))
//                                .forward(14)
//                                .back(12)
//                                .turn(Math.toRadians(-40))
//                                .forward(32)
//                                .turn(Math.toRadians(90))
//                                .forward(100)
//                                .strafeLeft(24)
//                                .strafeRight(24)
//                                .forward(14)

                     //****************BA BEST -Left- ********************//
//                                .turn(Math.toRadians(270))
//                                .forward(20)
//                                .turn(Math.toRadians(45))
//                                .forward(6)
//                                .back(6)
//                                .turn(Math.toRadians(-45))
//                                .strafeRight(18)
//                                .forward(32)
//                                .turn(Math.toRadians(90))
//                                .forward(100)
//                                .strafeLeft(30)
//                                .strafeRight(30)
//                                .forward(14)

                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}