package com.mygdx.game;

import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MyGdxGame extends ApplicationAdapter implements Screen{
	SpriteBatch batch;
	Background bg;
	Bird bird; // птичка
	Obstacles obstacles;
	boolean gameOver;
	Texture restartTexture, to_menu;
	BitmapFont Font_score;
	BitmapFont Font_score_final;
	static int score;
	static int score_itogo;
	boolean is_bird = true; // если птица отрисована
	Animation<TextureRegion> animation; // гифка
	float elapsed;
	final Drop game;
	Music music_fon1, music_fon2, music_fon3, music_fon4, music_fon5, music_fon6, music_fon7, music_game_over; // сами песенки
	boolean isPlaying1, isPlaying2, isPlaying3, isPlaying4, isPlaying5, isPlaying6, isPlaying7; // проверка воспроизводится ли музыка
	private float timeSeconds = 0f; // таймер, чтоб проигрывало ровно один раз музыку и меняло на другую
	boolean in_menu = false; // если произошел переход в меню
	private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private ImageButton button;
    private Stage stage;
    private OrthographicCamera camera;
    Random random_choose_maingame = new Random(); // рандомное число
    int random, score_to_file;
    FileWriter file_results;
	
	public MyGdxGame (final Drop gam) {
		Gdx.graphics.setVSync(true); // вертикальная синхронизация
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
		random = random_choose_maingame.nextInt((7 - 1) + 1) + 1;
		
		music_fon1 = Gdx.audio.newMusic(Gdx.files.internal("music_fon1.mp3")); // фоновая музыка
		music_fon2 = Gdx.audio.newMusic(Gdx.files.internal("music_fon2.mp3"));
		music_fon3 = Gdx.audio.newMusic(Gdx.files.internal("music_fon3.mp3"));
		music_fon4 = Gdx.audio.newMusic(Gdx.files.internal("music_fon4.mp3"));
		music_fon5 = Gdx.audio.newMusic(Gdx.files.internal("music_fon5.mp3"));
		music_fon6 = Gdx.audio.newMusic(Gdx.files.internal("music_fon6.mp3"));
		music_fon7 = Gdx.audio.newMusic(Gdx.files.internal("music_fon7.mp3"));
		music_game_over = Gdx.audio.newMusic(Gdx.files.internal("music_game_over.mp3"));
		
		myTexture = new Texture(Gdx.files.internal("main_game_go_menu.png")); // создание кнопки выхода в меню
	    myTextureRegion = new TextureRegion(myTexture);
	    myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
	    button = new ImageButton(myTexRegionDrawable); //Set the button up
	    button.setPosition(0, 1050);
	    stage = new Stage(new ScreenViewport());
		stage.addActor(button);
		Gdx.input.setInputProcessor(stage);
		button.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	if((score / 16) > score_to_file) {
    				score_to_file = (score / 16);
    			}
    			input_to_file();
            	gameOver = true;
    			in_menu = true;
    			music_fon1.stop(); // остановка фоновой музыки
    			music_fon2.stop(); // остановка фоновой музыки
    			music_fon3.stop(); // остановка фоновой музыки
    			music_fon4.stop(); // остановка фоновой музыки
    			music_fon5.stop(); // остановка фоновой музыки
    			music_fon6.stop(); // остановка фоновой музыки
    			music_fon7.stop(); // остановка фоновой музыки
    			music_game_over.stop(); // остановка музыки game_over
    			recreate();
    			game.setScreen(new MainMenuScreen(game));
                return true;
            }
        }); // нажатие на кнопку выхода в меню
		
		camera = new OrthographicCamera(1920, 1080); // установка на hd (для последующего масштабирования)
	}

	@Override
	public void render (float delta) {
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		update();
		ScreenUtils.clear(1, 1, 1 , 1); // create background color
		batch.setProjectionMatrix(camera.combined);
		batch.begin(); // начало отрисовки
		bg.render(batch); // отрисовка картинки
		obstacles.render(batch);
		Font_score.setColor(Color.WHITE);
		Font_score_final.setColor(Color.WHITE);
		
		if(!gameOver) {
			bird.render(batch);
			Font_score.draw(batch, "SCORE:" + (score / 16), 10, 20); // вывод промежуточного
			
			if(random == 1) {
				music_fon1.play(); // здесь фоновая музыка запускается
				isPlaying1 = music_fon1.isPlaying();
				timeSeconds +=Gdx.graphics.getRawDeltaTime(); // запуск таймера
				if(timeSeconds > 212) {
					music_fon1.stop();
					isPlaying1 = false;
				} // остановка песни
				if(!isPlaying1) {
					random = random_choose_maingame.nextInt((7 - 1) + 1) + 1;
					timeSeconds = 0;
				}// выбор рандомной песни
			}
			else if(random == 2) {
				music_fon2.play(); // здесь фоновая музыка запускается
				isPlaying2 = music_fon2.isPlaying();
				timeSeconds +=Gdx.graphics.getRawDeltaTime();
				if(timeSeconds > 184) {
					music_fon2.stop();
					isPlaying2 = false;
				}
				if(!isPlaying2) {
					random = random_choose_maingame.nextInt((7 - 1) + 1) + 1;
					timeSeconds = 0;
				}
			}
			else if(random == 3) {
				music_fon3.play(); // здесь фоновая музыка запускается
				isPlaying3 = music_fon3.isPlaying();
				timeSeconds +=Gdx.graphics.getRawDeltaTime();
				if(timeSeconds > 263) {
					music_fon3.stop();
					isPlaying3 = false;
				}
				if(!isPlaying3) {
					random = random_choose_maingame.nextInt((7 - 1) + 1) + 1;
					timeSeconds = 0;
				}
			}
			else if(random == 4) {
				music_fon4.play(); // здесь фоновая музыка запускается
				isPlaying4 = music_fon4.isPlaying();
				timeSeconds +=Gdx.graphics.getRawDeltaTime();
				if(timeSeconds > 236) {
					music_fon4.stop();
					isPlaying4 = false;
				}
				if(!isPlaying4) {
					random = random_choose_maingame.nextInt((7 - 1) + 1) + 1;
					timeSeconds = 0;
				}
			}
			else if(random == 5) {
				music_fon5.play(); // здесь фоновая музыка запускается
				isPlaying5 = music_fon5.isPlaying();
				timeSeconds +=Gdx.graphics.getRawDeltaTime();
				if(timeSeconds > 171) {
					music_fon5.stop();
					isPlaying5 = false;
				}
				if(!isPlaying5) {
					random = random_choose_maingame.nextInt((7 - 1) + 1) + 1;
					timeSeconds = 0;
				}
			}
			else if(random == 6) {
				music_fon6.play(); // здесь фоновая музыка запускается
				isPlaying6 = music_fon6.isPlaying();
				timeSeconds +=Gdx.graphics.getRawDeltaTime();
				if(timeSeconds > 124) {
					music_fon6.stop();
					isPlaying6 = false;
				}
				if(!isPlaying6) {
					random = random_choose_maingame.nextInt((7 - 1) + 1) + 1;
					timeSeconds = 0;
				}
			}
			else if(random == 7) {
				music_fon7.play(); // здесь фоновая музыка запускается
				isPlaying7 = music_fon7.isPlaying();
				timeSeconds +=Gdx.graphics.getRawDeltaTime();
				if(timeSeconds > 138) {
					music_fon7.stop();
					isPlaying7 = false;
				}
				if(!isPlaying7) {
					random = random_choose_maingame.nextInt((7 - 1) + 1) + 1;
					timeSeconds = 0;
				}
			} 
			 
		} // пока не проиграли
		else {
			bird.position.y = -100; // перемещание птицы за экран, чтоб при поражении нажатие на space не работало
			batch.draw(restartTexture, 320, 190);
			Font_score_final.getData().setScale(3, 2); // размер шрифта
			Font_score_final.draw(batch, "FINAL RESULT:" + (score_itogo / 16), 350, 420); // вывод итогового результата
			elapsed += Gdx.graphics.getDeltaTime();
		    batch.draw(animation.getKeyFrame(elapsed), 320, 90); // гифка 
			is_bird = false;
			if(gameOver && !in_menu) {
				music_game_over.play();
			}
			music_fon1.stop(); // остановка фоновой музыки
			music_fon2.stop(); // остановка фоновой музыки
			music_fon3.stop(); // остановка фоновой музыки
			music_fon4.stop(); // остановка фоновой музыки
			music_fon5.stop(); // остановка фоновой музыки
			music_fon6.stop(); // остановка фоновой музыки
			music_fon7.stop(); // остановка фоновой музыки
		} // в случае поражения
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime()); // отрисовка кнопки
        stage.draw();
	}
	
	public void update() {
		bg.update();
		obstacles.update();
		bird.update();
		for(int i = 0; i < Obstacles.obs.length; i++) {
			if(bird.position.x > Obstacles.obs[i].position.x && bird.position.x < Obstacles.obs[i].position.x + 50 && is_bird == true) { // чтоб считало очки пока птица отрисована
				score += 1; // подсчет очков
				if(!Obstacles.obs[i].emptySpace.contains(bird.position)) {
					gameOver = true;
					score_itogo = score; // итоговый результат
				}
			}
		} // если врежется в трубу
		if(bird.position.y < 0 || bird.position.y > 580) {
			gameOver = true;
			score_itogo = score; // итоговый результат
			if((score_itogo / 16) > score_to_file) {
				score_to_file = (score / 16);
			}
		} // если вылетает за экран
		if(Gdx.input.isKeyPressed(Input.Keys.W) && gameOver) {
			recreate();
		}// рестарт
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			if((score / 16) > score_to_file) {
				score_to_file = (score / 16);
			}
			input_to_file();
			gameOver = true;
			in_menu = true;
			score = 0;
			score_itogo = 0;
			Background.new_fon = true; // чтоб с фоном не баговало
			Background.promezh_fon = true; // чтоб с фоном не баговало
			music_fon1.stop(); // остановка фоновой музыки
			music_fon2.stop(); // остановка фоновой музыки
			music_fon3.stop(); // остановка фоновой музыки
			music_fon4.stop(); // остановка фоновой музыки
			music_fon5.stop(); // остановка фоновой музыки
			music_fon6.stop(); // остановка фоновой музыки
			music_fon7.stop(); // остановка фоновой музыки
			music_game_over.stop(); // остановка музыки game_over
			dispose();
			game.setScreen(new MainMenuScreen(game));
		}// Выход в меню
	}
	
	public void input_to_file(){
		String shifr;
		if(statistics_menu.max_result_score < score_to_file) {
			statistics_menu.max_result_score = score_to_file;	
			shifr = encryption();
			try {
				try {
					file_results = new FileWriter("..//assets/results.txt", false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // запись в файл
				System.out.print("Записал");
				System.out.print(shifr);
				file_results.write(shifr);
				file_results.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} // запись в файл
	
	public String encryption() {
		String temp_shifr = "";
		String temp = Integer.toString(statistics_menu.max_result_score);
		for(int i = 0; i < temp.length(); i++) {
			if(temp.charAt(i) == '0') {
				temp_shifr += ")";
			}
			else if(temp.charAt(i) == '1') {
				temp_shifr += "l";
			}
			else if(temp.charAt(i) == '2') {
				temp_shifr += "b";
			}
			else if(temp.charAt(i) == '3') {
				temp_shifr += "z";
			}
			else if(temp.charAt(i) == '4') {
				temp_shifr += "v";
			}
			else if(temp.charAt(i) == '5') {
				temp_shifr += "<";
			}
			else if(temp.charAt(i) == '6') {
				temp_shifr += "[";
			}
			else if(temp.charAt(i) == '7') {
				temp_shifr += "$";
			}
			else if(temp.charAt(i) == '8') {
				temp_shifr += "!";
			}
			else if(temp.charAt(i) == '9') {
				temp_shifr += "j";
			}
		}
		return temp_shifr;
	} // шифрование цифр
	
	@Override
	public void dispose () {
		batch.dispose();
	}
	
	public void recreate() {
		obstacles.recreate();
		bird.recreate();
		gameOver = false;
		score = 0;
		score_itogo = 0;
		is_bird = true;
		music_game_over.stop();
		random = random_choose_maingame.nextInt((7 - 1) + 1) + 1; // рандомное число
		Background.new_fon = true; // чтоб с фоном не баговало
		Background.promezh_fon = true; // чтоб с фоном не баговало
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}


	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resize(int width, int height){
		camera.setToOrtho(false, 1080, 600); // масштабироавние экрана
	}
}
