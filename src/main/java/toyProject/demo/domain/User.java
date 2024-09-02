package toyProject.demo.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Table(name="User")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="nickname")
    private String nickname;
    @ManyToMany
    @JoinTable(
            name = "user_player",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private Set<Player> players = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "user_team",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams = new HashSet<>();

    protected User(){}

    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void addTeam(Team team){
        teams.add(team);
    }
}
