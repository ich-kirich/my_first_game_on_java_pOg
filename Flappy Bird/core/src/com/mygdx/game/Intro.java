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
	Animation<TextureRegion> animation; // �����
	float elapsed;
	private float timeSeconds = 0f;
	Texture intro_final;
    FileReader file_read;

	public Intro(final Drop gam) {
		Gdx.graphics.setVSync(true); // ������������ �������������
		this.game = gam;
		animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("intro.gif").read()); // �����
		intro_final = new Texture("intro_final.png"); // ��� ���������� �����
		
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
