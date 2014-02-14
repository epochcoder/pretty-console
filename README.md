pretty-console
=============

A simple library for writing various key-value type information to the console or other mediums, such as `<pre>`

###### Example

<pre>
 + -----------------------pretty-console----------------------- + 
 |                                     #                        | 
 |                                     #                        | 
 |                                     ##                       | 
 |                                      #                       | 
 |                       ###            ##                      | 
 |                       ###            ##                      | 
 |                       ###            ##                      | 
 |                                      ##                      | 
 |                                      ##                      | 
 |                             ######   ##                      | 
 |                             ######   ##                      | 
 |                             ######   ##                      | 
 |                                      ##                      | 
 |                       ###            ##                      | 
 |                       ###            ##                      | 
 |                       ###            #                       | 
 |                                      #                       | 
 |                                     ##                       | 
 |                                     #                        | 
 +                                                              + 
 + ----Now that you are smiling, here is some information...--- + 
 +                                                              + 
 | Why? : pretty-console was made to display diagnostic         | 
 |        information in java log files/emails and on           | 
 |        web container startup to show                         | 
 |        system properties and application properties.         | 
 | Enjoy : Please...                                            | 
 + ------------------------------------------------------------ + 
</pre>

###### Usage

It comes standard with a JVM Key Handler to format JVM type system properties correcty, it can be added to any `ConsoleBox` instance by using

    ConsoleBox box = new ConsoleBox(150);
    box.handler(new JvmKeyHandler());

Here is a simple example of writing System properties to the console:

	ConsoleBox box = new ConsoleBox(150);
    box.handler(new JvmKeyHandler());

    Enumeration<?> names = System.getProperties().propertyNames();
    box.title("JVM Information");

    while (names.hasMoreElements()) {
        String prop = (String) names.nextElement();
        box.line(prop, System.getProperty(prop));
    }

    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    box.build(System.out);