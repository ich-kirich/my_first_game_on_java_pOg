package com.mygdx.game;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MyGdxGame extends ApplicationAdapter implements Screen{
	SpriteBatch batch;
	Background bg;
	Bird bird; // bird
	Obstacles obstacles;
	static boolean gameOver;
	Texture restartTexture, to_menu;
	BitmapFont Font_score;
	BitmapFont Font_score_final;
	static int score;
	static int score_itogo;
	boolean is_bird = true; // if the bird is rendered
	Animation<TextureRegion> animation, animation_die; // gif
	float elapsed, elapsed_anim_bird, x_pos, y_pos;
	final Drop game;
	Music music_fon1, music_fon2, music_fon3, music_fon4, music_fon5, music_fon6, music_fon7, music_game_over; // music
	boolean isPlaying1, isPlaying2, isPlaying3, isPlaying4, isPlaying5, isPlaying6, isPlaying7; // check if music is playing
	private float timeSeconds = 0f; // timer to play music exactly once and change to another
	boolean in_menu = false; // if there is a transition to the menu
	private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private ImageButton button;
    private Stage stage;
    private OrthographicCamera camera;
    Random random_choose_maingame = new Random(); // random number
    int random, score_to_file;
    FileWriter file_results;
	
	public MyGdxGame (final Drop gam) {
		Gdx.graphics.setVSync(true); // vertical sync
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
		animation_die = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("die_animation1.gif").read()); // bird death GIF
		random = random_choose_maingame.nextInt((7 - 1) + 1) + 1;
		
		music_fon1 = Gdx.audio.newMusic(Gdx.files.internal("music_fon1.mp3")); // background music
		music_fon2 = Gdx.audio.newMusic(Gdx.files.internal("music_fon2.mp3"));
		music_fon3 = Gdx.audio.newMusic(Gdx.files.internal("music_fon3.mp3"));
		music_fon4 = Gdx.audio.newMusic(Gdx.files.internal("music_fon4.mp3"));
		music_fon5 = Gdx.audio.newMusic(Gdx.files.internal("music_fon5.mp3"));
		music_fon6 = Gdx.audio.newMusic(Gdx.files.internal("music_fon6.mp3"));
		music_fon7 = Gdx.audio.newMusic(Gdx.files.internal("music_fon7.mp3"));
		music_game_over = Gdx.audio.newMusic(Gdx.files.internal("music_game_over.mp3"));
		
		myTexture = new Texture(Gdx.files.internal("main_game_go_menu.png")); // creating a menu exit button
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
    			music_fon1.stop(); // stop background music
    			music_fon2.stop(); // stop background music
    			music_fon3.stop(); // stop background music
    			music_fon4.stop(); // stop background music
    			music_fon5.stop(); // stop background music
    			music_fon6.stop(); // stop background music
    			music_fon7.stop(); // stop background music
    			music_game_over.stop(); // stop background music game_over
    			recreate();
    			game.setScreen(new MainMenuScreen(game));
                return true;
            }
        }); // pressing the menu exit button
		
		camera = new OrthographicCamera(1920, 1080); // setting to hd (for later scaling)
	}

	@Override
	public void render (float delta) {
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		update();
		ScreenUtils.clear(1, 1, 1 , 1); // create background color
		batch.setProjectionMatrix(camera.combined);
		batch.begin(); // start drawing
		bg.render(batch); // drawing a picture
		obstacles.render(batch);
		Font_score.setColor(Color.WHITE);
		Font_score_final.setColor(Color.WHITE);
		
		if(!gameOver) {
			bird.render(batch);
			Font_score.draw(batch, "SCORE:" + (score / 16), 10, 20); // intermediate result output
			
			if(random == 1) {
				music_fon1.play(); // here the background music starts
				isPlaying1 = music_fon1.isPlaying();
				timeSeconds +=Gdx.graphics.getRawDeltaTime(); // start timer
				if(timeSeconds > 212) {
					music_fon1.stop();
					isPlaying1 = false;
				} // music stop
				if(!isPlaying1) {
					random = random_choose_maingame.nextInt((7 - 1) + 1) + 1;
					timeSeconds = 0;
				}// random choose music
			}
			else if(random == 2) {
				music_fon2.play(); // here the background music starts
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
				music_fon3.play(); // here the background music starts
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
				music_fon4.play(); // here the background music starts
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
				music_fon5.play(); // here the background music starts
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
				music_fon6.play(); // here the background music starts
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
				music_fon7.play(); // here the background music starts
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
			 
		} // until you lose
		else {
			if(is_bird) {
				y_pos = bird.position.y;
				x_pos = 100;
			}
			is_bird = false;
			x_pos -= 4;
			elapsed_anim_bird += Gdx.graphics.getDeltaTime(); 
			batch.draw(animation_die.getKeyFrame(elapsed), x_pos,  y_pos); // gif
			bird.position.y = -100; // moving the bird off the screen so that when hit pressing space does not work
			batch.draw(restartTexture, 320, 190);
			Font_score_final.getData().setScale(3, 2); // font size
			Font_score_final.draw(batch, "FINAL RESULT:" + (score_itogo / 16), 350, 420); // conclusion of the final result
			elapsed += Gdx.graphics.getDeltaTime();
		    batch.draw(animation.getKeyFrame(elapsed), 320, 90); // gif 
			if(gameOver && !in_menu) {
				music_game_over.play();
			}
			music_fon1.stop(); // stop background music
			music_fon2.stop(); // stop background music
			music_fon3.stop(); // stop background music
			music_fon4.stop(); // stop background music
			music_fon5.stop(); // stop background music
			music_fon6.stop(); // stop background music
			music_fon7.stop(); // stop background music
		} // in case of defeat
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime()); // button rendering
        stage.draw();
	}
	
	public void update() {
		bg.update();
		obstacles.update();
		bird.update();
		for(int i = 0; i < Obstacles.obs.length; i++) {
			if(bird.position.x > Obstacles.obs[i].position.x && bird.position.x < Obstacles.obs[i].position.x + 50 && is_bird == true) { // to count points while the bird is drawn
				score += 1; // scoring
				if(!Obstacles.obs[i].emptySpace.contains(bird.position.x, bird.position.y) || !Obstacles.obs[i].emptySpace.contains(bird.position.x, bird.position.y + 50) ) { // upper and lower border of the bird
					gameOver = true;
					score_itogo = score; // final result
				}
			}
		} // if it hits a pipe
		if(bird.position.y < 0 || bird.position.y > 580) {
			gameOver = true;
			score_itogo = score; // final result
			if((score_itogo / 16) > score_to_file) {
				score_to_file = (score / 16);
			}
		} // if it goes off screen
		if(Gdx.input.isKeyPressed(Input.Keys.W) && gameOver) {
			recreate();
		}// restart
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			if((score / 16) > score_to_file) {
				score_to_file = (score / 16);
			}
			input_to_file();
			gameOver = true;
			in_menu = true;
			score = 0;
			score_itogo = 0;
			Background.new_fon = true; // it is for background in Class Background
			Background.promezh_fon = true; // it is for background in Class Background
			music_fon1.stop(); // stop background music
			music_fon2.stop(); // stop background music
			music_fon3.stop(); // stop background music
			music_fon4.stop(); // stop background music
			music_fon5.stop(); // stop background music
			music_fon6.stop(); // stop background music
			music_fon7.stop(); // stop background music
			music_game_over.stop(); // stop background music game_over
			dispose();
			game.setScreen(new MainMenuScreen(game));
		}// Exit to the menu
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
				} // writing to file
				file_results.write(shifr);
				file_results.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} // writing to file
	
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
	} // digit encryption
	
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
		random = random_choose_maingame.nextInt((7 - 1) + 1) + 1; //random number
		Background.new_fon = true; // it is for background in Class Background
		Background.promezh_fon = true; // it is for background in Class Background
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
		camera.setToOrtho(false, 1080, 600); // screen scaling
	}
}
