package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Drop extends Game{ // main class for set screen
	
	SpriteBatch batch;
	BitmapFont font;
	public Stage stage;
	
	@Override
	public void create() {
		Gdx.graphics.setVSync(true); // vertical sync
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new Intro(this));// first screen
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
