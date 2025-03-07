package org.example;

import java.io.File;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter The File Path :");
        String filePath = input.nextLine();

        File f = new File(filePath);


        if (!(f.exists())) {
            System.out.println("Folder does not exists");
        } else if ((f.list()).length == 0) {
            System.out.println("Empty Folder");
        } else {
            System.out.println("processing");
        }

    }
}