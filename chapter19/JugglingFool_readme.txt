MakeApplet generates an Applet corresponding to a BeanBox layout.  The
data and classes needed for this Applet are packaged into a JAR file.
The Applet itself is a Bean and it can be read back into the BeanBox
if desired.

At construction time, the generated Applet uses serialization data only if
it contains beans with hidden-state. Note, other builders may use different
techniques to reconstitute a compound bean.

This directory contains the following files:

JugglingFool.html
	A test html file for displaying the applet.  It requires
	a JDK1.1-compliant browser.  The APPLET tag references
	the JAR files described next

JugglingFool.jar
	This JAR file packages .class files and serialized data, if any,
	so it can be referenced by the HTML file.  This JAR file
	depends on the JAR files listed next.

support.jar
	This JAR file contains a few support classes that are
	used by the generated classes.

buttons.jar
juggler.jar
	These JAR file(s) describe the Beans that were instantiated
	in the BeanBox and packaged into MyApplet.

JugglingFool_files
	This directory contains source files, data, if any, and Makefiles.
	It is possible to do small adjustments to the generated sources
	and create a new JAR file.


***************

BROWSER SUPPORT

The generated applet requires full JDK1.1 support.  The appletviewer
tool included in the JDK1.1 distributions can be used.  Alternatively
you can use the HotJava browser.

The latest versions of Navigator and Internet Explorer only
partially support JDK1.1.

The failures in IE4.0 include lack of support for the
Class.getResource() call.  In particular, this means that the Juggler
bean (in juggler.jar) will not be able to show its image.  IE4.0 does
support getResourceAsStream() which is used by the generated Applet
to acquire serialized information on its beans.  Note that an Image
can be created from an InputStream by first creating a byte array
and then using Toolkit.createImage().

Navigator 4.04 generates a security exception from
getResourceAsStream() invocations.  We expect this to be resolved
promptly

HotJava 1.1, and 1.1.1 make a distinction in security checks
between the URLs "file:/<dir>" and "file:///<dir>"; an applet
denoted by the first one will work but one using the second will not:
it will raise a security exception while doing getResourceAsStream()
