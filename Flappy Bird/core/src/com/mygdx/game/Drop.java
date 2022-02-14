package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Drop extends Game{ // главный класс
	
	SpriteBatch batch;
	BitmapFont font;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new Intro(this));// самый первый экран
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		font.dispose();
	}

}
