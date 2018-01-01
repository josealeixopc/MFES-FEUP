package com.mfes.gui;

import java.util.ArrayList;

public class Form {

    String name;
    ArrayList<Field> fields;

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

    private void printInvalidInputMessage(){
        System.out.print( "Invalid input!!!\n" +
                "Please try a different input.\n");
    }
}
