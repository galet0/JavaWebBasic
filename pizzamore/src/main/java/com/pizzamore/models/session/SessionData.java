package com.pizzamore.models.session;


import javax.persistence.*;

@Entity
@Table(name = "session_data")
public class SessionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "key_data")
    private String key;

    @Column(name = "value_data")
    private String value;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session usersSession;

    public SessionData() {
    }

    public SessionData(String key, String value, Session usersSession) {
        this.setKey(key);
        this.setValue(value);
        this.setUsersSession(usersSession);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Session getUsersSession() {
        return usersSession;
    }

    public void setUsersSession(Session usersSession) {
        this.usersSession = usersSession;
    }
}
