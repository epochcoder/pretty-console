
package com.github.epochcoder.prettyconsole.handlers;

import com.github.epochcoder.prettyconsole.ConsoleBoxKeyHandler;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

/**
 * ensures that JVM system properties are handled correctly by consolebox
 * @author Willie Scholtz
 */
public class JvmKeyHandler implements ConsoleBoxKeyHandler {
    
    private static final boolean WINDOWS = System.getProperty("os.name").indexOf("Windows") > -1;
    
    /**
     * keys that should be converted to new lines
     */
    private static final String[] CLASSPATHS = new String[] {
        "java.library.path", "java.class.path", "sun.boot.class.path"
    };

    /**
     * keys that should be skipped
     */
    private static final String[] SKIPS = new String[] {
        "line.seperator"
    };

    @Override
    public boolean shouldHandle(String key) {
        for (String string : CLASSPATHS) {
            if (string.equals(key)) {
                return true;
            }
        }

        for (String string : SKIPS) {
            if (string.equals(key)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String handleValue(String key, String value) {
        for (String string : CLASSPATHS) {
            if (string.equals(key)) {
                value = Joiner.on("\n").join(Iterables.transform(Splitter.on(WINDOWS ? ";" : ":").split(value),
                        new Function<String, String>() {
                    int item = 0;
                    @Override
                    public String apply(String input) {
                        return "[" + ++item + "] " + input;
                    }
                }));
            }
        }

        for (String string : SKIPS) {
            if (string.equals(key)) {
                return null;
            }
        }

        return value;
    }
}