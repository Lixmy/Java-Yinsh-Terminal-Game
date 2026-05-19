package iut.gon.othello.model.tokens;

import iut.gon.othello.model.Team;

public class Pawn extends Token {

	public Pawn(Team team) {
		super(team);
	}

	@Override
	public String charRepr() {
		return "Pawn";
	}

	@Override
	public Token clone() {
		return new Pawn(this.getTeam());
	}

}
