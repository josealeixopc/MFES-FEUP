package com.mfes.gui;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu {

    static String BACK_KEY = "b";
    static String QUIT_KEY = "q";

    private Scanner scanner;
    private String menuName;
    private ArrayList<String> options;
    private Menu parent;

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
        this.getAndProcessInput();
    }

    private void printMenu(){

        System.out.println("** " + this.menuName + " **\n");

        for(int i = 1; i <= this.options.size(); i++){
            System.out.println(i + " - " + this.options.get(i-1));
        }

        System.out.println("\nChoose 'q' to quit or 'b' to go back");
        System.out.print("Option to select: ");
    }


    private void getAndProcessInput(){

        String input;
        int selectedOption;

        while(true) {

            input = this.scanner.nextLine();

            if (input.equalsIgnoreCase(BACK_KEY)) {
                if(parent != null){
                    parent.show();
                    break;
                }
                else{
                    printNoParentMessage();
                    printInvalidInputMessage();
                    continue;
                }

            }

            if (input.equalsIgnoreCase(QUIT_KEY)) {
                System.exit(0);
                break;
            }

            try {
                selectedOption = Integer.parseInt(input);
                if(selectedOption >= 1 && selectedOption <= this.options.size()) {
                    this.selectedOptionTrigger(selectedOption);
                    break;
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                printInvalidInputMessage();
            }



        }

    }

    abstract void selectedOptionTrigger(int selectedOption);

    private void printInvalidInputMessage(){
        System.out.print( "Invalid input!!!\n" +
                "Please selected a different option: ");
    }

    private void printNoParentMessage(){
        System.out.println("This menu has no parent.");
    }

}
