package com.mfes.gui;

import com.mfes.model.Client;
import com.mfes.model.IronTrainers;
import com.mfes.model.MyUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class CLI {

    private Scanner scanner;
    private IronTrainers ironTrainers;

    // MENUS
    private Menu initialMenu;
    private Menu clientMenu;

    // FIELDS
    private Field emailField;
    private Field passwordField;
    private Field nameField;
    private Field genderField;
    private Field weightField;

    CLI() {
        this.scanner = new Scanner(System.in);
        this.ironTrainers = new IronTrainers();

        createMenus();
        createFields();
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

        this.clientMenu = new Menu(this.scanner, "Client Menu", options, initialMenu) {
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

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(emailField);
        fields.add(passwordField);

        Form loginForm = new Form("Login Form", fields);
        loginForm.showForm();
        boolean submit = loginForm.submitForm(scanner);

        if(!submit){
            initialMenu.show(); // go back to initial menu (parent menu)
        }
        else{
            /* After filling the form */
            email = emailField.getInput();
            password = passwordField.getInput();

            /* Do something with input*/

            clientMenu.show();  // go to next menu
        }
    }

    private void showRegisterFrom(){
        String email;
        String password;
        String name;
        char gender;
        float weight;
        int height;
        MyUtils.Date birthDate;

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(emailField);
        fields.add(passwordField);


        //Client newClient = new Client(email, password, name, gender, weight, height, birthDate);
    }

    private void createFields(){
        emailField = new Field(scanner, "Email") {
            @Override
            protected boolean canInputBeParsed() {
                return true;    // any string is a string
            }
        };

        passwordField = new Field(scanner, "Password") {
            @Override
            protected boolean canInputBeParsed() {
                return true;    // any string is a string
            }
        };

        nameField = new Field(scanner, "Name") {
            @Override
            protected boolean canInputBeParsed() {
                return true;    // any string is a string
            }
        };

        genderField = new Field(scanner, "Gender (M or F)") {
            @Override
            protected boolean canInputBeParsed() {
                if(this.input.equalsIgnoreCase("M") || this.input.equalsIgnoreCase("F")){
                    return true;
                }

                return false;
            }
        };

        weightField = new Field(scanner, "Weight"){

            @Override
            protected boolean canInputBeParsed() {
                try{
                    Float.parseFloat(this.input);
                    return true;
                }catch (Exception e){
                    return false;
                }
            }
        };
    }
}
