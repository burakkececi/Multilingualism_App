package entities;

import java.util.ArrayList;
import java.util.List;

public class League {

    private LanguageName languageName;
    private List<User> users;
    private List<User> bronzeLeague;
    private List<User> silverLeague;
    private List<User> goldLeague;
    private List<User> sapphireLeague;
    private List<User> rubyLeague;

    public League(LanguageName languageName) {
        this.languageName = languageName;
        createLeagues();
    }

    private void createLeagues() {
        users = new ArrayList<>();
        bronzeLeague = new ArrayList<>();
        silverLeague = new ArrayList<>();
        goldLeague = new ArrayList<>();
        sapphireLeague = new ArrayList<>();
        rubyLeague = new ArrayList<>();
    }

    public LanguageName getLanguageName() {
        return languageName;
    }

    public void addUser(User user) {
        users.add(user);
    }

    private User findTheUserWithHighestPoints() {
        User userWithHighestPoints = users.get(0);
        for (User user : users) {
            if (user.getTotalPoints() > userWithHighestPoints.getTotalPoints()) {
                userWithHighestPoints = user;
            }
        }
        return userWithHighestPoints;
    }

    private boolean checkForRubyLeague(User user) {
        return user.getStreakDays() > 30 && (user.getTotalPoints() > 5000 || user.getNumberOfSolvedUnits() > 10);
    }

    private boolean checkForSapphireLeague(User user) {
        return user.getStreakDays() > 7;
    }

    public void distributeUsersToLeagues() {
        for (int i = 0; i < 5; i++) {
            User user = findTheUserWithHighestPoints();
            if (checkForRubyLeague(user)) {
                rubyLeague.add(user);
            } else if (checkForSapphireLeague(user)) {
                sapphireLeague.add(user);
            } else {
                goldLeague.add(user);
            }
            users.remove(user);
        }

        for (int i = 0; i < 5; i++) {
            User user = findTheUserWithHighestPoints();
            goldLeague.add(user);
            users.remove(user);
        }

        for (int i = 0; i < 5; i++) {
            User user = findTheUserWithHighestPoints();
            silverLeague.add(user);
            users.remove(user);
        }

        // copy the remaining users to bronze league
        bronzeLeague.addAll(users);
        users.clear();
    }
}
