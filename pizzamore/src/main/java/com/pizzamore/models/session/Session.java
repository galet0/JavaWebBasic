package com.pizzamore.models.session;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToMany(mappedBy = "usersSession", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SessionData> usersSessionData;

    public Session() {
        this.setUsersSessionData(new HashSet<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<SessionData> getUsersSessionData() {
        return usersSessionData;
    }

    public void setUsersSessionData(Set<SessionData> sessionData) {
        this.usersSessionData = sessionData;
    }

    public void addSessionData(String key, String value){
        SessionData sessionData = new SessionData(key, value, this);
        this.getUsersSessionData().add(sessionData);
    }
}
