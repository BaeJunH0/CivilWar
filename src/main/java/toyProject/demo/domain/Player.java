package toyProject.demo.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Table(name="Player")
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="level", nullable = false)
    private int level;
    @Column(name="nickname", nullable = false)
    private String nickname;
    @Column(name="freeTier")
    private String freeTier;
    @Column(name="soloTier")
    private String soloTier;
    @ManyToOne
    private User user;
    @ManyToMany
    @JoinTable(
            name = "player_team",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams = new HashSet<>();

    protected Player(){}

    public Player(int level, String nickname, String freeTier, String soloTier, User user) {
        this.level = level;
        this.nickname = nickname;
        this.freeTier = freeTier;
        this.soloTier = soloTier;
        this.user = user;
    }

    public int getLevel() {
        return level;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFreeTier() {
        return freeTier;
    }

    public String getSoloTier() {
        return soloTier;
    }

    public User getUser(){
        return user;
    }

    public void joinTeam(Team team){
        teams.add(team);
    }
}
