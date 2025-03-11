package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.example.Strings.*;

public class LogAggregatorTool {
    public static void main(String[] args) {

        String userFilePath = args[0];
        File userFolder = new File(userFilePath);
        String[] folderContents = userFolder.list();
        if (!(userFolder.isDirectory())) {
            System.out.println(invalidPath);
            return;
        }
        if (folderContents.length == 0) {
            System.out.println(emptyFolder);
            return;
        }
        for (String files : folderContents) {
            if (!(files.endsWith(".log"))) {
                System.out.println(files + extensionchecker);
            }
        }
        System.out.println(processing);
    }
}
