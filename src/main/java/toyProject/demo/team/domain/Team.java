package toyProject.demo.team.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "owner")
    private String  owner;

    private Team(String name, String  owner) {
        this.name = name;
        this.owner = owner;
    }

    public static Team of(String name, String owner){
        return new Team(name, owner);
    }
}
