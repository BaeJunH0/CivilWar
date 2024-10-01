package toyProject.demo.user.domain;

import jakarta.persistence.*;
import toyProject.demo.player.domain.Player;
import toyProject.demo.team.domain.Team;

import java.util.HashSet;
import java.util.Set;

@Table(name="users")
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
    @Column(name="admin")
    private boolean admin;

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
        this.admin = false;
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

    public boolean getAdmin(){
        return admin;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void addTeam(Team team){
        teams.add(team);
    }

    public void changeName(String nickname){
        this.nickname = nickname;
    }
}
