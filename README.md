pretty-console
=============

A simple library for writing various key-value type information to the console or other mediums, such as `<pre>`

###### Example

<pre>
 + -----------------------pretty-console----------------------- + 
 |                                                              | 
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
    
Result:

<pre>
 + -------------------------------------------------------------------------JVM Information------------------------------------------------------------------------ + 
 | java.vendor : Oracle Corporation                                                                                                                                 | 
 | sun.java.launcher : SUN_STANDARD                                                                                                                                 | 
 | catalina.base : C:\Users\Willie\.tomcat\7.0.34                                                                                                                   | 
 | sun.management.compiler : HotSpot 64-Bit Tiered Compilers                                                                                                        | 
 | catalina.useNaming : true                                                                                                                                        | 
 | os.name : Windows 7                                                                                                                                              | 
 | sun.boot.class.path : [1] C:\Program Files\Apache Software Foundation\Apache Tomcat 7.0.34\endorsed\webservices-api.jar                                          | 
 |                       [2] C:\Program Files\Java\jdk1.7.0_25\jre\lib\resources.jar                                                                                | 
 |                       [3] C:\Program Files\Java\jdk1.7.0_25\jre\lib\rt.jar                                                                                       | 
 |                       [4] C:\Program Files\Java\jdk1.7.0_25\jre\lib\sunrsasign.jar                                                                               | 
 |                       [5] C:\Program Files\Java\jdk1.7.0_25\jre\lib\jsse.jar                                                                                     | 
 |                       [6] C:\Program Files\Java\jdk1.7.0_25\jre\lib\jce.jar                                                                                      | 
 |                       [7] C:\Program Files\Java\jdk1.7.0_25\jre\lib\charsets.jar                                                                                 | 
 |                       [8] C:\Program Files\Java\jdk1.7.0_25\jre\lib\jfr.jar                                                                                      | 
 |                       [9] C:\Program Files\Java\jdk1.7.0_25\jre\classes                                                                                          | 
 | java.util.logging.config.file : C:\Users\Willie\.tomcat\7.0.34\conf\logging.properties                                                                           | 
 | sun.desktop : windows                                                                                                                                            | 
 | java.vm.specification.vendor : Oracle Corporation                                                                                                                | 
 | java.runtime.version : 1.7.0_25-b17                                                                                                                              | 
 | user.name : Willie                                                                                                                                               | 
 | shared.loader :                                                                                                                                                  | 
 | tomcat.util.buf.StringCache.byte.enabled : true                                                                                                                  | 
 | user.language : en                                                                                                                                               | 
 | java.naming.factory.initial : org.apache.naming.java.javaURLContextFactory                                                                                       | 
 | sun.boot.library.path : C:\Program Files\Java\jdk1.7.0_25\jre\bin                                                                                                | 
 | java.version : 1.7.0_25                                                                                                                                          | 
 | java.util.logging.manager : org.apache.juli.ClassLoaderLogManager                                                                                                | 
 | user.timezone : Africa/Harare                                                                                                                                    | 
 | sun.arch.data.model : 64                                                                                                                                         | 
 | http.nonProxyHosts : localhost|127.0.0.1|WILLIE-LT                                                                                                               | 
 | java.endorsed.dirs : C:\Program Files\Apache Software Foundation\Apache Tomcat 7.0.34\endorsed                                                                   | 
 | sun.cpu.isalist : amd64                                                                                                                                          | 
 | sun.jnu.encoding : Cp1252                                                                                                                                        | 
 | file.encoding.pkg : sun.io                                                                                                                                       | 
 | package.access : sun.,org.apache.catalina.,org.apache.coyote.,org.apache.tomcat.,org.apache.jasper.                                                              | 
 | file.separator : \                                                                                                                                               | 
 | java.specification.name : Java Platform API Specification                                                                                                        | 
 | java.class.version : 51.0                                                                                                                                        | 
 | user.country : US                                                                                                                                                | 
 | java.home : C:\Program Files\Java\jdk1.7.0_25\jre                                                                                                                | 
 | java.vm.info : mixed mode                                                                                                                                        | 
 | os.version : 6.1                                                                                                                                                 | 
 | path.separator : ;                                                                                                                                               | 
 | java.vm.version : 23.25-b01                                                                                                                                      | 
 | user.variant :                                                                                                                                                   | 
 | sun.awt.enableExtraMouseButtons : true                                                                                                                           | 
 | java.awt.printerjob : sun.awt.windows.WPrinterJob                                                                                                                | 
 | sun.io.unicode.encoding : UnicodeLittle                                                                                                                          | 
 | tomcat.util.scan.DefaultJarScanner.jarsToSkip : bootstrap.jar,commons-daemon.jar,tomcat-juli.jar,annotations-api.jar,el-api.jar,jsp-api.jar,servlet-api.jar,cata | 
 |                                                 lina.jar,catalina-ant.jar,catalina-ha.jar,catalina-tribes.jar,jasper.jar,jasper-el.jar,ecj-*.jar,tomcat-api.jar, | 
 |                                                 tomcat-util.jar,tomcat-coyote.jar,tomcat-dbcp.jar,tomcat-jni.jar,tomcat-spdy.jar,tomcat-i18n-en.jar,tomcat-i18n- | 
 |                                                 es.jar,tomcat-i18n-fr.jar,tomcat-i18n-ja.jar,tomcat-juli-adapters.jar,catalina-jmx-remote.jar,catalina-ws.jar,to | 
 |                                                 mcat-jdbc.jar,commons-beanutils*.jar,commons-codec*.jar,commons-collections*.jar,commons-dbcp*.jar,commons-diges | 
 |                                                 ter*.jar,commons-fileupload*.jar,commons-httpclient*.jar,commons-io*.jar,commons-lang*.jar,commons-logging*.jar, | 
 |                                                 commons-math*.jar,commons-pool*.jar,jstl.jar,geronimo-spec-jaxrpc*.jar,wsdl4j*.jar,ant.jar,ant-junit*.jar,aspect | 
 |                                                 j*.jar,jmx.jar,h2*.jar,hibernate*.jar,httpclient*.jar,jmx-tools.jar,jta*.jar,log4j*.jar,mail*.jar,slf4j*.jar,xer | 
 |                                                 cesImpl.jar,xmlParserAPIs.jar,xml-apis.jar,access-bridge.jar,access-bridge-64.jar,dnsns.jar,jaccess.jar,ldapsec. | 
 |                                                 jar,localedata.jar,sunjce_provider.jar,sunmscapi.jar,sunpkcs11.jar,jhall.jar,tools.jar,sunec.jar,zipfs.jar,gnome | 
 |                                                 -java-bridge.jar,pulse-java.jar,apple_provider.jar,AppleScriptEngine.jar,CoreAudio.jar,dns_sd.jar,j3daudio.jar,j | 
 |                                                 3dcore.jar,j3dutils.jar,jai_core.jar,jai_codec.jar,mlibwrapper_jai.jar,MRJToolkit.jar,vecmath.jar,junit.jar,juni | 
 |                                                 t-*.jar,ant-launcher.jar                                                                                         | 
 | awt.toolkit : sun.awt.windows.WToolkit                                                                                                                           | 
 | package.definition : sun.,java.,org.apache.catalina.,org.apache.coyote.,org.apache.tomcat.,org.apache.jasper.                                                    | 
 | user.script :                                                                                                                                                    | 
 | java.naming.factory.url.pkgs : org.apache.naming                                                                                                                 | 
 | user.home : C:\Users\Willie                                                                                                                                      | 
 | org.apache.catalina.startup.ContextConfig.jarsToSkip :                                                                                                           | 
 | java.specification.vendor : Oracle Corporation                                                                                                                   | 
 | java.library.path : [1] C:\Program Files\Java\jdk1.7.0_25\bin                                                                                                    | 
 |                     [2] C:\Windows\Sun\Java\bin                                                                                                                  | 
 |                     [3] C:\Windows\system32                                                                                                                      | 
 |                     [4] C:\Windows                                                                                                                               | 
 |                     [5] C:\Program Files (x86)\NVIDIA Corporation\PhysX\Common                                                                                   | 
 |                     [6] C:\Program Files (x86)\Python\33\                                                                                                        | 
 |                     [7] C:\Program Files (x86)\CollabNet Subversion Client                                                                                       | 
 |                     [8] C:\Windows\system32                                                                                                                      | 
 |                     [9] C:\Windows                                                                                                                               | 
 |                     [10] C:\Windows\System32\Wbem                                                                                                                | 
 |                     [11] C:\Windows\System32\WindowsPowerShell\v1.0\                                                                                             | 
 |                     [12] C:\Program Files\Intel\WiFi\bin\                                                                                                        | 
 |                     [13] C:\Program Files\Common Files\Intel\WirelessCommon\                                                                                     | 
 |                     [14] C:\Program Files\TortoiseSVN\bin                                                                                                        | 
 |                     [15] C:\Program Files (x86)\Microsoft SQL Server\110\Tools\Binn\                                                                             | 
 |                     [16] C:\Program Files\Microsoft SQL Server\110\Tools\Binn\                                                                                   | 
 |                     [17] C:\Program Files\Microsoft SQL Server\110\DTS\Binn\                                                                                     | 
 |                     [18] C:\Program Files (x86)\Microsoft SQL Server\110\Tools\Binn\ManagementStudio\                                                            | 
 |                     [19] C:\Program Files (x86)\Microsoft Visual Studio 10.0\Common7\IDE\PrivateAssemblies\                                                      | 
 |                     [20] C:\Program Files (x86)\Microsoft SQL Server\110\DTS\Binn\                                                                               | 
 |                     [21] C:\Program Files\nodejs\                                                                                                                | 
 |                     [22] E:\software\Ant\1.8.2\bin                                                                                                               | 
 |                     [23] E:\software\andriod-sdk\tools                                                                                                           | 
 |                     [24] E:\software\PhantomJS                                                                                                                   | 
 |                     [25] E:\software\cURL                                                                                                                        | 
 |                     [26] C:\Program Files\Java\jdk1.7.0_25\bin                                                                                                   | 
 |                     [27] C:\Program Files (x86)\Git\cmd                                                                                                          | 
 |                     [28] C:\Program Files\TortoiseGit\bin                                                                                                        | 
 |                     [29] C:\Program Files (x86)\MySQL\MySQL Utilities 1.3.5\                                                                                     | 
 |                     [30] E:\software\OpenSSL-Win32\bin                                                                                                           | 
 |                     [31] C:\Program Files (x86)\QuickTime\QTSystem\                                                                                              | 
 |                     [32] E:\software\Ivy\apache-ivy-2.3.0                                                                                                        | 
 |                     [33] E:\software\Gradle\gradle-1.10\bin                                                                                                      | 
 |                     [34] E:\software\Maven\apache-maven-3.1.1\bin                                                                                                | 
 |                     [35] C:\Users\Willie\AppData\Roaming\npm                                                                                                     | 
 |                     [36] .                                                                                                                                       | 
 | java.vendor.url : http://java.oracle.com/                                                                                                                        | 
 | org.apache.catalina.startup.TldConfig.jarsToSkip :                                                                                                               | 
 | java.vm.vendor : Oracle Corporation                                                                                                                              | 
 | common.loader : ${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar                                                  | 
 | java.runtime.name : Java(TM) SE Runtime Environment                                                                                                              | 
 | sun.java.command : org.apache.catalina.startup.Bootstrap start                                                                                                   | 
 | java.class.path : [1] C:\Program Files\Apache Software Foundation\Apache Tomcat 7.0.34\bin\bootstrap.jar                                                         | 
 |                   [2] C:\Program Files\Apache Software Foundation\Apache Tomcat 7.0.34\bin\tomcat-juli.jar                                                       | 
 | java.vm.specification.name : Java Virtual Machine Specification                                                                                                  | 
 | java.vm.specification.version : 1.7                                                                                                                              | 
 | catalina.home : C:\Program Files\Apache Software Foundation\Apache Tomcat 7.0.34                                                                                 | 
 | sun.cpu.endian : little                                                                                                                                          | 
 | sun.os.patch.level : Service Pack 1                                                                                                                              | 
 | java.io.tmpdir : C:\Users\Willie\.tomcat\7.0.34\temp                                                                                                             | 
 | java.vendor.url.bug : http://bugreport.sun.com/bugreport/                                                                                                        | 
 | server.loader :                                                                                                                                                  | 
 | os.arch : amd64                                                                                                                                                  | 
 | java.awt.graphicsenv : sun.awt.Win32GraphicsEnvironment                                                                                                          | 
 | java.ext.dirs : C:\Program Files\Java\jdk1.7.0_25\jre\lib\ext;C:\Windows\Sun\Java\lib\ext                                                                        | 
 | user.dir : C:\Program Files\Apache Software Foundation\Apache Tomcat 7.0.34\bin                                                                                  | 
 | line.separator :                                                                                                                                                 | 
 | java.vm.name : Java HotSpot(TM) 64-Bit Server VM                                                                                                                 | 
 | file.encoding : Cp1252                                                                                                                                           | 
 | java.specification.version : 1.7                                                                                                                                 | 
 + ---------------------------------------------------------------------------------------------------------------------------------------------------------------- + 
</pre>
