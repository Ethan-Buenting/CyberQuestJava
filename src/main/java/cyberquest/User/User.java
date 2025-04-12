package cyberquest.User;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // We'll store module completion as a string of 0s and 1s (e.g., "10011001")
    @Column(length = 8)
    private String moduleProgress;

    public User() {
        // Initialize progress as all incomplete: "00000000"
        this.moduleProgress = "00000000";
    }

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.moduleProgress = "00000000";
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getModuleProgress() {
        return moduleProgress;
    }

    public void setModuleProgress(String moduleProgress) {
        this.moduleProgress = moduleProgress;
    }

    // Helper method to mark a module as complete (index 0–7)
    public void completeModule(int index) {
        if (index < 0 || index > 7) return;
        char[] progressArray = this.moduleProgress.toCharArray();
        progressArray[index] = '1';
        this.moduleProgress = new String(progressArray);
    }

    // Check if a module is completed
    public boolean isModuleComplete(int index) {
        if (index < 0 || index > 7) return false;
        return this.moduleProgress.charAt(index) == '1';
    }

    @Override
    public String toString() {
        return "cyberquest.java.cyberquest.User{id=" + id + ", name='" + name + "', username='" + username + "', progress=" + moduleProgress + "}";
    }
}
