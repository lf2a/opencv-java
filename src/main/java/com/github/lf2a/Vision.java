package com.github.lf2a;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;

public class Vision {

    public static boolean debug = false;
    public static String dPath = "";

    public static void enableDebug(String dPath) {
        debug = true;
        Vision.dPath = dPath;
    }

    /** Loading the OpenCV library. */
    static {
        // linux (ubuntu)
        System.load(new File("").getAbsolutePath() + "/libs/libopencv_java420.so");
    }

    public static Position find(String source0, String template0) throws InterruptedException {
        Mat source = Imgcodecs.imread(source0, Imgproc.COLOR_BGR2GRAY);
        Mat template = Imgcodecs.imread(template0, Imgproc.COLOR_BGR2GRAY);

        int machMethod = Imgproc.TM_CCOEFF;

        Mat outputImage = new Mat();
        Imgproc.matchTemplate(source, template, outputImage, machMethod);

        Core.MinMaxLocResult mmr = Core.minMaxLoc(outputImage);
        Point matchLoc = mmr.maxLoc;

        Scalar scalar = new Scalar(0, 255, 0);

        if (debug) {
            Thread.sleep(500);
            // Draw rectangle
            Imgproc.rectangle(
                    source,
                    new Point(matchLoc.x, matchLoc.y),
                    new Point(matchLoc.x + template.width(), matchLoc.y + template.height()),
                    scalar,
                    1
            );

            Thread.sleep(500);
            // Draw line
            Imgproc.line(
                    source,
                    new Point(matchLoc.x + (template.width() / 2), matchLoc.y + (template.height() / 2)),
                    new Point(matchLoc.y + 300, matchLoc.y + 200),
                    scalar,
                    1
            );

            Thread.sleep(500);
            // draw circle
            Imgproc.circle(
                    source,
                    new Point(matchLoc.y + 300, matchLoc.y + 200),
                    1,
                    scalar,
                    2
            );

            Imgcodecs.imwrite(dPath, source);
        }

        return new Position(matchLoc.x + (template.width() / 2), matchLoc.y + (template.height() / 2));
    }
}
