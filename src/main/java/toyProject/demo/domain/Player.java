package toyProject.demo.domain;

import jakarta.persistence.*;

@Table(name="Player")
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="level")
    private int level;
    @Column(name="nickname")
    private String nickname;
    @Column(name="freeTier")
    private String freeTier;
    @Column(name="soloTier")
    private String soloTier;

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
}
