package utils;

import java.io.FileWriter;
import java.io.IOException;

public class ConvergenceGraph {

    public static void generate(
            double[] fitnessHistory,
            int generationsUsed) {

        int width = 900;
        int height = 500;

        int left = 80;
        int right = 40;
        int top = 50;
        int bottom = 70;

        int graphWidth = width - left - right;

        int graphHeight = height - top - bottom;

        double maxFitness = 0.0;

        for (int i = 0; i <= generationsUsed; i++) {

            if (fitnessHistory[i] > maxFitness) {
                maxFitness = fitnessHistory[i];
            }
        }

        if (maxFitness == 0) {
            maxFitness = 1.0;
        }

        StringBuilder svg = new StringBuilder();

        svg.append(
                "<svg xmlns=\"http://www.w3.org/2000/svg\" "
                        + "width=\"" + width + "\" "
                        + "height=\"" + height + "\">\n");

        // Background
        svg.append(
                "<rect width=\"100%\" height=\"100%\" "
                        + "fill=\"white\"/>\n");

        // Title
        svg.append(
                "<text x=\"450\" y=\"30\" "
                        + "text-anchor=\"middle\" "
                        + "font-size=\"20\" "
                        + "font-family=\"Arial\">"
                        + "Genetic Algorithm Convergence"
                        + "</text>\n");

        // Y axis
        svg.append(
                "<line x1=\"" + left
                        + "\" y1=\"" + top
                        + "\" x2=\"" + left
                        + "\" y2=\"" + (height - bottom)
                        + "\" stroke=\"black\"/>\n");

        // X axis
        svg.append(
                "<line x1=\"" + left
                        + "\" y1=\"" + (height - bottom)
                        + "\" x2=\"" + (width - right)
                        + "\" y2=\"" + (height - bottom)
                        + "\" stroke=\"black\"/>\n");

        // X-axis label
        svg.append(
                "<text x=\"450\" y=\"490\" "
                        + "text-anchor=\"middle\" "
                        + "font-size=\"15\" "
                        + "font-family=\"Arial\">"
                        + "Generation"
                        + "</text>\n");

        // Y-axis label
        svg.append(
                "<text x=\"20\" y=\"275\" "
                        + "text-anchor=\"middle\" "
                        + "font-size=\"15\" "
                        + "font-family=\"Arial\" "
                        + "transform=\"rotate(-90 20 275)\">"
                        + "Best Fitness"
                        + "</text>\n");

        // Grid lines
        for (int i = 0; i <= 5; i++) {

            double value = maxFitness * i / 5.0;

            int y = height
                    - bottom
                    - (int) (graphHeight
                            * i
                            / 5.0);

            svg.append(
                    "<line x1=\"" + left
                            + "\" y1=\"" + y
                            + "\" x2=\"" + (width - right)
                            + "\" y2=\"" + y
                            + "\" "
                            + "stroke=\"lightgray\"/>\n");

            svg.append(
                    "<text x=\"" + (left - 10)
                            + "\" y=\"" + (y + 5)
                            + "\" text-anchor=\"end\" "
                            + "font-size=\"12\" "
                            + "font-family=\"Arial\">"
                            + String.format("%.4f", value)
                            + "</text>\n");
        }

        // X-axis labels
        int numberOfLabels = 5;

        for (int i = 0; i <= numberOfLabels; i++) {

            int generation = generationsUsed * i
                    / numberOfLabels;

            int x = left
                    + (int) (graphWidth
                            * i
                            / (double) numberOfLabels);

            svg.append(
                    "<text x=\"" + x
                            + "\" y=\"" + (height - bottom + 25)
                            + "\" text-anchor=\"middle\" "
                            + "font-size=\"12\" "
                            + "font-family=\"Arial\">"
                            + generation
                            + "</text>\n");
        }

        // Fitness curve
        StringBuilder points = new StringBuilder();

        for (int i = 0; i <= generationsUsed; i++) {

            int x;

            if (generationsUsed == 0) {

                x = left;

            } else {

                x = left
                        + (int) (graphWidth
                                * i
                                / (double) generationsUsed);
            }

            int y = height
                    - bottom
                    - (int) (graphHeight
                            * fitnessHistory[i]
                            / maxFitness);

            points.append(x)
                    .append(",")
                    .append(y)
                    .append(" ");

            // Point
            svg.append(
                    "<circle cx=\"" + x
                            + "\" cy=\"" + y
                            + "\" r=\"2\" "
                            + "fill=\"black\"/>\n");
        }

        svg.append(
                "<polyline points=\""
                        + points
                        + "\" "
                        + "fill=\"none\" "
                        + "stroke=\"black\" "
                        + "stroke-width=\"2\"/>\n");

        svg.append("</svg>");

        try {

            FileWriter writer = new FileWriter(
                    "convergence_graph.svg");

            writer.write(
                    svg.toString());

            writer.close();

            System.out.println();
            System.out.println(
                    "Convergence graph generated:");

            System.out.println(
                    "convergence_graph.svg");

        } catch (IOException e) {

            System.out.println(
                    "Error generating convergence graph.");

            e.printStackTrace();
        }
    }
}