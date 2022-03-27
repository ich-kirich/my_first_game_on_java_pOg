package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

public class ExitMenu implements Screen{

	private Texture back, billy_sad, billy_happy, no, yes, arrow_left, arrow_right;
	final Drop game;
	private TextureRegion myTextureRegion, myTextureRegion1;
    private TextureRegionDrawable myTexRegionDrawable, myTexRegionDrawable1;
    protected ImageButton button, button1;
    protected Stage stage;
    private OrthographicCamera camera;
    boolean is_mouse_no = false, is_mouse_yes = false;
    Music music_fon;
	public ExitMenu(Drop gam) {
		this.game = gam;
		back = new Texture("menu_exit.png");
		arrow_left = new Texture("arrow_left.png");
		arrow_right = new Texture("arrow_right.png");
		billy_sad = new Texture("menu_exit_billy_sad.png");
		billy_happy = new Texture("menu_exit_billy_happy.png");
		camera = new OrthographicCamera(1920, 1080); // установка на hd (для последующего масштабирования)
		music_fon = Gdx.audio.newMusic(Gdx.files.internal("exit_menu_music_fon.mp3")); // фоновая музыка
		
		no = new Texture(Gdx.files.internal("menu_exit_no.png"));
		yes = new Texture(Gdx.files.internal("menu_exit_yes.png"));
	    myTextureRegion = new TextureRegion(yes);
	    myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
	    button = new ImageButton(myTexRegionDrawable); //Set the button up
	    button.setPosition(400, 250);
	    stage = new Stage(new ScreenViewport());
		stage.addActor(button); // кнопка YES
		
		myTextureRegion1 = new TextureRegion(no);
	    myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
		button1 = new ImageButton(myTexRegionDrawable1);
		button1.setPosition(1370, 255);
		stage.addActor(button1); // // кнопка NO
		
		button.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	music_fon.stop(); // прекращение проигрывания фоновой музыки
            	Gdx.app.exit();
    			dispose();
                return true;
            }
            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
            	is_mouse_yes = true;
            } // если курсор наведен
            
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            	is_mouse_yes = false;
            } // если не наведен курсор
        }); //  нажатие на кнопку YES
		
		Gdx.input.setInputProcessor(stage);
		button1.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	music_fon.stop(); // прекращение проигрывания фоновой музыки
            	camera = new OrthographicCamera(1080, 600); // установка на hd (для последующего масштабирования)
    			game.setScreen(new MainMenuScreen(game));
    			dispose();
                return true;
            }
            

            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
            	is_mouse_no = true;
            } // если курсор наведен
            
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            	is_mouse_no = false;
            }
            
        }); //  нажатие на кнопку NO
		

	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		ScreenUtils.clear(1, 1, 1, 1); // create background color
		//game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin(); // начало отрисовки
		music_fon.play(); // здесь фоновая музыка запускается
		game.batch.draw(back, -90, -60);
		if(is_mouse_no) {
			game.batch.draw(billy_happy, 250, 250);
			game.batch.draw(arrow_left, 431, 110);
			game.batch.draw(arrow_right, 500, 110);
		}
		if(is_mouse_yes) {
			game.batch.draw(billy_sad, 250, 250);
			game.batch.draw(arrow_left, 110, 110);
			game.batch.draw(arrow_right, 178, 110);
		}
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
		
	}

}
