package com.company;

public class Main {

    /**
     * Links all commands in a chain.
     *
     * @param commands Command array.
     * @return First command in a new chain.
     */
    public static Command linkCommands(Command[] commands) {
        if (commands.length == 0)
            throw new IllegalArgumentException("commands.length == 0");

        Command command = commands[0];
        for (int i = 1; i < commands.length; i++)
            command = command.setNext(commands[i]);

        return commands[0];
    }

    /**
     * Prints all command signatures.
     *
     * @param commands Command array.
     */
    public static void printSignatures(Command[] commands) {
        System.out.println("List of available commands: ");
        for (var command : commands) {
            System.out.println("  " + command.getSignature());
        }
    }

    public static void main(String[] args) {
        var commands = new Command[]{
                new LsCommand(),
                new MkdirCommand(),
                new EchoCommand(),
                new CatCommand(),
        };

        if (args.length == 0) {
            printSignatures(commands);
            System.exit(0);
        }

        Command handler = linkCommands(commands);

        if (handler.handle(args)) {
            System.exit(0);
        } else {
            printSignatures(commands);
            System.exit(1);
        }
    }
}
