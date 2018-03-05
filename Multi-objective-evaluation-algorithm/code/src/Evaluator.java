package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Evaluator {
    public void evaluate(){
        Runtime rt = Runtime.getRuntime();
        //String[] commands = {"system.exe","-get t"};
        String[] env = {};
        Process proc;
        File runDir = new File("/Users/jonasdammen/Development/code/src/github.com/Bio-Inspired-Artificial-Intelligence/Multi-objective-evaluation-algorithm/Segmentation_Evaluation/");
        try {
            proc = rt.exec("/usr/local/bin/python3 run.py", env, runDir);
        } catch (IOException e1) {
            System.out.println("Error executing command:" + e1.getLocalizedMessage());
            return;
        }

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(proc.getErrorStream()));

        // read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        String fullOutputstring = null;
        String s = null;
        try {
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
                fullOutputstring = fullOutputstring + s;
            }
        } catch (IOException e) {
            System.out.println("Unable to output the results due to error:" + e.getLocalizedMessage());
            return;
        }

        // read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        try {
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            System.out.println("Unable to output the errors due to error:" + e.getLocalizedMessage());
        }
        System.out.println(fullOutputstring);
    }
}
