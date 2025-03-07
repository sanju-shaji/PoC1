package org.example;

import java.io.File;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter The File Path :");
        String userfilePath = input.nextLine();

        File userFolder = new File(userfilePath);
        String [] folderContents=userFolder.list();



        if (!(userFolder.exists())) {
            System.out.println("Folder does not exists");
        } else if (folderContents.length == 0) {
                System.out.println("Folder is Empty");
            }
        else {
            System.out.println("Processing...");
        }

        }

    }
