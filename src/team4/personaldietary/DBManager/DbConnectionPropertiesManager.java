package team4.personaldietary.DBManager;

import team4.personaldietary.bean.DbConnectionConfigBean;

import static java.nio.file.Files.newInputStream;
import static java.nio.file.Files.newOutputStream;
import static java.nio.file.Paths.get;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;


import static java.nio.file.Paths.get;

public class DbConnectionPropertiesManager {
    /**
     * Returns a DbConnectionConfigBean object with the contents of the properties file
     *
     * @param path
     *            Must exist, will not be created
     * @param propFileName
     *            Name of the properties file
     * @return The bean loaded with the properties
     * @throws IOException
     */
    public final DbConnectionConfigBean loadTextProperties(final String path, final String propFileName) throws IOException {
        Properties prop = new Properties();

        Path txtFile = get(path, propFileName + ".properties");

        DbConnectionConfigBean dcb = new DbConnectionConfigBean();

        // File must exist
        if (Files.exists(txtFile)) {
            try (InputStream propFileStream = newInputStream(txtFile);) {
                prop.load(propFileStream);
            }
            dcb.setUrl(prop.getProperty("url"));
            dcb.setPort(prop.getProperty("port"));
            dcb.setDatabase(prop.getProperty("database"));
            dcb.setUser(prop.getProperty("user"));
            dcb.setPassword(prop.getProperty("password"));
        }
        return dcb;
    }

    /**
     * Creates a plain text properties file based on the parameters
     *
     * @param path
     *            Must exist, will not be created
     * @param propFileName
     *            Name of the properties file
     * @param dcb
     *            The bean to store into the properties
     * @throws IOException
     */
    public final void writeTextProperties(final String path, final String propFileName, final DbConnectionConfigBean dcb) throws IOException {

        Properties prop = new Properties();

        prop.setProperty("url", dcb.getUrl());
        prop.setProperty("port", dcb.getPort());
        prop.setProperty("database", dcb.getDatabase());
        prop.setProperty("user", dcb.getUser());
        prop.setProperty("password", dcb.getPassword());

        Path txtFile = get(path, propFileName + ".properties");

        // Creates the file or if file exists it is truncated to length of zero
        // before writing
        try (OutputStream propFileStream = newOutputStream(txtFile)) {
            prop.store(propFileStream, "SMTP Properties");
        }
    }

    /**
     * Returns a DbConnectionConfigBean object with the contents of the properties file
     * that is in an XML format
     *
     * @param path
     *            Must exist, will not be created. Empty string means root of
     *            program folder
     * @param propFileName
     *            Name of the properties file
     * @return The bean loaded with the properties
     * @throws IOException
     */
    public final DbConnectionConfigBean loadXmlProperties(final String path, final String propFileName) throws IOException {

        Properties prop = new Properties();

        // The path of the XML file
        Path xmlFile = get(path, propFileName + ".xml");

        DbConnectionConfigBean dcb = new DbConnectionConfigBean();

        // File must exist
        if (Files.exists(xmlFile)) {
            try (InputStream propFileStream = newInputStream(xmlFile);) {
                prop.loadFromXML(propFileStream);
            }
            dcb.setUrl(prop.getProperty("url"));
            dcb.setPort(prop.getProperty("port"));
            dcb.setDatabase(prop.getProperty("database"));
            dcb.setUser(prop.getProperty("user"));
            dcb.setPassword(prop.getProperty("password"));
        }
        return dcb;
    }

    /**
     * Creates an XML properties file based on the parameters
     *
     * @param path
     *            Must exist, will not be created
     * @param propFileName
     *            Name of the properties file. Empty string means root of
     *            program folder
     * @param dcb
     *            The bean to store into the properties
     * @throws IOException
     */
    public final void writeXmlProperties(final String path, final String propFileName, final DbConnectionConfigBean dcb) throws IOException {

        Properties prop = new Properties();

        prop.setProperty("url", dcb.getUrl());
        prop.setProperty("port", dcb.getPort());
        prop.setProperty("database", dcb.getDatabase());
        prop.setProperty("user", dcb.getUser());
        prop.setProperty("password", dcb.getPassword());


        Path xmlFile = get(path, propFileName + ".xml");

        // Creates the file or if file exists it is truncated to length of zero
        // before writing
        try (OutputStream propFileStream = newOutputStream(xmlFile)) {
            prop.storeToXML(propFileStream, "XML SMTP Properties");
        }
    }

    /**
     * Retrieve the properties file. This syntax rather than normal File I/O is
     * employed because the properties file is inside the jar. The technique
     * shown here will work in both Java SE and Java EE environments. A similar
     * technique is used for loading images.
     *
     * In a Maven project all configuration files, images and other files go
     * into src/main/resources. The files and folders placed there are moved to
     * the root of the project when it is built.
     *
     * @param propertiesFileName
     *            : Name of the properties file
     * @throws IOException
     *             : Error while reading the file
     * @throws NullPointerException
     *             : File not found
     * @return The bean loaded with the properties
     */
    public final DbConnectionConfigBean loadJarTextProperties(final String propertiesFileName) throws IOException, NullPointerException {

        Properties prop = new Properties();
        DbConnectionConfigBean dcb = new DbConnectionConfigBean();

        // There is no exists method for files in a jar so we try to get the
        // resource and if its not there it returns a null
        if (this.getClass().getResource("/" + propertiesFileName) != null) {
            // Assumes that the properties file is in the root of the
            // project/jar.
            // Include a path from the root if required.
            try (InputStream stream = this.getClass().getResourceAsStream("/" + propertiesFileName);) {
                prop.load(stream);
            }
            dcb.setUrl(prop.getProperty("url"));
            dcb.setPort(prop.getProperty("port"));
            dcb.setDatabase(prop.getProperty("database"));
            dcb.setUser(prop.getProperty("user"));
            dcb.setPassword(prop.getProperty("password"));
        }
        return dcb;
    }
}
