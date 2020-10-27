package com.company;

import java.io.File;

public class MkdirCommand extends Command {

    @Override
    public boolean canHandle(String commandName) {
        return commandName.equals("mkdir");
    }

    @Override
    public String getSignature() {
        return "mkdir <directory>";
    }

    @Override
    public boolean runCommand(String[] args) {
        if (args.length != 2) {
            System.err.println("Command signature: " + getSignature());
            return false;
        }

        String directory = args[1];

        try {
            var f = new File(directory);
            if (f.exists() && f.isDirectory()) {
                System.err.println("Directory already exists.");
                return false;
            }
            return f.mkdir();

        } catch (Exception exception) {
            System.err.println("Something went wrong.");
            return false;
        }
    }
}
