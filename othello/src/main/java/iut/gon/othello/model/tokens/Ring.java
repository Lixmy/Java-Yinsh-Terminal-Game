package iut.gon.othello.model.tokens;

import iut.gon.othello.model.Team;

public class Ring extends Token {

	public Ring(Team team) {
		super(team);
	}

	@Override
	public String charRepr() {
		return "Ring";
	}

	@Override
	public Token clone() {
		return new Ring(this.getTeam());
	}

}
