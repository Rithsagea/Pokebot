package com.pokebot.game;

import com.pokebot.types.data.MoveData;

public class PokemonMove {
	public MoveData move;
	public int maxPP;
	public int currPP;
	
	public PokemonMove(MoveData move) {
		this.move = move;
		
		maxPP = move.pp;
		currPP= maxPP;
	}
}
