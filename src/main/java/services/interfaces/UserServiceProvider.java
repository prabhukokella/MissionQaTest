package services.interfaces;

public interface UserServiceProvider {

    void getFirstPageUsers();
    void getAllUsers();

    int getTotalUsers();
    int getUniqueUserIdsCount();

    void getUser(int id);
    String getField(String key);

    int getStatusCode();

    void createUser(String name, String job);
    String getResponseField(String key);

    void login(String email, String password);
    String getResponseBody();

    void getDelayedUsers();
    boolean areUserIdsUnique();
}