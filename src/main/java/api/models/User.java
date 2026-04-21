package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// ✅ ignore unknown fields safely
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private final String name;
    private final String job;
    private final String id;
    private final String createdAt;

    // ✅ Constructor for request (create user)
    public User(String name, String job) {
        this.name = name;
        this.job = job;
        this.id = null;
        this.createdAt = null;
    }

    // ✅ Constructor for response (Jackson will use this)
    public User(String name, String job, String id, String createdAt) {
        this.name = name;
        this.job = job;
        this.id = id;
        this.createdAt = createdAt;
    }

    // ---------- GETTERS ONLY (IMMUTABLE) ----------
    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}