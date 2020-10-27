package com.company;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CatCommand extends Command {

    @Override
    public boolean canHandle(String commandName) {
        return commandName.equals("cat");
    }

    @Override
    public String getSignature() {
        return "cat <filename.txt> {<filename.txt>}";
    }

    @Override
    public boolean runCommand(String[] args) {
        if (args.length < 2) {
            System.err.println("Command signature: " + getSignature());
            return false;
        }

        try {
            for (int i = 1; i < args.length; i++) {
                String filename = args[i];
                var file = new File(filename);
                if (!file.exists() || !file.isFile()) {
                    System.err.println("File " + filename + " was not found.");
                } else {
                    for (String line : Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8)) {
                        System.out.println(line);
                    }
                    System.out.println();
                }
            }
            return true;
        } catch (Exception exception) {
            System.err.println("Something went wrong.");
            return false;
        }
    }
}
