package org.example;

import java.io.File;

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
                System.out.println(extensionchecker);
                return;
            }
        }
        System.out.println(processing);
    }
}
