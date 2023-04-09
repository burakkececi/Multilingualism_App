package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class League {

    private final LanguageName languageName;
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


    public List<User> getTheBestUsersInLeague(String leagueName, int numberOfUsers) {
        List<User> users = new ArrayList<>();
        leagueName = leagueName.toUpperCase(Locale.UK);
        switch (leagueName) {
            case "BRONZE" -> {
                for (int i = 0; i < numberOfUsers; i++) {
                    users.add(bronzeLeague.get(i));
                }
            }
            case "SILVER" -> {
                for (int i = 0; i < numberOfUsers; i++) {
                    users.add(silverLeague.get(i));
                }
            }
            case "GOLD" -> {
                for (int i = 0; i < numberOfUsers; i++) {
                    users.add(goldLeague.get(i));
                }
            }
            case "SAPPHIRE" -> {
                for (int i = 0; i < numberOfUsers; i++) {
                    users.add(sapphireLeague.get(i));
                }
            }
            case "RUBY" -> {
                for (int i = 0; i < numberOfUsers; i++) {
                    users.add(rubyLeague.get(i));
                }
            }
        }
        return users;
    }

}
