package xyz.brandon.blackjack.utils;

import java.util.HashMap;
import java.util.Set;

public class ArgsParser {

    private final String identifier;
    private final HashMap<String, String> args;

    public ArgsParser(String indentifierArgString) {
        this(indentifierArgString.split("=")[0], indentifierArgString.split("=")[1]);
    }

    public ArgsParser(String identifier, String argString) {
        args = new HashMap<>();
        this.identifier = identifier;

        for (String arg : argString.split("_")) { //_ divides args
            add(arg.split(":")[0], arg.split(":")[1]);
        }
    }

    public boolean has(String arg) {
        return args.containsKey(arg);
    }

    public void add(String arg, String value) {
        if (has(arg)) {
            System.out.println(arg + " is already in argsParser, updating value.");
        }
        args.put(arg, value);
    }

    public String get(String arg) {
        return args.getOrDefault(arg, null);
    }

    public String getIdentifier() {
        return identifier;
    }

    public Set<String> getArgs() {
        return args.keySet();
    }

}
