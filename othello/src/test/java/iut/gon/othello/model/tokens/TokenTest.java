package iut.gon.othello.model.tokens;

import iut.gon.othello.model.Team;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour Token, Pawn et Ring.
 * Chemin : src/test/java/model/TokenTest.java
 */
class TokenTest {

    // -------------------------------------------------------------------------
    // Pawn
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Pawn(WHITE) : team est WHITE")
    void pawnWhiteTeam() {
        Pawn p = new Pawn(Team.WHITE);
        assertEquals(Team.WHITE, p.getTeam());
    }

    @Test
    @DisplayName("Pawn(BLACK) : team est BLACK")
    void pawnBlackTeam() {
        Pawn p = new Pawn(Team.BLACK);
        assertEquals(Team.BLACK, p.getTeam());
    }

    @Test
    @DisplayName("Pawn : toString non null et non vide")
    void pawnToString() {
        Pawn p = new Pawn(Team.WHITE);
        assertNotNull(p.toString());
        assertFalse(p.toString().isBlank());
    }

    @Test
    @DisplayName("Pawn : changeTeam renvoie un Pawn de l'équipe opposée")
    void pawnChangeTeam() {
        Pawn white = new Pawn(Team.WHITE);
        white.changeTeam();
        assertInstanceOf(Pawn.class, white);
        assertEquals(Team.BLACK, white.getTeam());
    }

    // -------------------------------------------------------------------------
    // Ring
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Ring(WHITE) : team est WHITE")
    void ringWhiteTeam() {
        Ring r = new Ring(Team.WHITE);
        assertEquals(Team.WHITE, r.getTeam());
    }

    @Test
    @DisplayName("Ring(BLACK) : team est BLACK")
    void ringBlackTeam() {
        Ring r = new Ring(Team.BLACK);
        assertEquals(Team.BLACK, r.getTeam());
    }

    @Test
    @DisplayName("Ring : toString non null et non vide")
    void ringToString() {
        Ring r = new Ring(Team.BLACK);
        assertNotNull(r.toString());
        assertFalse(r.toString().isBlank());
    }

    // -------------------------------------------------------------------------
    // Hiérarchie
    // -------------------------------------------------------------------------

    @Test
    @DisplayName("Pawn est un Token")
    void pawnIsToken() {
        assertInstanceOf(Token.class, new Pawn(Team.WHITE));
    }

    @Test
    @DisplayName("Ring est un Token")
    void ringIsToken() {
        assertInstanceOf(Token.class, new Ring(Team.WHITE));
    }

    @Test
    @DisplayName("Pawn et Ring sont des types distincts")
    void pawnAndRingDistinct() {
        Token pawn = new Pawn(Team.WHITE);
        Token ring = new Ring(Team.WHITE);
        assertNotEquals(pawn.getClass(), ring.getClass());
    }
}