package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bird {
	Texture img;
	static Vector2 position;
	float vy;
	float gravity;
	Music fly_stepan;
	
	public Bird() {
		img = new Texture("bird2.png");
		position = new Vector2(100, 380);
		vy = 0;
		gravity = -0.7f; // Ã£Ã°Ã Ã¢Ã¨Ã²Ã Ã¶Ã¨Ã¿
		fly_stepan = Gdx.audio.newMusic(Gdx.files.internal("fly_stepan1.mp3")); // Ã¯Ã°Ã¨ Ã¯Ã®Ã«Ã¥Ã²Ã¥ Ã±Ã²Ã¥Ã¯Ã Ã­Ã 
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(img, position.x, position.y);
	}
	
	public void update() {
<<<<<<< HEAD
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && position.y > 0) { // position.y > 0  - ÷òîá ïîñëå ïîðàæåíèÿ íå ðàáîòàëî íàæàòèå íà space
			vy = 5; // ïðè íàæàòè íà ïðîáåë ïòè÷êà ïðûãàåò âûøå
=======
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && position.y > 0) { // position.y < 0  - Ã·Ã²Ã®Ã¡ Ã¯Ã®Ã±Ã«Ã¥ Ã¯Ã®Ã°Ã Ã¦Ã¥Ã­Ã¨Ã¿ Ã­Ã¥ Ã°Ã Ã¡Ã®Ã²Ã Ã«Ã® Ã­Ã Ã¦Ã Ã²Ã¨Ã¥ Ã­Ã  space
			vy = 5; // Ã¯Ã°Ã¨ Ã­Ã Ã¦Ã Ã²Ã¨ Ã­Ã  Ã¯Ã°Ã®Ã¡Ã¥Ã« Ã¯Ã²Ã¨Ã·ÃªÃ  Ã¯Ã°Ã»Ã£Ã Ã¥Ã² Ã¢Ã»Ã¸Ã¥
>>>>>>> 449b3d7da87d7056624c983323762156e1e1943a
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
