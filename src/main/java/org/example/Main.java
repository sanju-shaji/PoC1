package org.example;

import java.io.File;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

    Scanner input= new Scanner(System.in);
        System.out.println("Enter The File Path :");
        String filePath= input.next();

        File f=new File(filePath);
        if(!(f.exists()) && !(f.isDirectory())) {
            System.out.println("Folder Not Found");
        }

        else{
            System.out.println("Processing");
        }
   }
}