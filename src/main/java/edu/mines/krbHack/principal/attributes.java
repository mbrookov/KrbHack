package edu.mines.krbHack.principal;

import java.util.Objects;

public class Attributes {
    String username;
    String password;
    Boolean allow_tix;
    String policy;

    public Attributes(String username, String password, Boolean allow_tix, String policy) {
        this.username = username;
        this.password = password;
        this.allow_tix = allow_tix;
        this.policy = policy;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAllow_tix() {
        return allow_tix;
    }

    public void setAllow_tix(Boolean allow_tix) {
        this.allow_tix = allow_tix;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attributes)) return false;
        Attributes that = (Attributes) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(allow_tix, that.allow_tix) && Objects.equals(policy, that.policy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, allow_tix, policy);
    }

    @Override
    public String toString() {
        return "attributes{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", allow_tix=" + allow_tix +
                ", policy='" + policy + '\'' +
                '}';
    }
}
