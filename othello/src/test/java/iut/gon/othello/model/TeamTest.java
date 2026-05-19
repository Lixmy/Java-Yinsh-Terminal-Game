package iut.gon.othello.model;

import org.junit.jupiter.api.Test;

import iut.gon.othello.model.Team;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    @Test
    @DisplayName("Team possède une valeur WHITE")
    void teamWhiteExists() {
        assertDoesNotThrow(() -> Team.valueOf("WHITE"));
    }

    @Test
    @DisplayName("Team possède une valeur BLACK")
    void teamBlackExists() {
        assertDoesNotThrow(() -> Team.valueOf("BLACK"));
    }

    @Test
    @DisplayName("WHITE et BLACK sont différents")
    void teamsAreDifferent() {
        assertNotEquals(Team.WHITE, Team.BLACK);
    }

    @Test
    @DisplayName("Team n'a que deux valeurs")
    void teamHasTwoValues() {
        assertEquals(2, Team.values().length);
    }

    @Test
    @DisplayName("opposite() de WHITE est BLACK")
    void oppositeWhiteIsBlack() {
        assertEquals(Team.BLACK, Team.WHITE.other());
    }

    @Test
    @DisplayName("opposite() de BLACK est WHITE")
    void oppositeBlackIsWhite() {
        assertEquals(Team.WHITE, Team.BLACK.other());
    }

    @Test
    @DisplayName("opposite() est symétrique")
    void oppositeSymmetric() {
        for (Team t : Team.values()) {
            assertEquals(t, t.other().other());
        }
    }
}