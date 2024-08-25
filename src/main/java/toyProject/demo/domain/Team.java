package toyProject.demo.domain;

import jakarta.persistence.*;

import java.util.List;

@Table(name="Team")
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany
    private List<Player> players;

    protected Team(){}

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
