package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bird {
	Texture img;
	Vector2 position;
	float vy;
	float gravity;
	Music fly_stepan;
	
	public Bird() {
		img = new Texture("bird2.png");
		position = new Vector2(100, 380);
		vy = 0;
		gravity = -0.7f; // гравитация
		fly_stepan = Gdx.audio.newMusic(Gdx.files.internal("fly_stepan1.mp3")); // при полете степана
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(img, position.x, position.y);
	}
	
	public void update() {
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			vy = 5; // при нажати на пробел птичка прыгает выше
			fly_stepan.play();
		}
		else {
			fly_stepan.stop();
		}
		
		vy += gravity;
		position.y += vy;
	}
	
	public void recreate() {
		position = new Vector2(100, 380);
		vy = 0;
	}
}
