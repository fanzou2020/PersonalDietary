package team4.personaldietary.bean;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Craig Boucher, Tanveer, Fan Zou, Osman Momoh, Xin Ma
 *
 */
public class DbConnectionConfigBean {

    private StringProperty url;
    private StringProperty port;
    private StringProperty user;
    private StringProperty password;
    private StringProperty database;

    /**
     * Default Constructor
     */
    public DbConnectionConfigBean() {
        this("", "", "","","");
    }

    /**
     *
     * @param url
     * @param user
     * @param password
     */
    public DbConnectionConfigBean(final String url, final String port, final String database,final String user, final String password) {
        super();
        this.url = new SimpleStringProperty(url);
        this.port=new SimpleStringProperty(port);
        this.database=new SimpleStringProperty(database);
        this.user = new SimpleStringProperty(user);
        this.password = new SimpleStringProperty(password);
    }

    /**
     * @return the url
     */

    public final String getUrl() {
        return url.get();
    }
    public final String getFullUrl() {
        return "jdbc:mysql://"+url.get();
    }

    /**
     * @param url the url to set
     */
    public void setUrl(final String url) {
        this.url.set(url);
    }

    public StringProperty urlProperty(){
        return url;
    }

    /**
     * @return the user
     */
    public final String getUser() {
        return user.get();
    }

    /**
     * @param user the user to set
     */
    public void setUser(final String user) {
        this.user.set(user);
    }

    public StringProperty userProperty(){
        return user;
    }

    /**
     * @return the password
     */
    public final String getPassword() {
        return password.get();
    }

    /**
     * @param password the password to set
     */
    public void setPassword(final String password) {
        this.password.set(password);
    }

    public StringProperty passwordProperty(){
        return password;
    }
    /**
     * @return the port
     */
    public final String getPort() {
        return port.get();
    }

    /**
     * @param port the port to set
     */
    public void setPort(final String port) {
        this.port.set(port);
    }

    public StringProperty portProperty(){
        return port;
    }
    /**
     * @return the database
     */
    public final String getDatabase() {
        return database.get()+"?useSSL=false&serverTimezone=UTC&autoReconnect=true&autoReconnectForPools=true";
    }

    /**
     * @param database the database to set
     */
    public void setDatabase(final String database) {
        this.database.set(database);
    }

    public StringProperty databaseProperty(){
        return database;
    }

}
