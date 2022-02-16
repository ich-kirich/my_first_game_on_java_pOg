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
	Animation<TextureRegion> animation; // �����
	float elapsed;
	private float timeSeconds = 0f;
	Texture intro_final;
	
	public Intro(final Drop gam) {
		this.game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("intro.gif").read()); // �����
		intro_final = new Texture("intro_final.png"); // ��� ���������� �����
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		camera.update();
		ScreenUtils.clear(1, 1, 1, 1); // create background color
		game.batch.begin(); // ������ ���������
		elapsed += Gdx.graphics.getDeltaTime() + 0.01;
		timeSeconds +=Gdx.graphics.getRawDeltaTime(); // ��� ����, ����� ����� ���� ��� ���������(���� ���� ������)
		if(timeSeconds <= 6) { // ����� 6 ������ ��� ������ ��������� �����
			game.batch.draw(animation.getKeyFrame(elapsed), -75, -50); // �����
			}
		else {
			game.batch.draw(intro_final, -75, -50); // ��������� ����, ������� ���������� ����� ������
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
