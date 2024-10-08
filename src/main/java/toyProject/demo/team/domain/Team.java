package toyProject.demo.team.domain;

import jakarta.persistence.*;
import toyProject.demo.user.domain.User;
import toyProject.demo.player.domain.Player;

import java.util.HashSet;
import java.util.Set;

@Table(name="teams")
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @ManyToMany(mappedBy = "teams")
    private Set<Player> players = new HashSet<>();
    @ManyToMany(mappedBy = "teams")
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

    public void changeName(String name){
        this.name = name;
    }
}
