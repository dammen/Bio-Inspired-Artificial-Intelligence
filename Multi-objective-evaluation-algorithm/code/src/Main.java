package src;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //Evaluator evaluator = new Evaluator();
        //evaluator.evaluate();

        ImageHandler ih = new ImageHandler();
        ih.loadImage("/Users/jonasdammen/Development/code/src/github.com/Bio-Inspired-Artificial-Intelligence/Multi-objective-evaluation-algorithm/Test Images Project 2/86016/Test image.jpg");
        HashMap<Point, Pixel> pixels = ih.pixels;
        Graph graph = createGraph(pixels, ih);
        //ih.showImage();
        printImageValues(ih, true);
    }

    private static Graph createGraph(HashMap<Point, Pixel> pixels, ImageHandler ih){
        Color[][] img = ih.imageAsRGB;

        Graph graph = new Graph();

        for(int row = 0; row < img.length; row++){
            Color[] curColumn = img[row];
            for(int col = 0; col < curColumn.length; col++){
                // TODO: Add vertices and edges to the graph
            }
        }
        return null;
    }

    public static int[][] getEuclidianDistance(int[][][] image){
        return null;
    }


    private static void printImageValues(ImageHandler ih, boolean rgb){
        if(rgb){
            Color[][] img = ih.imageAsRGB;
            for (Color[] line : img) {
                for (Color val: line){
                    System.out.println(
                            val.getBlue() + "," +
                                    val.getRed() +"," +
                                    val.getGreen()
                    );

                }
            }
        } else {
            int[][][] img = ih.imageAsInteger;
            for (int[][] matrix : img) {
                for (int[] array : matrix) {
                    for (int val : array) {
                        System.out.print(val + ",");
                    }
                    System.out.println("");

                }
            }
        }
    }

    public static void printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
}
