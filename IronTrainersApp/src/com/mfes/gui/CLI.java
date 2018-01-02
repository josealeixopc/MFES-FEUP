package com.mfes.gui;

import com.mfes.model.Client;
import com.mfes.model.IronTrainers;
import com.mfes.model.MyUtils;
import com.mfes.model.Trainer;
import com.mfes.model.quotes.FQuote;
import com.mfes.model.quotes.MQuote;

import java.util.ArrayList;
import java.util.Scanner;

public class CLI {

    private Scanner scanner;
    private IronTrainers ironTrainers;

    // MENUS
    private Menu initialMenu;
    private Menu clientMenu;
    private Menu trainerMenu;
    private Menu exerciseMenu;

    // FIELDS
    private Field emailField;
    private Field passwordField;
    private Field nameField;
    private Field genderField;
    private Field weightField;
    private Field exerciseNameField;
    private Field exerciseDescriptionField;
    private Field exerciseBodyPartField;


    CLI() {
        this.scanner = new Scanner(System.in);
        this.ironTrainers = new IronTrainers();

        createDummyUsers();
        createMenus();
        createFields();
    }

    public void run(){
        showAppBanner();

        //this.initialMenu.show();

        //teste
        this.clientMenu.show();
    }

    private void createDummyUsers(){

        // Female
        Client c1 = new Client("client1@mfes.com", "mfes", "Client1", FQuote.getInstance(), 60.0, 170, new MyUtils.Date(1,1, 1990));
        Client c2 = new Client("client2@mfes.com", "mfes", "Client2", FQuote.getInstance(), 47.4, 150, new MyUtils.Date(1,12, 1996));

        // Male
        Client c3 = new Client("client3@mfes.com", "mfes", "Client3", MQuote.getInstance(), 80.0, 192, new MyUtils.Date(17,6, 1987));

        // Trainer
        Trainer t1 = new Trainer("trainer1@mfes.com", "mfes", "Trainer1");

        this.ironTrainers.addClient(c1);
        this.ironTrainers.addClient(c2);
        this.ironTrainers.addClient(c3);

        this.ironTrainers.addTrainer(t1);
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
        createTrainerMenu();
        createExerciseMenu();
    }

    private void createInitialMenu(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Log In as a Client");
        options.add("Log In as a Trainer");
        options.add("Register as a Client");

        this.initialMenu = new Menu(this.scanner, "Login / Register", options) {
            @Override
            void selectedOptionTrigger(int selectedOption) {
                switch (selectedOption) {
                    case 1: showLoginFormClient(); break;
                    case 2: showLoginFormTrainer(); break;
                    case 3: System.out.println("Register"); break;
                    default:
                        break;
                }
            }
        };
    }

    private void createClientMenu(){
        ArrayList<String> options = new ArrayList<>();
        options.add("My Profile");
        options.add("My Training Plan");
        options.add("My Milestones");

        this.clientMenu = new Menu(this.scanner, "Client Menu", options) {
            @Override
            void selectedOptionTrigger(int selectedOption) {
                switch (selectedOption) {
                    case 1:
                        showProfileInformation();
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    default: break;
                }
            }
        };
    }

    private void createTrainerMenu(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Exercises");
        options.add("Series");
        options.add("Sets");
        options.add("Milestones");

        this.trainerMenu = new Menu(this.scanner, "Trainer Menu", options, initialMenu) {
            @Override
            void selectedOptionTrigger(int selectedOption) {
                switch (selectedOption) {
                    case 1: exerciseMenu.show(); break;
                    default: break;
                }
            }
        };
    }

    private void createExerciseMenu(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Add Exercise");
        options.add("See Exercises");

        this.exerciseMenu = new Menu(this.scanner, "Trainer Menu", options, initialMenu) {
            @Override
            void selectedOptionTrigger(int selectedOption) {
                switch (selectedOption) {
                    default: break;
                }
            }
        };
    }

    private void showLoginFormClient(){

        String clientEmail;
        String clientPassword;

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(emailField);
        fields.add(passwordField);

        Form loginForm = new Form("Login Form Client", fields);
        loginForm.showForm();
        boolean submit = loginForm.submitForm(scanner);

        if(!submit){
            initialMenu.show(); // go back to initial menu (parent menu)
        }
        else{
            /* After filling the form */
            clientEmail = emailField.getInput();
            clientPassword = passwordField.getInput();

            /* Do something with input*/
            boolean loginSuccessful = ironTrainers.loginClient(clientEmail, clientPassword);

            if(loginSuccessful)
                clientMenu.show();  // go to next menu
            else{
                System.out.println("Wrong username or password.");
                initialMenu.show();
            }

        }
    }

    private void showLoginFormTrainer(){

        String trainerEmail;
        String trainerPassword;

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(emailField);
        fields.add(passwordField);

        Form loginForm = new Form("Login Form Trainer", fields);
        loginForm.showForm();
        boolean submit = loginForm.submitForm(scanner);

        if(!submit){
            initialMenu.show();
        }
        else{
            /* After filling the form */
            trainerEmail = emailField.getInput();
            trainerPassword = passwordField.getInput();

            /* Do something with input*/
            boolean loginSuccessful = ironTrainers.loginTrainer(trainerEmail, trainerPassword);

            if(loginSuccessful)
                trainerMenu.show();  // go to next menu
            else{
                System.out.println("Wrong username or password.");
                initialMenu.show();
            }
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

    private void showProfileInformation(){
        Client client =
    }
    
    private void showCreateExerciseForm(){
        /**/
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

        exerciseNameField = new Field(scanner, "Exercise name"){

            @Override
            protected boolean canInputBeParsed() {
                return true;
            }
        };

        exerciseDescriptionField = new Field(scanner, "Exercise description"){

            @Override
            protected boolean canInputBeParsed() {
                return true;
            }
        };

        exerciseBodyPartField = new Field(scanner, "Exercise body part (Arm, back, chest, leg)"){

            @Override
            protected boolean canInputBeParsed() {
                if(this.input.equalsIgnoreCase("arm") ||
                        this.input.equalsIgnoreCase("back") ||
                        this.input.equalsIgnoreCase("chest") ||
                        this.input.equalsIgnoreCase("leg")){
                    return true;
                }

                return false;
            }
        };
    }
}
