package com.mfes.gui;

import java.util.ArrayList;
import java.util.Scanner;

public class Form {

    private String name;
    private ArrayList<Field> fields;

    Form(String name, ArrayList<Field> fields){
        this.name = name;
        this.fields = fields;
    }

    public void showForm(){

        System.out.println("** " + name + " **");
        System.out.println();

        for(int i = 0; i < fields.size(); i++){

            Field currentField = this.fields.get(i);
            boolean validInput = false;

            while (!validInput){

                currentField.print();
                currentField.readInput();

                validInput = currentField.canInputBeParsed();

                if(!validInput){
                    this.printInvalidInputMessage();
                }
            }
        }
    }

    public boolean submitForm(Scanner scanner){

        Field submission = new Field(scanner,
                "Are you sure you want to submit this form?\n" +
                "[Y]es or [N]o?") {
            @Override
            protected boolean canInputBeParsed() {
                if(this.input.equalsIgnoreCase("y") || this.input.equalsIgnoreCase("n")){
                    return true;
                }
                else{
                    return false;
                }
            }
        };

        submission.print();
        submission.readInput();

        if(submission.getInput().equalsIgnoreCase("y")){
            return true;
        }
        else{
            return false;
        }
    }

    private void printInvalidInputMessage(){
        System.out.print( "Invalid input for the field!!!\n" +
                "Please try a different input.\n");
    }
}
