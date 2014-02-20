package com.github.epochcoder.prettyconsole;

import com.github.epochcoder.prettyconsole.handlers.ConsoleBoxPasswordHandler;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * represents a console display box in the system console (or any PrintStream).
 * used to display friendly technical information
 * @author Willie Scholtz
 */
public final class ConsoleBox {

    /**
     * the character to use in box corners
     */
    private String boxChar = " + ";

    /**
     * the character to use to pad strings with
     */
    private char padChar = ' ';

    /**
     * the character used for the box's right and left sides
     */
    private String endChar = " | ";

    /**
     * the character used for the box's top, title and bottom sides
     */
    private String borderChar = "-";

    /**
     * the character used for representing black in a color
     */
    private String blackChar = " ";

    /**
     * the character used for representing white in a color
     */
    private String whiteChar = "#";

    /**
     * the character used for representing aliasing of fonts.
     * actually other colors, but since our image is only black and white
     * it will represent a shade, which in this case is aliasing
     */
    private String aliasChar = " ";

    /**
     * the key value separator for names and values
     */
    private String keyValueSeparator = " : ";

    private final List<ConsoleBoxKeyHandler> handlers;
    private final StringBuilder builder;
    private boolean content;
    private final int width;

    /**
     * creates a new instance of a ConsoleBox
     * @param boxWidth the width of the box (in character count)
     * @param title the initial title of the box, leave blank for none
     */
    public ConsoleBox(int boxWidth, String title) {
        this.width = boxWidth;
        this.builder = new StringBuilder();
        this.handlers = new ArrayList<ConsoleBoxKeyHandler>();

        // add default password handler
        this.handlers.add(new ConsoleBoxPasswordHandler());

        if (!Strings.isNullOrEmpty(title)) {
            this.title(title);
        }
    }

    /*
     * creates a new instance of a ConsoleBox with no title
     * @param boxWidth the width of the box (in character count)
     */
    public ConsoleBox(int boxWidth) {
        this(boxWidth, null);
    }
    
    /**
     * pads a string from both sides
     * @param string the string to pad
     * @param pad the padding character
     * @param length the length to pad
     * @return the padded string
     */
    private String padBoth(String string, String pad, int length) {
        int right = (length - string.length()) / 2 + string.length();
        String result = Strings.padEnd(string, right, pad.toCharArray()[0]);
        return Strings.padStart(result, length, pad.toCharArray()[0]);
    }
    
    /**
     * configures the box characters, note that sensible defaults are already set.
     * @param cornerChar the character to use in box corners
     * @param padChar the character to use to pad strings with
     * @param sideChar the character used for the box's right and left sides
     * @param borderChar the character used for the box's top, title and bottom sides
     * @return the current instance
     */
    public ConsoleBox setBoxCharacters(String cornerChar, char padChar, String sideChar, String borderChar) {
        this.boxChar = Preconditions.checkNotNull(cornerChar);
        this.padChar = Preconditions.checkNotNull(padChar);
        this.endChar = Preconditions.checkNotNull(sideChar);
        this.borderChar = Preconditions.checkNotNull(borderChar);
        return this;
    }
    
    /**
     * configures the box's ASCII characters, note that sensible defaults are already set.
     * @param blackChar the character used for representing black in a color
     * @param whiteChar the character used for representing white in a color
     * @param aliasChar the character used for representing aliasing of fonts.
     * actually other colors, but since our image is only black and white
     * it will represent a shade, which in this case is aliasing.
     * @return the current instance
     */
    public ConsoleBox setAsciiCharacters(String blackChar, String whiteChar, String aliasChar) {
        this.blackChar = Preconditions.checkNotNull(blackChar);
        this.whiteChar = Preconditions.checkNotNull(whiteChar);
        this.aliasChar = Preconditions.checkNotNull(aliasChar);

        return this;
    }
    
    /**
     * configures the box's key-value separator.
     * @param keyValueSeperator the character used for representing black in a color
     * @param whiteChar the character used for representing white in a color
     * @param aliasChar the character used for representing aliasing of fonts.
     * actually other colors, but since our image is only black and white
     * it will represent a shade, which in this case is aliasing.
     * @return the current instance
     */
    public ConsoleBox setKeyValueSeparator(String keyValueSeparator) {
        this.keyValueSeparator = Preconditions.checkNotNull(keyValueSeparator);
        return this;
    }

    /**
     * adds a new key-value handler for this ConsoleBox
     * @param handler the handler to use
     * @return the current instance
     */
    public ConsoleBox handler(ConsoleBoxKeyHandler handler) {
        this.handlers.add(handler);
        return this;
    }

    /**
     * builds and writes this box to the specified output stream
     * @param output
     */
    public void build(PrintStream output) {
        this.title("");
        output.println(this.builder.toString());
    }

    /**
     * adds a title section to the console box
     * @param title the title to use
     * @return the current box
     */
    public ConsoleBox title(String title) {
        this.builder.append("\n").append(this.boxChar).append(padBoth(title,
                this.borderChar, this.width)).append(this.boxChar);

        return this;
    }

    /**
     * adds an empty line section to the console box
     * @return the current box
     */
    public ConsoleBox empty() {
        this.builder.append("\n").append(this.boxChar).append(
                padBoth("", " ", this.width)).append(this.boxChar);

        return this;
    }

    /**
     * generates and writes the specified text as an ASCII image into this box
     * @param text the text to write as ASCII
     * @param invert should the ASCII colors be inverted?
     * @return the current box
     */
    public ConsoleBox ascii(String text, boolean invert) {
        final BufferedImage image = new BufferedImage(this.width,
                32, BufferedImage.TYPE_INT_RGB);

        final Graphics graphics = image.getGraphics();
        final Graphics2D g2d = (Graphics2D) graphics;

        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Font textFont = new Font("Dialog", Font.BOLD, 22);
        FontMetrics textMetrics = g2d.getFontMetrics(textFont);
        g2d.setFont(textFont);

        int tX = (image.getWidth() / 2) - (textMetrics.stringWidth(text) / 2);
        int tY = (image.getHeight() / 2) + (textMetrics.getHeight() / 2) - 5;

        g2d.drawString(text, tX, tY);
        g2d.drawRenderedImage(image, null);
        g2d.dispose();

        final int iHeight = image.getHeight();
        final int iWidth = image.getWidth();

        final String bChar = invert ? this.whiteChar : this.blackChar;
        final String wChar = invert ? this.blackChar : this.whiteChar;

        for (int y = 0; y < iHeight; y++) {
            final StringBuilder sb = new StringBuilder();
            for (int x = 0; x < iWidth; x++) {
                final int rgbColor = image.getRGB(x, y);
                sb.append(rgbColor == -16777216 ? bChar : rgbColor == -1 ? wChar : aliasChar);
            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            this.builder.append("\n").append(this.endChar)
                    .append(sb).append(this.endChar);
        }

        return this;
    }

    /**
     * adds a informational line to the console box,
     * automatically splitting large values
     * @param key the name of the value to display
     * @param value the value of this line
     * @return the current box
     */
    public ConsoleBox line(String key, String value) {
        key = Strings.isNullOrEmpty(key) ? "null" : key;
        value = Strings.isNullOrEmpty(value) ? "" : value;

        // get the key length
        final int kL = key.length();
        final int kSl = this.keyValueSeparator.length();
        
        // calculate remaining box space for the value
        final int ths = (this.width - kL - kSl);
        Preconditions.checkState(ths > -1, "key[" + key + "] is to long "
                + "for box with a " + width + " width!");

        // \n | the_key_length_in_spaces
        final String joinOn = ("\n" + this.endChar + Strings.padEnd("",
                kL + kSl, this.padChar));

        // get key handlers and modify if neccessary
        for (ConsoleBoxKeyHandler handler : this.handlers) {
            if (handler.shouldHandle(key)) {
                value = handler.handleValue(key, value);
                // don't break, possibilitty of multiple handlers
            }
        }

        // if a key handler returns null, a key should be skipped
        if (value != null) {
            // split the string on either length or new lines
            Iterable<String> splitted = Splitter.on(Pattern
                    .compile("(?<=\\G.{" + ths + "})|\\n")).split(value);

            // add the value + end characters (multiple lines)
            String formatted = Joiner.on(joinOn).join(
                    Iterables.transform(splitted, new Function<String, String>() {
                @Override
                public String apply(String input) {
                    return Strings.padEnd(input, ths, ' ') + endChar;
                }
            }));

            // write completed line to builder
            this.builder.append("\n").append(this.endChar).append(key)
                    .append(this.keyValueSeparator).append(formatted);

            this.content = true;
        }

        return this;
    }

    /**
     * @return true if {@link #line(java.lang.String, java.lang.String)}
     * has been called at least once
     */
    public boolean hasContent() {
        return content;
    }
}