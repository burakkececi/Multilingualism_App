package business.mappers;

import entities.User;

public class UserMapper implements CSVMapper<User> {

    @Override
    public User map(String[] data) {
        return new User(data[0], data[1]);
    }

}
