package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Bird {
	static Texture img;
	static Vector3 position;
	float vy;
	float gravity, elapsed_anim_fly;
	static Music fly;
	Animation<TextureRegion> animation_fly;
	public Bird() {
		if(!skins_menu.is_choose_skin) {
			img = new Texture("skin_default.png");
			fly = Gdx.audio.newMusic(Gdx.files.internal("fly_default.mp3")); // при полете звук
		} // если скин не выбран
		position = new Vector3(100, 380, 430);
		vy = 0;
		gravity = -0.7f; // гравитация
		animation_fly = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("fly_anim1.gif").read()); // гифка смерти птички
	}
	
	public void render(SpriteBatch batch) {
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			elapsed_anim_fly += Gdx.graphics.getDeltaTime(); 
			batch.draw(animation_fly.getKeyFrame(elapsed_anim_fly), position.x - 20,  position.y - 15); // гифка
		}
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
		position.z += vy;
	}
	
	public void recreate() {
		position = new Vector3(100, 380, 430);
		vy = 0;
	}
}
