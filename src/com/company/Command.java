package com.company;

/**
 * Command class implemented with Chain of Responsibility pattern.
 */
public abstract class Command {

    // Next command in a chain.
    private Command next;

    /**
     * Links two commands together and forms a command chain.
     *
     * @param other Other command.
     * @return Chained command.
     */
    public Command setNext(Command other) {
        next = other;
        return other;
    }

    /**
     * Check that the given command can be handled.
     *
     * @param commandName Name of the command.
     * @return Whether the command can be handled or not.
     */
    public abstract boolean canHandle(String commandName);

    /**
     * Runs the command.
     *
     * @param args String array that contains parsed data.
     * @return True if there were no runtime errors, False otherwise.
     */
    public abstract boolean runCommand(String[] args);

    /**
     * Returns a full command signature.
     *
     * @return String containing signature.
     */
    public abstract String getSignature();

    public boolean handle(String[] args) {
        if (args.length == 0)
            throw new IllegalArgumentException("args.length == 0");

        if (!canHandle(args[0]))
            return next != null && next.handle(args);

        return runCommand(args);
    }
}
