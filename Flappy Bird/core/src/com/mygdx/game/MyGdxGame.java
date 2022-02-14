package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter implements Screen{
	SpriteBatch batch;
	Background bg;
	Bird bird; // ������
	Obstacles obstacles;
	boolean gameOver;
	Texture restartTexture;
	BitmapFont Font_score;
	BitmapFont Font_score_final;
	int score;
	int score_itogo;
	boolean is_bird = true; // ���� ����� ����������
	Animation<TextureRegion> animation; // �����
	float elapsed;
	final Drop game;
	
	
	public MyGdxGame (final Drop gam) {
		this.game = gam;
		batch = new SpriteBatch();
		bg = new Background();
		obstacles = new Obstacles();
		bird = new Bird();
		gameOver = false;
		restartTexture = new Texture("RestartBtn1.png");
		Font_score = new BitmapFont();
		Font_score_final = new BitmapFont();
		animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("W.gif").read()); // GIF
	}

	@Override
	public void render (float delta) {
		update();
		ScreenUtils.clear(1, 1, 1, 1); // create background color
		batch.begin(); // ������ ���������
		
		bg.render(batch); // ��������� ��������
		obstacles.render(batch);
		Font_score.setColor(Color.WHITE);
		Font_score_final.setColor(Color.WHITE);
		if(!gameOver) {
			bird.render(batch);
			Font_score.draw(batch, "SCORE:" + (score / 16), 10, 20); // ����� ��������������
		}
		else {
			batch.draw(restartTexture, 130, 150);
			Font_score_final.getData().setScale(3, 2); // ������ ������
			Font_score_final.draw(batch, "FINAL RESULT:" + (score_itogo / 16), 160, 380); // ����� ��������� ����������
			elapsed += Gdx.graphics.getDeltaTime();
		    batch.draw(animation.getKeyFrame(elapsed), 130, 50); // ����� 
			is_bird = false;
		}
		batch.end();
	}
	
	public void update() {
		bg.update();
		obstacles.update();
		bird.update();
		for(int i = 0; i < Obstacles.obs.length; i++) {
			if(bird.position.x > Obstacles.obs[i].position.x && bird.position.x < Obstacles.obs[i].position.x + 50 && is_bird == true) { // ���� ������� ���� ���� ����� ����������
				score += 1; // ������� �����
				if(!Obstacles.obs[i].emptySpace.contains(bird.position)) {
					gameOver = true;
					score_itogo = score; // �������� ���������
				}
			}
		} // ���� �������� � �����
		if(bird.position.y < 0 || bird.position.y > 466) {
			gameOver = true;
			score_itogo = score; // �������� ���������
		} // ���� �������� �� �����
		if(Gdx.input.isKeyPressed(Input.Keys.W) && gameOver) {
			recreate();
		}// �������
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			game.setScreen(new MainMenuScreen(game));
		}// ����� � ����
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
	public void recreate() {
		obstacles.recreate();
		bird.recreate();
		gameOver = false;
		score = 0;
		is_bird = true;

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	
}
