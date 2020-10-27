package com.company;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

public class LsCommand extends Command {

    @Override
    public boolean canHandle(String commandName) {
        return commandName.equals("ls");
    }

    @Override
    public String getSignature() {
        return "ls [-r]";
    }

    @Override
    public boolean runCommand(String[] args) {
        if (args.length > 2 || args.length == 2 && !args[1].equals("-r")) {
            System.err.println("Command signature: " + getSignature());
            return false;
        }

        try {
            String[] paths = new File(System.getProperty("user.dir")).list();
            if (args.length == 2 && args[1].equals("-r")) {
                Arrays.sort(paths, Collections.reverseOrder());
            } else {
                Arrays.sort(paths);
            }
            for (String pathname : paths)
                System.out.println("  " + pathname);
            return true;
        } catch (Exception exception) {
            System.err.println("Something went wrong.");
            return false;
        }
    }
}
