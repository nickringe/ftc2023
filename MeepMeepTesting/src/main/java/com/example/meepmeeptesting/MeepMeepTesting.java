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
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 63.5, 0)) //start pose here
                                .turn(Math.toRadians(270))
                                .forward(26)
                                .strafeRight(10)
                                //.turn(Math.toRadians(40))
                                //.forward(14)
                                .back(12)
                               // .turn(Math.toRadians(-40))
                              //  .forward(32)
                               // .turn(Math.toRadians(90))
                               // .forward(100)
                                .strafeLeft(12)
                               // .strafeRight(24)
                                .forward(38)
                                .turn(Math.toRadians(90))
                                .forward(82)
                                .strafeLeft(24)
                                .strafeRight(24)
                                .forward(12)
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}