package com.mfes.gui;

import java.util.ArrayList;
import java.util.Scanner;

public class CLI {

    private Scanner scanner;

    CLI() {
        scanner = new Scanner(System.in);
    }

    public void run(){
        showInitialMenu();
    }

    private void showAppBanner(){
        System.out.println("\n" +
                "*******************************\n" +
                "*                             *\n" +
                "*        IRON TRAINERS        *\n" +
                "*                             *\n" +
                "*******************************\n");
    }

    private void showInitialMenu(){
        showAppBanner();

        ArrayList<String> options = new ArrayList<>();
        options.add("Log In");
        options.add("Register");

        Menu loginMenu = new Menu(this.scanner, "Login / Register", options) {
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

        loginMenu.show();
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

        /* After filling the form */
        email = emailField.getInput();
        password = passwordField.getInput();
        weight = Float.parseFloat(weightField.getInput());

        System.out.println("MyEmail: " + email);
        System.out.println("MyPassword: " + password);
        System.out.println("MyWeight: " + weight);
    }
}
