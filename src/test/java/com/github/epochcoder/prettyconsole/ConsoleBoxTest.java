package com.github.epochcoder.prettyconsole;

import com.google.common.base.Charsets;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for testing console box
 */
public class ConsoleBoxTest extends TestCase {

    /**
     * Create the test case
     * @param testName name of the test case
     */
    public ConsoleBoxTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ConsoleBoxTest.class);
    }

    private ConsoleBox basicBox(int width) {
        ConsoleBox box = new ConsoleBox(width);
        box.title("TEST");
        return box;
    }

    private String getBox(ConsoleBox box) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        box.build(new PrintStream(baos));

        String actual = null;
        try {
            actual = baos.toString(Charsets.UTF_8.name());
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ConsoleBoxTest.class.getName()).log(
                    Level.SEVERE, "failed to get UTF-8", ex);
        }

        System.out.println(actual);
        return actual;
    }

    /**
     * @throws java.io.UnsupportedEncodingException
     */
    public void testCreation() throws UnsupportedEncodingException {
        ConsoleBox box = this.basicBox(10);
        box.line("a", "1");

        final String actual = this.getBox(box);
        final String expected = "\n" +
                                " + ---TEST--- + \n" +
                                " | a : 1      | \n" +
                                " + ---------- + \r\n";

        assertEquals("basic box build", expected, actual);
    }

    public void testOverflow() throws UnsupportedEncodingException {
        ConsoleBox box = this.basicBox(10);
        box.line("a", "123456789987654321");

        final String actual = this.getBox(box);
        final String expected = "\n" +
                                " + ---TEST--- + \n" +
                                " | a : 123456 | \n" +
                                " |     789987 | \n" +
                                " |     654321 | \n" +
                                " |            | \n" +
                                " + ---------- + \r\n";

        assertEquals("overflow in content", expected, actual);
    }

    public void testNewLineOverflow() throws UnsupportedEncodingException {
        ConsoleBox box = this.basicBox(10);
        box.line("a", "1\n2\n3\n4\n5\n6\n7\n8\n9\n12345679abcdefg");

        final String actual = this.getBox(box);
        final String expected = "\n" +
                                " + ---TEST--- + \n" +
                                " | a : 1      | \n" +
                                " |     2      | \n" +
                                " |     3      | \n" +
                                " |     4      | \n" +
                                " |     5      | \n" +
                                " |     6      | \n" +
                                " |     7      | \n" +
                                " |     8      | \n" +
                                " |     9      | \n" +
                                " |     123456 | \n" +
                                " |     79abcd | \n" +
                                " |     efg    | \n" +
                                " + ---------- + \r\n";

        assertEquals("overflow in content with new lines", expected, actual);
    }

    public void testDefaultPasswordHandling() throws UnsupportedEncodingException {
        ConsoleBox box = this.basicBox(20);
        box.line("password", "supercoolpassword");
        box.line("pass", "mysecret");

        final String actual = this.getBox(box);
        final String expected = "\n" +
                                " + --------TEST-------- + \n" +
                                " | password : *****     | \n" +
                                " | pass : *****         | \n" +
                                " + -------------------- + \r\n";


        assertEquals("default password handling", expected, actual);
    }

    public void testLinesAndTitles() throws UnsupportedEncodingException {
        ConsoleBox box = this.basicBox(25);
        box.line("keyOne", "valueOne");
        box.line("keyTwo", "valueTwo");
        box.title("TITLE");
        box.line("keyThree", "valueThree");
        box.line("keyFour", "valueFour");

        final String actual = this.getBox(box);
        final String expected = "\n" +
                                " + -----------TEST---------- + \n" +
                                " | keyOne : valueOne         | \n" +
                                " | keyTwo : valueTwo         | \n" +
                                " + ----------TITLE---------- + \n" +
                                " | keyThree : valueThree     | \n" +
                                " | keyFour : valueFour       | \n" +
                                " + ------------------------- + \r\n";

        assertEquals("with lines and titles", expected, actual);
    }

    public void testAscii() throws UnsupportedEncodingException {
        ConsoleBox box = this.basicBox(60);
        box.line("keyOne", "valueOne");
        box.line("keyTwo", "valueTwo");
        box.title("TITLE");
        box.line("keyThree", "valueThree");
        box.line("keyFour", "valueFour");
        box.title("");
        box.ascii("ASCII", false);

        final String actual = this.getBox(box);
        final String expected = "\n" +
                                " + ----------------------------TEST---------------------------- + \n" +
                                " | keyOne : valueOne                                            | \n" +
                                " | keyTwo : valueTwo                                            | \n" +
                                " + ----------------------------TITLE--------------------------- + \n" +
                                " | keyThree : valueThree                                        | \n" +
                                " | keyFour : valueFour                                          | \n" +
                                " + ------------------------------------------------------------ + \n" +
                                " |         ###                                      ###  ###    | \n" +
                                " |        ####          ########        #######     ###  ###    | \n" +
                                " |        #####        ##########      ##########   ###  ###    | \n" +
                                " |        ## ##        ##      ##     ##       ##   ###  ###    | \n" +
                                " |       ##   #        ##      ##    ##             ###  ###    | \n" +
                                " |       ##   ##       ##            ##             ###  ###    | \n" +
                                " |       ##   ##        ###          ##             ###  ###    | \n" +
                                " |      ##     #         ######      ##             ###  ###    | \n" +
                                " |      ##     ##            ###     ##             ###  ###    | \n" +
                                " |      #########              ##    ##             ###  ###    | \n" +
                                " |     ###########    ##       ##    ##             ###  ###    | \n" +
                                " |     ###########    ##       ##    ##             ###  ###    | \n" +
                                " |     ##       ##    ###      ##     ##       ##   ###  ###    | \n" +
                                " |    ##         ##    ##########      #########    ###  ###    | \n" +
                                " |    ##         ##     ########        #######     ###  ###    | \n" +
                                " |    ##         ##                        #        ###  ###    | \n" +
                                " + ------------------------------------------------------------ + \r\n";

        assertEquals("with ascii text", expected, actual);
    }

    public void testCustomHandler() throws UnsupportedEncodingException {
        ConsoleBox box = this.basicBox(25);
        box.handler(new ConsoleBoxKeyHandler() {
            public boolean shouldHandle(String key) {
                return "a".equals(key) || "c".equals(key);
            }

            public String handleValue(String key, String value) {
                if ("a".equals(key)) {
                    return "a_replace";
                }

                if ("c".equals(key)) {
                    return "c_replace";
                }

                return value;
            }
        });
        box.line("a", "valueOne");
        box.line("b", "valueTwo");
        box.line("c", "valueThree");
        box.line("d", "valueFour");

        final String actual = this.getBox(box);
        final String expected = "\n" +
                                " + -----------TEST---------- + \n" +
                                " | a : a_replace             | \n" +
                                " | b : valueTwo              | \n" +
                                " | c : c_replace             | \n" +
                                " | d : valueFour             | \n" +
                                " + ------------------------- + \r\n";

        assertEquals("with a custom handler", expected, actual);
    }

    public void testEmpty() throws UnsupportedEncodingException {
        ConsoleBox box = this.basicBox(5);
        box.empty();
        box.empty();

        final String actual = this.getBox(box);
        final String expected = "\n" +
                                " + -TEST + \n" +
                                " +       + \n" +
                                " +       + \n" +
                                " + ----- + \r\n";

        assertEquals("with empty content", expected, actual);
    }
}