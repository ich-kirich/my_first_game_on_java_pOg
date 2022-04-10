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
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen{
	
	final Drop game;
	private OrthographicCamera camera;
	Texture bg_menu;
	private Texture arrow_left, arrow_right;
	Music music_intro_fon, music_intro_fon2;
	int random_choose_menu = (int)(Math.random() * ((2 - 1) + 1)) + 1; // рандомное число
    private Texture myTexture, myTexture1, myTexture2, button_statistics;
    private TextureRegion myTextureRegion, myTextureRegion1, myTextureRegion2, myTextureRegion3;
    private TextureRegionDrawable myTexRegionDrawable, myTexRegionDrawable1, myTexRegionDrawable2, myTexRegionDrawable3;
    protected ImageButton button, button1, button_to_skins, button_to_statistics;
    protected Stage stage;
    private boolean is_mouse_play = false, is_mouse_exit = false, is_mouse_skins = false, is_mouse_statistics = false;
	
	public MainMenuScreen(Drop gam) {
		Gdx.graphics.setVSync(true); // вертикальная синхронизация
		this.game = gam;
		bg_menu = new Texture("menu.png");
		music_intro_fon = Gdx.audio.newMusic(Gdx.files.internal("intro_music.mp3")); // фоновая музыка
		music_intro_fon2 = Gdx.audio.newMusic(Gdx.files.internal("intro_music2.mp3")); // фоновая музыка
		camera = new OrthographicCamera(1920, 1080); // установка на hd (для последующего масштабирования)
		arrow_left = new Texture("arrow_left.png");
		arrow_right = new Texture("arrow_right.png");
		
		myTexture = new Texture(Gdx.files.internal("button_play.png"));
		myTexture1 = new Texture(Gdx.files.internal("button_exit.png"));
	    myTextureRegion = new TextureRegion(myTexture);
	    myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
	    button = new ImageButton(myTexRegionDrawable); //Set the button up
	    button.setPosition(400, 270);
	    stage = new Stage(new ScreenViewport());
		stage.addActor(button); // кнопка play
		
		myTextureRegion1 = new TextureRegion(myTexture1);
	    myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
		button1 = new ImageButton(myTexRegionDrawable1);
		button1.setPosition(400, 105);
		stage.addActor(button1); // // кнопка exit
		
		myTexture2 = new Texture(Gdx.files.internal("button_to_skins_menu.png"));
		myTextureRegion2 = new TextureRegion(myTexture2);
	    myTexRegionDrawable2 = new TextureRegionDrawable(myTextureRegion2);
	    button_to_skins = new ImageButton(myTexRegionDrawable2);
		button_to_skins.setPosition(400, 215);
		stage.addActor(button_to_skins); // // кнопка skins
		
		button_statistics = new Texture(Gdx.files.internal("button_statistics.png"));
	    myTextureRegion3 = new TextureRegion(button_statistics);
	    myTexRegionDrawable3 = new TextureRegionDrawable(myTextureRegion3);
	    button_to_statistics = new ImageButton(myTexRegionDrawable3); //Set the button up
	    button_to_statistics.setPosition(400, 157);
	    button_to_statistics.setTransform(true);
	    button_to_statistics.setScaleY(1.2f);;
		stage.addActor(button_to_statistics); // кнопка statistics
		
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
            
            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
            	is_mouse_play = true;
            } // если курсор наведен
            
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            	is_mouse_play = false;
            } // если не наведен курсор
            
        }); //  нажатие на кнопку play
		
		Gdx.input.setInputProcessor(stage);
	    button1.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	music_intro_fon.stop(); // прекращение проигрывания фоновой музыки
    			music_intro_fon2.stop(); // прекращение проигрывания фоновой музыки
    			game.setScreen(new ExitMenu(game));
            	dispose();
                return true;
            }
            
            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
            	is_mouse_exit = true;
            } // если курсор наведен
            
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            	is_mouse_exit = false;
            } // если не наведен курсор
        }); // нажатие на кнопку exit
	    
	    Gdx.input.setInputProcessor(stage);
	    button_to_skins.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	music_intro_fon.stop(); // прекращение проигрывания фоновой музыки
    			music_intro_fon2.stop(); // прекращение проигрывания фоновой музыки
    			game.setScreen(new skins_menu(game));
            	dispose();
                return true;
            }
            
            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
            	is_mouse_skins = true;
            } // если курсор наведен
            
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            	is_mouse_skins = false;
            } // если не наведен курсор
        }); // нажатие на кнопку skins
	    
	    Gdx.input.setInputProcessor(stage);
	    button_to_statistics.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	music_intro_fon.stop(); // прекращение проигрывания фоновой музыки
    			music_intro_fon2.stop(); // прекращение проигрывания фоновой музыки
    			game.setScreen(new statistics_menu(game));
    			Background.new_fon = true; // чтоб с фоном не баговало
    			Background.promezh_fon = true; // чтоб с фоном не баговало
    			dispose();
                return true;
            }
            
            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
            	is_mouse_statistics = true;
            } // если курсор наведен
            
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            	is_mouse_statistics = false;
            } // если не наведен курсор
            
        }); //  нажатие на кнопку statistics
	}
	

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(1, 1, 1, 1); // create background color
		//game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin(); // начало отрисовки
		game.batch.draw(bg_menu, 0, -100);
		
		if(is_mouse_exit) {
			game.batch.draw(arrow_right, 170, 45, 10, 12);
			game.batch.draw(arrow_left, 118, 45, 10, 12);
		}// если курсор наведен на кнопку exit
		if(is_mouse_play) {
			game.batch.draw(arrow_right, 170, 119, 10, 12);
			game.batch.draw(arrow_left, 118, 119, 10, 12);
		} // если курсор наведен на кнопку play
		if(is_mouse_skins) {
			game.batch.draw(arrow_right, 170, 95, 10, 12);
			game.batch.draw(arrow_left, 118, 95, 10, 12);
		} // если курсор наведен на кнопку skins
		if(is_mouse_statistics) {
			game.batch.draw(arrow_right, 170, 70, 10, 12);
			game.batch.draw(arrow_left, 118, 70, 10, 12);
		} // если курсор наведен на кнопку statistics
		
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
		camera.setToOrtho(false, 1080, 600); // масштабироавние экрана
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
		music_intro_fon.dispose();
		music_intro_fon2.dispose();
	}

}
