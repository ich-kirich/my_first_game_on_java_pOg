package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bird {
	static Texture img;
	static Vector2 position;
	float vy;
	float gravity;
	static Music fly;
	public Bird() {
		if(!skins_menu.is_choose_skin) {
			img = new Texture("skin_default.png");
			fly = Gdx.audio.newMusic(Gdx.files.internal("fly_default.mp3")); // при полете звук
		} // если скин не выбран
		position = new Vector2(100, 380);
		vy = 0;
		gravity = -0.7f; // гравитация
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(img, position.x, position.y);
	}
	
	public void update() {
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && position.y > 0) { // position.y > 0  - чтоб после поражения не работало нажатие на space
			vy = 5; // при нажати на пробел птичка прыгает выше
			fly.play();
		}
		else {
			fly.stop();
		}
		
		vy += gravity;
		position.y += vy;
	}
	
	public void recreate() {
		position = new Vector2(100, 380);
		vy = 0;
	}
}
