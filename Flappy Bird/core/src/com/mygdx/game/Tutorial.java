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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Tutorial implements Screen{
	final Drop game;
	private OrthographicCamera camera;
    private Texture button_to_main_menu, line, fon;
    private Music music_fon;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    protected ImageButton button;
    protected Stage stage;
    boolean is_mouse_back = false;
    
	public Tutorial(final Drop gam) {
		Gdx.graphics.setVSync(true); // вертикальная синхронизация
		this.game = gam;
		camera = new OrthographicCamera(1920, 1080); // установка на hd (для последующего масштабирования)
		line = new Texture("back_to_menu_line.png");
		fon = new Texture("tutorial_fon.png");
		music_fon = Gdx.audio.newMusic(Gdx.files.internal("tutorial_music_fon.mp3")); // фоновая музыка
		
		button_to_main_menu = new Texture(Gdx.files.internal("back_to_menu.png"));
	    myTextureRegion = new TextureRegion(button_to_main_menu);
	    myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
	    button = new ImageButton(myTexRegionDrawable); //Set the button up
	    button.setPosition(10, 1050);
	    stage = new Stage(new ScreenViewport());
		stage.addActor(button); // кнопка В МЕНЮ
		
	    Gdx.input.setInputProcessor(stage);
		button.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	music_fon.stop();
            	camera = new OrthographicCamera(1080, 600); // установка на hd (для последующего масштабирования)
    			game.setScreen(new MainMenuScreen(game));
    			dispose();
                return true;
            }
            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
            	is_mouse_back = true;
            } // если курсор наведен
            
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            	is_mouse_back = false;
            } // если не наведен курсор
        }); //  нажатие на кнопку В МЕНЮ
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		ScreenUtils.clear(1, 1, 1, 1); // create background color
		game.batch.begin(); // начало отрисовки
		game.batch.draw(fon, 0, 0, 650, 490); // гифка
		if(is_mouse_back) {
			game.batch.draw(line, 0, 464, 38, 1);
		}
		music_fon.play();
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			music_fon.stop();
			game.setScreen(new MainMenuScreen(game));
			dispose();
		}
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
		music_fon.dispose();
		line.dispose();
		button_to_main_menu.dispose();
	}

}
