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
}
