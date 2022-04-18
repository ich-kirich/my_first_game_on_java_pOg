package com.mygdx.game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class Intro implements Screen{

	final Drop game;
	Animation<TextureRegion> animation; // gif
	float elapsed;
	private float timeSeconds = 0f;
	Texture intro_final;
    FileReader file_read;

	public Intro(final Drop gam) {
		Gdx.graphics.setVSync(true); // vertical sync
		this.game = gam;
		animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("intro.gif").read()); // gif
		intro_final = new Texture("intro_final.png"); // for last frame
		
		try {
			file_read = new FileReader("..//assets/results.txt");
			int c;
		    String temp = "", deshifr, temp_temp;
            try {
				while((c=file_read.read())!=-1){
				    temp += (char)c;
				}
				if(temp == "") {
					statistics_menu.max_result_score = 0;
				}
				else {
					temp_temp = temp;
					deshifr = decryption(temp_temp);
					statistics_menu.max_result_score = Integer.parseInt(deshifr);
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
	public String decryption(String temp) {
		String temp_deshifr = "";
		for(int i = 0; i < temp.length(); i++) {
			if(temp.charAt(i) == ')') {
				temp_deshifr += "0";
			}
			else if(temp.charAt(i) == 'l') {
				temp_deshifr += "1";
			}
			else if(temp.charAt(i) == 'b') {
				temp_deshifr += "2";
			}
			else if(temp.charAt(i) == 'z') {
				temp_deshifr += "3";
			}
			else if(temp.charAt(i) == 'v') {
				temp_deshifr += "4";
			}
			else if(temp.charAt(i) == '<') {
				temp_deshifr += "5";
			}
			else if(temp.charAt(i) == '[') {
				temp_deshifr += "6";
			}
			else if(temp.charAt(i) == '$') {
				temp_deshifr += "7";
			}
			else if(temp.charAt(i) == '!') {
				temp_deshifr += "8";
			}
			else if(temp.charAt(i) == 'j') {
				temp_deshifr += "9";
			}
		}
		return temp_deshifr;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		ScreenUtils.clear(1, 1, 1, 1); // create background color
		game.batch.begin(); // start drawing
		elapsed += Gdx.graphics.getDeltaTime() + 0.01;
		timeSeconds +=Gdx.graphics.getRawDeltaTime(); // in order for the GIF to play once (seconds are counting)
		if(timeSeconds <= 6) { // exactly 6 seconds for one GIF
			game.batch.draw(animation.getKeyFrame(elapsed), -75, -50); // gif
			}
		else {
			game.batch.draw(intro_final, -75, -50); // last frame to be drawn indefinitely
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
