package com.mfes.gui;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu {

    Scanner scanner;
    String menuName;
    ArrayList<String> options;
    Menu parent;

    Menu(Scanner scanner, String menuName, ArrayList<String> options){
        this(scanner, menuName, options, null);
    }

    Menu(Scanner scanner, String menuName, ArrayList<String> options, Menu parent){
        this.scanner = scanner;
        this.menuName = menuName;
        this.options = options;
        this.parent = parent;
    }

    void show(){
        this.printMenu();
        int selectedOption = this.getSelectedOption();
        this.selectedOptionTrigger(selectedOption);
    }

    abstract void selectedOptionTrigger(int selectedOption);

    private void printMenu(){

        System.out.println("** " + this.menuName + " **\n");

        for(int i = 1; i <= this.options.size(); i++){
            System.out.println(i + " - " + this.options.get(i-1));
        }

        System.out.print("\nOption to select: ");
    }

    private int getSelectedOption(){

        boolean validInput = false;
        int selectedOption = -1;
        String input;

        while(!validInput){

            input = this.scanner.nextLine();

            try {
                selectedOption = Integer.parseInt(input);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

            if(selectedOption >= 1 && selectedOption <= this.options.size()) {
                validInput = true;
            }
            else {
                printInvalidInputMessage();
            }
        }

        return selectedOption;
    }

    private void printInvalidInputMessage(){
        System.out.print( "Invalid input!!!\n" +
                "Please selected a different option: ");
    }

}
