package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class Intro implements Screen{

	final Drop game;
	OrthographicCamera camera;
	Animation<TextureRegion> animation; // гифка
	float elapsed;
	private float timeSeconds = 0f;
	Texture intro_final;
	
	public Intro(final Drop gam) {
		this.game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("intro.gif").read()); // гифка
		intro_final = new Texture("intro_final.png"); // для последнего кадра
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		camera.update();
		ScreenUtils.clear(1, 1, 1, 1); // create background color
		game.batch.begin(); // начало отрисовки
		elapsed += Gdx.graphics.getDeltaTime() + 0.01;
		timeSeconds +=Gdx.graphics.getRawDeltaTime(); // для того, чтобы гифка один раз проиграла(идет счет секунд)
		if(timeSeconds <= 6) { // ровно 6 секунд для одного проигрыша гифки
			game.batch.draw(animation.getKeyFrame(elapsed), -75, -50); // гифка
			}
		else {
			game.batch.draw(intro_final, -75, -50); // последний кадр, который бесконечно будет висеть
		}
		game.batch.end();
		if(Gdx.input.isKeyPressed(Input.Keys.F)) {
			game.setScreen(new MainMenuScreen(game));
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
