package com.mfes;

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
                    case 1: System.out.println("You have an account"); break;
                    case 2: System.out.println("You don't have an account"); break;
                    default:
                        break;
                }
            }
        };

        loginMenu.show();
    }

    private void showLoginForm(){

    }
}
