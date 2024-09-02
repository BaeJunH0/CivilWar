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
    @ManyToOne
    private User user;
    @ManyToMany(mappedBy = "teams")
    private Set<Player> players = new HashSet<>();

    protected Team(){}

    public Team(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public void addPlayer(Player player){
        players.add(player);
    }
}
