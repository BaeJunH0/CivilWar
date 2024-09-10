package toyProject.demo.domain;

import jakarta.persistence.*;
import toyProject.demo.DTO.player.PlayerRequest;

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
    @ManyToMany(mappedBy = "users")
    private Set<User> users = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "player_team",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams = new HashSet<>();

    protected Player(){}

    public Player(int level, String nickname, String freeTier, String soloTier) {
        this.level = level;
        this.nickname = nickname;
        this.freeTier = freeTier;
        this.soloTier = soloTier;
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

    public Set<User> getUsers() {
        return users;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void joinTeam(Team team){
        teams.add(team);
    }

    public void joinUser(User user){
        users.add(user);
    }

    public void update(PlayerRequest playerRequest){
        this.level = playerRequest.getLevel();
        this.freeTier = playerRequest.getFreeTier();
        this.soloTier = playerRequest.getSoloTier();
        this.nickname = playerRequest.getNickname();
    }
}
