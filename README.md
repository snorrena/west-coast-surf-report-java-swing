# west-coast-surf-report-java-swing
west-coast-surf-report-java-swing is a standalone Java swing gui application

The West Vancouver Surf Report is a stand alone Java application port of the Android mobile application of the same name.

This application will run on any personal computer that has Java installed. It performs a similar function as the mobile app of monitor, record and report of ocean data from the Halibut Bank buoy in the Strait of Georgia near Vancouver.

When the app is running, it will collect and parse hourly data scrapped from a web RSS feed and run through an algorithmn that is used to predict surf conditions on the beaches of West Vancouver.

An alarm is triggered if the surf potential reaches 80%.

The graphical user interface for this program was designed using the Java Swing library framework and the data store is on the local hard drive in JSON (Javascript Object Notation) format.

For a Windows computer, I have wrapped the Java executable Jar in a Windows exe file using Lauch4j.
