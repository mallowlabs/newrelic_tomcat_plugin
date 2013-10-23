New Relic Tomcat Plugin
========================================

Features
-------------
This plugin support below metrics.

* Memory Total Usage
* Memory PS Eden Space Usage
* Memory PS Old Gen Usage
* Memory PS Survivor Space Usage
* Memory Code Cache Usage
* Thread Current Busy Thread Count

You can use this with New Relic LITE plan :P

Prerequisites
-------------

1. A New Relic account. Signup for a free account at http://newrelic.com
2. A configured Java Developer Kit (JDK) - version 1.6 or better
3. The Ant build tool - version 1.8 or better
4. Git
5. Apache Tomcat 7.0.33 or later

Building the Agent
----------------------------------

1. From your shell run: `ant` to build the Agent Plugin
2. A tar archive will be placed in the `dist` folder with the pattern `tomcat-X.Y.Z.tar.gz`. The tar file will include a tomcat jar and several configuration files. This is an example of a distributable file for a plugin.
3. Extract the tar file to a location where you want to run the plugin agent from.

Starting the Java plugin agent
----------------------------------

Setup tomcat-users.xml like:

    <role rolename="manager-status"/>
    <user username="newrelic" password="newrelic" roles="manager-status"/>

1. Copy `config/template_newrelic.properties` to `config/newrelic.properties`
2. Edit `config/newrelic.properties` and replace `YOUR_LICENSE_KEY_HERE` with your New Relic license key
3. Edit `config/org.codefirst.newrelic.plugins.tomcat.json`
4. From your shell run: `java -jar tomcat-*.jar`
5. Look for error-free startup messages on stdout, such as "com.newrelic.metrics.publish.Runner setupAndRun" and "INFO: New Relic monitor started."
6. Wait a few minutes for New Relic to start processing the data sent from your agent.
7. Sign in to your New Relic account.
8. From the New Relic menu bar, look for the name of the agent that you just created (the **some_plugin_name** part of your GUID).
9. To view your plugin's summary page, click the plugin's name ("example").

