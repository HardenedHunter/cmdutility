package com.company;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

public class EchoCommand extends Command {

    @Override
    public boolean canHandle(String commandName) {
        return commandName.equals("echo");
    }

    @Override
    public String getSignature() {
        return "echo <text> <filename.txt>";
    }

    @Override
    public boolean runCommand(String[] args) {
        if (args.length != 3) {
            System.err.println("Command signature: " + getSignature());
            return false;
        }

        String text = args[1];
        String filename = args[2];

        try {
            var file = new File(filename);
            if (file.exists() && file.isFile()) {
                System.err.println("File already exists.");
                return false;
            }

            Files.write(Paths.get(filename), Collections.singleton(text), StandardCharsets.UTF_8);
            return true;
        } catch (Exception exception) {
            System.err.println("Something went wrong.");
            return false;
        }
    }
}
