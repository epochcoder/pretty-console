package com.github.epochcoder.prettyconsole.handlers;

import com.github.epochcoder.prettyconsole.ConsoleBoxKeyHandler;
import java.util.regex.Pattern;

/**
 * ensures no passwords are present in the ConsoleBox
 * @author Willie Scholtz
 */
public class ConsoleBoxPasswordHandler implements ConsoleBoxKeyHandler {
    /**
     * pattern defining which attributes should be blurred
     */
    private static final Pattern IGNORE_ATT_PATT = Pattern
            .compile("pass(word)?", Pattern.CASE_INSENSITIVE);

    /**
     * replacement value for sensitive data
     */
    private static final String SAFE_REPLACEMENT = "*****";

    /**
     * determines if a certain parameter is safe to transmit over the wire
     * @param key the name of the parameter that will be sent
     * @return true if the parameter may be sent
     */
    private static boolean safeToTransmit(final String key) {
        return !IGNORE_ATT_PATT.matcher(key).find();
    }

    @Override
    public boolean shouldHandle(String key) {
        return !safeToTransmit(key);
    }

    @Override
    public String handleValue(String key, String value) {
        return SAFE_REPLACEMENT;
    }
}