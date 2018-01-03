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
    private Menu profileMenu;
    private Menu clientTrainingPlanMenu;

    // FIELDS
    private Field emailField;
    private Field passwordField;
    private Field nameField;
    private Field genderField;
    private Field weightField;
    private Field heightField;
    private Field dayField;
    private Field birthYearField;
    private Field birthMonthField;
    private Field birthDayField;


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

        this.initialMenu.show();
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
        createProfileMenu();
        createClientTrainingPlanMenu();
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
                    case 3: showRegisterFrom(); break;
                    default:
                        break;
                }
            }
        };
    }

    private void createClientTrainingPlanMenu(){
        ArrayList<String> options = new ArrayList<>();
        options.add("See Another Training Plan");

        this.clientTrainingPlanMenu = new Menu(this.scanner, "Training Plan Menu", options, clientMenu) {
            @Override
            void selectedOptionTrigger(int selectedOption) {
                switch (selectedOption) {
                    case 1:
                        String user = ironTrainers.getUser();
                        showTrainingPlanInformation(user);
                        clientTrainingPlanMenu.show();
                       break;
                    default: break;
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
                        profileMenu.show();
                        break;
                    case 2:
                        String user = ironTrainers.getUser();
                        showTrainingPlanInformation(user);
                        clientTrainingPlanMenu.show();
                        break;
                    case 3:

                        break;
                    default: break;
                }
            }
        };
    }

    private void createProfileMenu(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Edit Weight");
        options.add("Edit Height");

        this.profileMenu = new Menu(this.scanner, "Profile Menu", options, clientMenu) {
            @Override
            void selectedOptionTrigger(int selectedOption) {
                switch (selectedOption) {
                    case 1:
                        editWeight();
                        break;
                    case 2:
                        editHeight();
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

    private void showTrainingPlanInformation(String name){

        Number day = null;
        boolean valid = false;

        while (!valid) {
            System.out.println("Choose a day: ");
            if (scanner.hasNextInt()) {
                day = scanner.nextInt(); // Scans the next token of the input as an int.
                valid = true;
            }
            else{
                scanner.next();
            }
        }
        try {
            System.out.println(ironTrainers.getDailyPlan(name, day));
        }
        catch (Exception e){
            System.out.println("There's no training plan for day " + day);
        }
    }

    private void editWeight(){

        Number weight;
        Client client = ironTrainers.getClientByEmail(ironTrainers.getUser());

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(weightField);

        Form editWeight = new Form("Edit Weight Form", fields);
        editWeight.showForm();
        boolean submit = editWeight.submitForm(scanner);
        
        if (!submit){
            profileMenu.show();
        }
        else{
            weight = Float.parseFloat(weightField.getInput());
            client.setWeight(weight);
            clientMenu.show();
        }
    }

    private void editHeight(){

        Number height;
        Client client = ironTrainers.getClientByEmail(ironTrainers.getUser());

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(heightField);

        Form editHeight = new Form("Edit Height Form", fields);
        editHeight.showForm();
        boolean submit = editHeight.submitForm(scanner);

        if (!submit){
            profileMenu.show();
        }
        else{
            height = Integer.parseInt(heightField.getInput());
            client.setHeight(height);
            clientMenu.show();
        }
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
        fields.add(nameField);
        fields.add(genderField);
        fields.add(weightField);
        fields.add(heightField);
        fields.add(birthYearField);
        fields.add(birthMonthField);
        fields.add(birthDayField);

        Form loginForm = new Form("Login Form Trainer", fields);
        loginForm.showForm();
        boolean submit = loginForm.submitForm(scanner);

        if(!submit){
            initialMenu.show();
        }
        else{
            /* After filling the form */
            email = emailField.getInput();
            password = passwordField.getInput();
            name = nameField.getInput();

            if(genderField.input.equalsIgnoreCase("f")){
                gender = 'f';
            }
            else {
                gender = 'm';
            }

            weight = Float.parseFloat(weightField.getInput());
            height = Integer.parseInt(heightField.getInput());
            birthDate = new MyUtils.Date(Integer.parseInt(birthYearField.getInput()),
                                            Integer.parseInt(birthMonthField.getInput()),
                                            Integer.parseInt(birthDayField.getInput()));


            Client newClient;

            if(gender == 'f'){  // female
                 newClient = new Client(email, password, name, FQuote.getInstance(), weight, height, birthDate);
            }
            else{
                newClient = new Client(email, password, name, MQuote.getInstance(), weight, height, birthDate);
            }

            /* Do something with input*/
            ironTrainers.addClient(newClient);

            System.out.println("New user created.");

            initialMenu.show();
        }
    }

    private void showProfileInformation(){
        Client client = ironTrainers.getClientByEmail(ironTrainers.getUser());

        System.out.println("\nMy Profile Information\n");

        if (client.getGender().toString() == "<F>")
            System.out.println("Gender: Female");
        else
            System.out.println("Gender: Male");

        System.out.println("Height: " + client.getHeight());
        System.out.println("Weight: " + client.getWeight());
        System.out.println("Age: " + client.getAge());
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

        heightField = new Field(scanner, "Height"){

            @Override
            protected boolean canInputBeParsed() {
                try{
                    Integer.parseInt(this.input);
                    return true;
                }catch (Exception e){
                    return false;
                }
            }
        };

        dayField = new Field(scanner, "Day"){

            @Override
            protected boolean canInputBeParsed() {
                try{
                    Integer.parseInt(this.input);
                    return true;
                }catch (Exception e){
                    return false;
                }
            }
        };

        birthYearField = new Field(scanner, "Birth year"){

            @Override
            protected boolean canInputBeParsed() {
                try{
                    Integer.parseInt(this.input);
                    return true;
                }catch (Exception e){
                    return false;
                }
            }
        };

        birthMonthField = new Field(scanner, "Birth month"){

            @Override
            protected boolean canInputBeParsed() {
                try{
                    Integer.parseInt(this.input);
                    return true;
                }catch (Exception e){
                    return false;
                }
            }
        };

        birthDayField = new Field(scanner, "Birth day"){

            @Override
            protected boolean canInputBeParsed() {
                try{
                    Integer.parseInt(this.input);
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
