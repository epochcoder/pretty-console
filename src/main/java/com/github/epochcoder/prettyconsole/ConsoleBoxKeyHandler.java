package com.github.epochcoder.prettyconsole;

/**
 * interface for defining handlers for certain console box keys
 * @author Willie Scholtz
 */
public interface ConsoleBoxKeyHandler {

    /**
     * determines if the specified key should be handled by this key handler
     * @param key the key to check
     * @return true if this key should be handled by this key handler
     */
    public boolean shouldHandle(final String key);

    /**
     * if this handler is set to handle the specified key it, will execute this method to handle it's value
     * @param key the key being handled
     * @param value the original value to handle, note that console box automatically handles new lines and long lines
     * @return the formatted value for ConsoleBox, or null if the whole line should be skipped
     */
    public String handleValue(final String key, final String value);

}