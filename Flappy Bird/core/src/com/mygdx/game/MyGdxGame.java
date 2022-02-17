package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
	Music music_fon1, music_fon2, music_fon3, music_fon4, music_fon5, music_fon6, music_fon7, music_game_over; // ���� �������
	int random_choose_maingame = 6; // ��������� �����
	boolean isPlaying1, isPlaying2, isPlaying3, isPlaying4, isPlaying5, isPlaying6, isPlaying7; // �������� ��������������� �� ������
	private float timeSeconds = 0f; // ������, ���� ����������� ����� ���� ��� ������ � ������ �� ������
	boolean in_menu = false; // ���� ��������� ������� � ����
	
	
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
		music_fon1 = Gdx.audio.newMusic(Gdx.files.internal("music_fon1.mp3")); // ������� ������
		music_fon2 = Gdx.audio.newMusic(Gdx.files.internal("music_fon2.mp3"));
		music_fon3 = Gdx.audio.newMusic(Gdx.files.internal("music_fon3.mp3"));
		music_fon4 = Gdx.audio.newMusic(Gdx.files.internal("music_fon4.mp3"));
		music_fon5 = Gdx.audio.newMusic(Gdx.files.internal("music_fon5.mp3"));
		music_fon6 = Gdx.audio.newMusic(Gdx.files.internal("music_fon6.mp3"));
		music_fon7 = Gdx.audio.newMusic(Gdx.files.internal("music_fon7.mp3"));
		music_game_over = Gdx.audio.newMusic(Gdx.files.internal("music_game_over.mp3"));

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
			
			if(random_choose_maingame == 1) {
				music_fon1.play(); // ����� ������� ������ �����������
				isPlaying1 = music_fon1.isPlaying();
				timeSeconds +=Gdx.graphics.getRawDeltaTime(); // ������ �������
				if(timeSeconds > 575) {
					music_fon1.stop();
					isPlaying1 = false;
				} // ��������� �����
				if(!isPlaying1) {
					random_choose_maingame = (int)(Math.random() * ((7 - 1) + 1)) + 1;
					timeSeconds = 0;
				}// ����� ��������� �����

			}
			else if(random_choose_maingame == 2) {
				music_fon2.play(); // ����� ������� ������ �����������
				isPlaying2 = music_fon2.isPlaying();
				timeSeconds +=Gdx.graphics.getRawDeltaTime();
				if(timeSeconds > 548) {
					music_fon2.stop();
					isPlaying2 = false;
				}
				if(!isPlaying2) {
					random_choose_maingame = (int)(Math.random() * ((7 - 1) + 1)) + 1;
					timeSeconds = 0;
				}
			}
			else if(random_choose_maingame == 3) {
				music_fon3.play(); // ����� ������� ������ �����������
				isPlaying3 = music_fon3.isPlaying();
				timeSeconds +=Gdx.graphics.getRawDeltaTime();
				if(timeSeconds > 747) {
					music_fon3.stop();
					isPlaying3 = false;
				}
				if(!isPlaying3) {
					random_choose_maingame = (int)(Math.random() * ((7 - 1) + 1)) + 1;
					timeSeconds = 0;
				}
			}
			else if(random_choose_maingame == 4) {
				music_fon4.play(); // ����� ������� ������ �����������
				isPlaying4 = music_fon4.isPlaying();
				timeSeconds +=Gdx.graphics.getRawDeltaTime();
				if(timeSeconds > 600) {
					music_fon4.stop();
					isPlaying4 = false;
				}
				if(!isPlaying4) {
					random_choose_maingame = (int)(Math.random() * ((7 - 1) + 1)) + 1;
					timeSeconds = 0;
				}
			}
			else if(random_choose_maingame == 5) {
				music_fon5.play(); // ����� ������� ������ �����������
				isPlaying5 = music_fon5.isPlaying();
				timeSeconds +=Gdx.graphics.getRawDeltaTime();
				if(timeSeconds > 415) {
					music_fon5.stop();
					isPlaying5 = false;
				}
				if(!isPlaying5) {
					random_choose_maingame = (int)(Math.random() * ((7 - 1) + 1)) + 1;
					timeSeconds = 0;
				}
			}
			else if(random_choose_maingame == 6) {
				music_fon6.play(); // ����� ������� ������ �����������
				isPlaying6 = music_fon6.isPlaying();
				timeSeconds +=Gdx.graphics.getRawDeltaTime();
				if(timeSeconds > 365) {
					music_fon6.stop();
					isPlaying6 = false;
				}
				if(!isPlaying6) {
					random_choose_maingame = (int)(Math.random() * ((7 - 1) + 1)) + 1;
					timeSeconds = 0;
				}
			}
			else if(random_choose_maingame == 7) {
				music_fon7.play(); // ����� ������� ������ �����������
				isPlaying7 = music_fon7.isPlaying();
				timeSeconds +=Gdx.graphics.getRawDeltaTime();
				if(timeSeconds > 385) {
					music_fon7.stop();
					isPlaying7 = false;
				}
				if(!isPlaying7) {
					random_choose_maingame = (int)(Math.random() * ((7 - 1) + 1)) + 1;
					timeSeconds = 0;
				}
			}
			
		}
		else {
			batch.draw(restartTexture, 130, 150);
			Font_score_final.getData().setScale(3, 2); // ������ ������
			Font_score_final.draw(batch, "FINAL RESULT:" + (score_itogo / 16), 160, 380); // ����� ��������� ����������
			elapsed += Gdx.graphics.getDeltaTime();
		    batch.draw(animation.getKeyFrame(elapsed), 130, 50); // ����� 
			is_bird = false;
			if(gameOver && !in_menu) {
				music_game_over.play();
			}
			music_fon1.stop(); // ��������� ������� ������
			music_fon2.stop(); // ��������� ������� ������
			music_fon3.stop(); // ��������� ������� ������
			music_fon4.stop(); // ��������� ������� ������
			music_fon5.stop(); // ��������� ������� ������
			music_fon6.stop(); // ��������� ������� ������
			music_fon7.stop(); // ��������� ������� ������
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
			gameOver = true;
			in_menu = true;
			music_fon1.stop(); // ��������� ������� ������
			music_fon2.stop(); // ��������� ������� ������
			music_fon3.stop(); // ��������� ������� ������
			music_fon4.stop(); // ��������� ������� ������
			music_fon5.stop(); // ��������� ������� ������
			music_fon6.stop(); // ��������� ������� ������
			music_fon7.stop(); // ��������� ������� ������
			music_game_over.stop(); // ��������� ������ game_over
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
		music_game_over.stop();
		random_choose_maingame = (int)(Math.random() * ((7 - 1) + 1)) + 1; // ��������� �����

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
