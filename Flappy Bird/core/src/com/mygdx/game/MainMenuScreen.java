package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen{
	
	final Drop game;
	OrthographicCamera camera;
	Texture bg_menu;
	
	public MainMenuScreen(Drop gam) {
		this.game = gam;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		bg_menu = new Texture("menu.png");
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
		game.batch.draw(bg_menu, 0, -100);
		game.font.draw(game.batch, "PLAY", 100, 120);
		game.font.draw(game.batch, "EXIT", 100, 70);
		game.batch.end();
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
			if((90 < Gdx.input.getX() && Gdx.input.getX() <= 130) && (Gdx.input.getY() >= 350 && Gdx.input.getY() <= 370)) {
				game.setScreen(new MyGdxGame(game));
				dispose();
			}
	    } // кнопка PLAY
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && (100 < Gdx.input.getX() && Gdx.input.getX() <= 130) && (Gdx.input.getY() >= 400 && Gdx.input.getY() <= 420)) {
			Gdx.app.exit();
		}// кнопка EXIT
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
