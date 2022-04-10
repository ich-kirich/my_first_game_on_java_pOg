package com.mygdx.game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
    FileReader file_read;

	public Intro(final Drop gam) {
		Gdx.graphics.setVSync(true); // вертикальная синхронизация
		this.game = gam;
		animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("intro.gif").read()); // гифка
		intro_final = new Texture("intro_final.png"); // для последнего кадра
		
		try {
			file_read = new FileReader("..//assets/results.txt");
			int c;
		    String temp = "";
            try {
				while((c=file_read.read())!=-1){
				    temp += c;
				}
				if(temp == "") {
					statistics_menu.max_result_score = 0;
				}
				else {
					statistics_menu.max_result_score = Integer.parseInt(temp);
				}
				file_read.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
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
			dispose();
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
