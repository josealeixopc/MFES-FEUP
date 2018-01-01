package com.mfes;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Field {

    protected String description;
    protected Scanner scanner;
    protected String input;

    Field(Scanner scanner, String description){
        this.description = description;
        this.scanner = scanner;
    }

    public void print(){
        System.out.print(this.description + ": ");
    }

    public void readInput(){
        this.input = this.scanner.nextLine();
    }

    public String getInput() {
        return input;
    }

    /**
     * This function verifies if the input has been correctly parsed to the wanted type.
     * @return True if the input can be parsed to a defined type, false otherwise.
     */
    protected abstract boolean canInputBeParsed();
}
