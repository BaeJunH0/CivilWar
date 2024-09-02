package toyProject.demo.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Table(name="Team")
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @ManyToMany(mappedBy = "teams")
    private Set<Player> players = new HashSet<>();
    @ManyToMany(mappedBy = "users")
    private Set<User> users = new HashSet<>();

    protected Team(){}

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void joinUser(User user){
        users.add(user);
    }
}
