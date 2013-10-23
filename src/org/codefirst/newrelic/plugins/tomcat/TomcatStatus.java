package org.codefirst.newrelic.plugins.tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.codefirst.newrelic.plugins.tomcat.models.Memory;
import org.codefirst.newrelic.plugins.tomcat.models.MemoryPool;
import org.codefirst.newrelic.plugins.tomcat.models.ThreadInfo;
import org.codefirst.newrelic.plugins.tomcat.models.TomcatStatusResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TomcatStatus {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private String host;
    private int port;
    private String request;
    private String user;
    private String password;
    private int timeout;
    private String connector;

    public TomcatStatus(String host, int port, String request, String user, String password, int timeout, String connector) {
        super();
        this.host = host;
        this.port = port;
        this.request = request;
        this.user = user;
        this.password = password;
        this.timeout = timeout;
        this.connector = connector;
    }

    private String buildUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        // sb.append(user);
        // sb.append(":");
        // sb.append(password);
        // sb.append("@");
        sb.append(host);
        sb.append(":");
        sb.append(port);
        sb.append(request);
        return sb.toString();
    }

    public TomcatStatusResult fetch() {
        TomcatStatusResult result = new TomcatStatusResult();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            Authenticator.setDefault(new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password.toCharArray());
                }
            });

            DocumentBuilder db = dbf.newDocumentBuilder();
            URL url = new URL(buildUrl());

            URLConnection connection = url.openConnection();
            connection.setReadTimeout(timeout * 1000);
            Object content = connection.getContent();

            if (content instanceof InputStream) {
                Document doc = db.parse((InputStream) content);

                // total memory
                result.setMemory(new Memory(doc.getElementsByTagName("memory").item(0)));

                // memory pool
                NodeList memorypools = doc.getElementsByTagName("memorypool");
                for (int i = 0; i < memorypools.getLength(); i++) {
                    result.addMemoryPool(new MemoryPool(memorypools.item(i)));
                }

                // threads
                NodeList connectors = doc.getElementsByTagName("connector");
                for (int i = 0; i < connectors.getLength(); i++) {
                    Node connectorNode = connectors.item(i);
                    String connectorName = connectorNode.getAttributes().getNamedItem("name").getTextContent();
                    if (this.connector.equals(connectorName)) {
                        Node threadInfoNode = connectorNode.getFirstChild();
                        result.setThreadInfo(new ThreadInfo(threadInfoNode));
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            LOGGER.severe(e.getLocalizedMessage());
            e.printStackTrace();
        } catch (MalformedURLException e) {
            LOGGER.severe(e.getLocalizedMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.severe(e.getLocalizedMessage());
            e.printStackTrace();
        } catch (SAXException e) {
            LOGGER.severe(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return result;
    }
}
