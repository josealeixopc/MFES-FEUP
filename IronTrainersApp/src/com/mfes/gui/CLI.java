package com.mfes.gui;

import java.util.ArrayList;
import java.util.Scanner;

public class CLI {

    private Scanner scanner;
    // ADD IRON TRAINERS MEMBER

    // MENUS
    private Menu initialMenu;
    private Menu clientMenu;


    CLI() {
        scanner = new Scanner(System.in);

        createMenus();
    }

    public void run(){
        showAppBanner();

        this.initialMenu.show();
    }

    private void showAppBanner(){
        System.out.println("\n" +
                "*******************************\n" +
                "*                             *\n" +
                "*        IRON TRAINERS        *\n" +
                "*                             *\n" +
                "*******************************\n");
    }

    private void createMenus(){
        createInitialMenu();
        createClientMenu();
    }

    private void createInitialMenu(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Log In");
        options.add("Register");

        this.initialMenu = new Menu(this.scanner, "Login / Register", options) {
            @Override
            void selectedOptionTrigger(int selectedOption) {
                switch (selectedOption) {
                    case 1: showLoginForm(); break;
                    case 2: System.out.println("You don't have an account"); break;
                    default:
                        break;
                }
            }
        };
    }

    private void createClientMenu(){
        ArrayList<String> options = new ArrayList<>();
        options.add("My Training Plan");
        options.add("My Milestones");

        this.clientMenu = new Menu(this.scanner, "Client Menu", options) {
            @Override
            void selectedOptionTrigger(int selectedOption) {
                switch (selectedOption) {
                    default: break;
                }
            }
        };
    }

    private void showLoginForm(){

        String email;
        String password;
        Float weight;

        Field emailField = new Field(this.scanner, "Email") {
            @Override
            protected boolean canInputBeParsed() {
                return true;    // any string is a string
            }
        };

        Field passwordField = new Field(this.scanner, "Password") {
            @Override
            protected boolean canInputBeParsed() {
                return true;    // any string is a string
            }
        };

        Field weightField = new Field(this.scanner, "Weight (in kg)") {
            @Override
            protected boolean canInputBeParsed() {
                try{
                    Float.parseFloat(this.input);   // try to parse into a float. If an exception happens, return false.
                    return true;
                }catch (Exception e){
                    return false;
                }
            }
        };

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(emailField);
        fields.add(passwordField);
        fields.add(weightField);

        Form loginForm = new Form("Login Form", fields);
        loginForm.showForm();
        boolean submit = loginForm.submitForm(scanner);

        if(!submit){
            initialMenu.show(); // go back to initial menu
        }
        else{
            /* After filling the form */
            email = emailField.getInput();
            password = passwordField.getInput();
            weight = Float.parseFloat(weightField.getInput());

            /* Do something with input*/
            System.out.println("MyEmail: " + email);
            System.out.println("MyPassword: " + password);
            System.out.println("MyWeight: " + weight);

            clientMenu.show();  // go to next menu
        }


    }
}
