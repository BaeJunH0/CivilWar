package toyProject.demo.team.application;

import lombok.Getter;
import toyProject.demo.exception.CustomException;
import toyProject.demo.exception.errorCode.ScoreErrorCode;

import java.util.Locale;

@Getter
public enum Score {
    IRON(0),
    BRONZE(1),
    SILVER(2),
    GOLD(3),
    PLATINUM(4),
    EMERALD(5),
    DIAMOND(6),
    MASTER(7),
    GRANDMASTER(8),
    CHALLENGER(9),
    I(1),
    II(2),
    III(3),
    IV(4),
    V(5);

    private final int tierValue;

    Score(int tierValue) {
        this.tierValue = tierValue;
    }

    public static int fromName(String name) {
        for (Score tier : Score.values()) {
            if (tier.name().equals(name.toUpperCase(Locale.ROOT))) {
                return tier.getTierValue();
            }
        }
        throw CustomException.of(ScoreErrorCode.CALCULATING_ERROR);
    }
}
