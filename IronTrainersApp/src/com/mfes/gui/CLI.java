package com.mfes.gui;

import com.mfes.model.*;
import com.mfes.model.quotes.*;
import org.overture.codegen.runtime.VDMSeq;
import org.overture.codegen.runtime.VDMSet;

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
    private Menu clientMilestone;
    private Menu milestoneTrainerMenu;

    // FIELDS
    private Field emailField;
    private Field passwordField;
    private Field nameField;
    private Field genderField;
    private Field weightField;
    private Field heightField;
    private Field birthYearField;
    private Field birthMonthField;
    private Field birthDayField;

    private Field exerciseNameField;
    private Field exerciseDescriptionField;
    private Field exerciseBodyPartField;
    private Field exerciseNumRepsField;
    private Field exerciseTimeField;
    private Field exerciseLoadField;

    private Field trainingPlanDayField;

    CLI() {
        this.scanner = new Scanner(System.in);
        this.ironTrainers = new IronTrainers();

        createDummyUsers();
        createDummyMilestones();
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

        // TODO: comment this:
        Trainer test = new Trainer("t", "t", "t");
        this.ironTrainers.addTrainer(test);

        this.ironTrainers.addClient(c1);
        this.ironTrainers.addClient(c2);
        this.ironTrainers.addClient(c3);

        this.ironTrainers.addTrainer(t1);
    }

    private void createDummyMilestones(){
        Exercise e1 = new Exercise("Push up", "Push yourself off the ground, using only your arms' strength.", ArmQuote.getInstance());
        Exercise e2 = new Exercise("Sit up", "Lying down on the ground, elevate your upper body using abdominal strength.", ChestQuote.getInstance());
        Exercise e3 = new Exercise("Squat", "Bend your knees and lift your weight using your leg strength.", LegQuote.getInstance());
        Exercise e4 = new Exercise("Pull up", "Grab a bar and pull your body weight so that your chin touches the bar.", BackQuote.getInstance());

        Exercise e5 = new Exercise("Jogging", "Using no weights at all, run with low intensity.", LegQuote.getInstance());

        this.ironTrainers.addExercise(e1);
        this.ironTrainers.addExercise(e2);
        this.ironTrainers.addExercise(e3);
        this.ironTrainers.addExercise(e4);
        this.ironTrainers.addExercise(e5);

        Series s1 = new Series(120, 5, e1);
        Series s2 = new Series(1, e5, 120);

        MySet set1 = new MySet();
        set1.addSeries(s1);
        set1.addSeries(s2);

        MySet set2 = new MySet();
        set1.addSeries(s1);

        Client c1 = ironTrainers.getClientByEmail("client1@mfes.com");

        Milestone m1 = c1.getMilestone();
        m1.setDesiredWeight(100);
        m1.getTrainingPlan().setDailyPlan(1, set1);
        m1.getTrainingPlan().setDailyPlan(2, set2);
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
        createClientMilestoneMenu();
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
        options.add("Logout");

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
                        break;
                    case 3:
                        showClientMilestone();
                        clientMilestone.show();
                        break;
                    case 4:
                        ironTrainers.logout();
                        initialMenu.show();
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

    private void createClientMilestoneMenu(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Edit Desired Weight");

        this.clientMilestone = new Menu(this.scanner, "Profile Menu", options, clientMenu) {
            @Override
            void selectedOptionTrigger(int selectedOption) {
                switch (selectedOption) {
                    case 1:
                        editDesiredWeight();
                        break;
                    default: break;
                }
            }
        };
    }

    private void createTrainerMenu(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Exercises");
        options.add("Clients' Milestones");
        options.add("Logout");

        this.trainerMenu = new Menu(this.scanner, "Trainer Menu", options, initialMenu) {
            @Override
            void selectedOptionTrigger(int selectedOption) {
                switch (selectedOption) {
                    case 1: exerciseMenu.show(); break;
                    case 2: showSearchClientForm(); break;
                    case 3:
                        ironTrainers.logout();
                        initialMenu.show();
                        break;
                    default: break;
                }
            }
        };
    }

    private void createExerciseMenu(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Add Exercise");
        options.add("See Exercises");

        this.exerciseMenu = new Menu(this.scanner, "Exercises Menu", options, trainerMenu) {
            @Override
            void selectedOptionTrigger(int selectedOption) {
                switch (selectedOption) {
                    case 1: showCreateExerciseForm(); break;
                    case 2: showAllExercises(); break;
                    default: break;
                }
            }
        };
    }

    private void createMileStoneMenu(Client c){
        ArrayList<String> options = new ArrayList<>();
        options.add("See milestone");
        options.add("Change training plan");

        this.milestoneTrainerMenu = new Menu(this.scanner, "Client " + c.getName() + " milestones", options, trainerMenu) {
            @Override
            void selectedOptionTrigger(int selectedOption) {
                switch (selectedOption) {
                    case 1: showClientMilestone(c); break;
                    case 2: changeTrainingPlanForm(c); break;
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
            MySet set = ironTrainers.getDailyPlan(name, day);
            printSeries(set);
        }
        catch (Exception e){
            System.out.println("There's no training plan for day " + day);
        }

        clientTrainingPlanMenu.show();
    }

    private void printSeries(MySet set){

        VDMSeq series = set.getSeries();
        int i = 1;

        for(Object serie : series){
            Series s = (Series) serie;

            System.out.println("\nSeries no. " + i + "\n");

            if (s.getLoad() != null)
                System.out.println("Load: " + s.getLoad() + " kg");

            if (s.getTime() != null)
                System.out.println("Time: " + s.getLoad() + " s");

            System.out.println("Number of Repetitions: " + s.getNumberOfRepetitions() + " times");

            System.out.println("Exercise name: " + s.getExercise().getName());
            System.out.println("Exercise description: " + s.getExercise().getDescription());
            System.out.println("Exercise body part: " + s.getExercise().getBodyPart());
            System.out.println();

            i++;
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

    private void editDesiredWeight(){

        Number weight;
        Client client = ironTrainers.getClientByEmail(ironTrainers.getUser());

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(weightField);

        Form editDesiredWeight = new Form("Edit Client Milestone Form", fields);
        editDesiredWeight.showForm();
        boolean submit = editDesiredWeight.submitForm(scanner);

        if (!submit){
            clientMilestone.show();
        }
        else{
            weight = Float.parseFloat(weightField.getInput());
            client.getMilestone().setDesiredWeight(weight);
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

        if (client.getGender().toString().equalsIgnoreCase( "<F>"))
            System.out.println("Gender: Female");
        else
            System.out.println("Gender: Male");

        System.out.println("Height: " + client.getHeight());
        System.out.println("Weight: " + client.getWeight());
        System.out.println("Age: " + client.getAge());
    }

    private void showClientMilestone(){
        Client client = ironTrainers.getClientByEmail(ironTrainers.getUser());

        System.out.println("\nMy Milestone\n");
        System.out.println("Desired Weight: " + client.getMilestone().getDesiredWeight());
    }

    private void showCreateExerciseForm(){
        String name;
        String description;
        String bodyPart;

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(exerciseNameField);
        fields.add(exerciseDescriptionField);
        fields.add(exerciseBodyPartField);

        Form createExercise = new Form("Create Exercise", fields);
        createExercise.showForm();
        boolean submit = createExercise.submitForm(scanner);

        if(!submit){
            initialMenu.show();
        }
        else {
            name = exerciseNameField.getInput();
            description = exerciseDescriptionField.getInput();
            bodyPart = exerciseBodyPartField.getInput();

            Exercise exercise;
            if(bodyPart.equalsIgnoreCase("arm")){
                exercise = new Exercise(name, description, ArmQuote.getInstance());
            } else if(bodyPart.equalsIgnoreCase("back")){
                exercise = new Exercise(name, description, BackQuote.getInstance());
            } else if (bodyPart.equalsIgnoreCase("chest")){
                exercise = new Exercise(name, description, ChestQuote.getInstance());
            } else {
                exercise = new Exercise(name, description, LegQuote.getInstance());
            }

            ironTrainers.addExercise(exercise);
            System.out.println("Added exercise: " + exercise.toString());

            exerciseMenu.show();
        }
    }

    private void showSearchClientForm(){
        String email;

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(emailField);

        Form createExercise = new Form("Search client", fields);
        createExercise.showForm();
        boolean submit = createExercise.submitForm(scanner);

        if(!submit){
            trainerMenu.show();
        }
        else {
            email = emailField.getInput();

            Client client = this.ironTrainers.getClientByEmail(email);

            if(client.getEmail() != null){
                createMileStoneMenu(client);
                milestoneTrainerMenu.show();
            }
            else{
                System.out.println("A client with that email does not exist.");
                trainerMenu.show();
            }
        }
    }

    private void showClientMilestone(Client c){
        System.out.println("MILESTONE\n");
        System.out.println("Desired weight: " + c.getMilestone().getDesiredWeight());
        System.out.println("Current training plan: " + c.getMilestone().getTrainingPlan().toString());

        milestoneTrainerMenu.show();
    }

    private void printAllExercises(){
        System.out.println("** EXERCISES ** \n");

        VDMSet exercises = this.ironTrainers.getExercises();

        for(Object exercise : exercises){
            Exercise ex = (Exercise) exercise;
            System.out.println(ex.toString());
        }
    }

    private void showAllExercises(){
        printAllExercises();
        exerciseMenu.show();
    }

    private void changeTrainingPlanForm(Client c){

        int dayToChange = 0;
        MySet exerciseSet = new MySet();

        ArrayList<Field> tpFields = new ArrayList<>();
        tpFields.add(trainingPlanDayField);

        Form changeTrainingPlan = new Form("Change daily plan", tpFields);
        changeTrainingPlan.showForm();
        boolean tpSubmit = changeTrainingPlan.submitForm(scanner);

        if(!tpSubmit){
            milestoneTrainerMenu.show();
        }
        else {
            dayToChange = Integer.parseInt(trainingPlanDayField.getInput());
        }

        while(true){

            System.out.println("Add new series.");

            String exerciseName;
            int numReps;
            int timeSeconds;

            ArrayList<Field> fields = new ArrayList<>();
            fields.add(exerciseNameField);
            fields.add(exerciseNumRepsField);
            fields.add(exerciseTimeField);

            Form createExercise = new Form("Add exercise to series", fields);
            createExercise.showForm();
            boolean submit = createExercise.submitForm(scanner);

            if(!submit){
                trainerMenu.show();
            }
            else {
                exerciseName = exerciseNameField.getInput();
                numReps = Integer.parseInt(exerciseNumRepsField.getInput());
                timeSeconds = Integer.parseInt(exerciseTimeField.getInput());

                Exercise exercise = this.ironTrainers.getExercise(exerciseName);

                if(exercise.getName() == null){
                    System.out.println("An exercise with that name does not exist. Pick one of the next.");
                    printAllExercises();
                    continue;
                }
                else {
                    Series s = new Series(numReps, exercise, timeSeconds);
                    exerciseSet.addSeries(s);
                }
            }

            System.out.println("Do you want to add another series? Yes or no: ");

            String input;
            input = this.scanner.nextLine();

            if(input.equalsIgnoreCase("n")){
                break;
            }
        }

        c.getMilestone().getTrainingPlan().setDailyPlan(dayToChange, exerciseSet);
        System.out.println("Changed set of exercises of day: " + dayToChange);
        milestoneTrainerMenu.show();
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

        exerciseNumRepsField = new Field(scanner, "Number of repetitions"){

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

        exerciseTimeField = new Field(scanner, "Time in seconds"){
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

        exerciseLoadField = new Field(scanner, "Load in kg"){
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

        trainingPlanDayField = new Field(scanner, "Day of training plan"){

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
    }
}
