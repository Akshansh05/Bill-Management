package Manager;

import Model.User.DeviceContext;
import Model.User.User;
import Model.User.UserContext;

import java.util.HashMap;
import java.util.UUID;

public class UserManager {
    private final HashMap<String, User> users;

    public UserManager() {
        this.users = new HashMap<>();
    }

    public User addUser(String name) {
        User user = new User(UUID.randomUUID().toString(), name, new UserContext(new DeviceContext(UUID.randomUUID().toString(), true)));
        users.put(user.getId(), user);
        return user;
    }

    public void removeUser(String id) {
        if (users.containsKey(id)) {
            users.remove(id);
        }
    }

    public User getUser(String id) {
        if (users.containsKey(id)) {
           return  users.get(id);
        }
        return null;
    }

    public void updateDeviceConfig(User user, Boolean isUpiEnabled) {
        if (users.containsKey(user.getId())) {
            User updateUser = users.get(user.getId());
            updateUser.getUserContext().getDeviceContext().setUpiEnabled(isUpiEnabled);
        }
    }
}
