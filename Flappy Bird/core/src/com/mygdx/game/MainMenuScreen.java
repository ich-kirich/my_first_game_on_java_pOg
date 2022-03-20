package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen{
	
	final Drop game;
	OrthographicCamera camera;
	Texture bg_menu;
	Music music_intro_fon, music_intro_fon2;
	int random_choose_menu = (int)(Math.random() * ((2 - 1) + 1)) + 1; // рандомное число
    private Texture myTexture, myTexture1;
    private TextureRegion myTextureRegion, myTextureRegion1;
    private TextureRegionDrawable myTexRegionDrawable, myTexRegionDrawable1;
    protected ImageButton button, button1;
    protected Stage stage;
	
	public MainMenuScreen(Drop gam) {
		this.game = gam;
		bg_menu = new Texture("menu.png");
		music_intro_fon = Gdx.audio.newMusic(Gdx.files.internal("intro_music.mp3")); // фоновая музыка
		music_intro_fon2 = Gdx.audio.newMusic(Gdx.files.internal("intro_music2.mp3")); // фоновая музыка
		
		myTexture = new Texture(Gdx.files.internal("button_play.png"));
		myTexture1 = new Texture(Gdx.files.internal("button_exit.png"));
	    myTextureRegion = new TextureRegion(myTexture);
	    myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
	    button = new ImageButton(myTexRegionDrawable); //Set the button up
	    button.setPosition(400, 250);
	    stage = new Stage(new ScreenViewport());
		stage.addActor(button); // кнопка play
		
		myTextureRegion1 = new TextureRegion(myTexture1);
	    myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
		button1 = new ImageButton(myTexRegionDrawable1);
		button1.setPosition(400, 180);
		stage.addActor(button1); // // кнопка exit
		
		Gdx.input.setInputProcessor(stage); // начало принятие нажатия на кнопки
		button.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	music_intro_fon.stop(); // прекращение проигрывания фоновой музыки
    			music_intro_fon2.stop(); // прекращение проигрывания фоновой музыки
    			game.setScreen(new MyGdxGame(game));
    			Background.new_fon = true; // чтоб с фоном не баговало
    			Background.promezh_fon = true; // чтоб с фоном не баговало
    			dispose();
                return true;
            }
        }); //  нажатие на кнопку play
		
		Gdx.input.setInputProcessor(stage);
	    button1.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	music_intro_fon.stop(); // прекращение проигрывания фоновой музыки
    			music_intro_fon2.stop(); // прекращение проигрывания фоновой музыки
            	Gdx.app.exit();
            	dispose();
                return true;
            }
        }); // нажатие на кнопку exit
	}
	

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(1, 1, 1, 1); // create background color
		game.batch.begin(); // начало отрисовки
		game.batch.draw(bg_menu, 0, -100);
		switch(random_choose_menu) {
		case 1:
			music_intro_fon.play(); // здесь фоновая музыка запускается
			break;
		case 2:
			music_intro_fon2.play(); // здесь фоновая музыка запускается
			break;
		default:
			System.out.println("Что пошло не так с музыкой фоновой в меню......");
		} // рандомное проигрывание фоновой музыки
		game.batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime()); // отрисовка кнопок
		stage.draw();
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
