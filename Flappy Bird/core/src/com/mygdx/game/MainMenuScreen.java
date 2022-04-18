package com.mygdx.game;

import com.badlogic.gdx.Gdx;
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

public class MainMenuScreen implements Screen{
	
	final Drop game;
	private OrthographicCamera camera;
	Texture bg_menu;
	private Texture arrow_left, arrow_right;
	Music music_intro_fon, music_intro_fon2;
	int random_choose_menu = (int)(Math.random() * ((2 - 1) + 1)) + 1; // random number
    private Texture myTexture, myTexture1, myTexture2, button_statistics, button_tutorial;
    private TextureRegion myTextureRegion, myTextureRegion1, myTextureRegion2, myTextureRegion3, myTextureRegion4;
    private TextureRegionDrawable myTexRegionDrawable, myTexRegionDrawable1, myTexRegionDrawable2, myTexRegionDrawable3, myTexRegionDrawable4;
    protected ImageButton button, button1, button_to_skins, button_to_statistics, button_to_tutorial;
    protected Stage stage;
    private boolean is_mouse_play = false, is_mouse_exit = false, is_mouse_skins = false, is_mouse_statistics = false, is_mouse_tutorial = false;
	
	public MainMenuScreen(Drop gam) {
		Gdx.graphics.setVSync(true); // vertical sync
		this.game = gam;
		bg_menu = new Texture("menu.png");
		music_intro_fon = Gdx.audio.newMusic(Gdx.files.internal("intro_music.mp3")); // background music
		music_intro_fon2 = Gdx.audio.newMusic(Gdx.files.internal("intro_music2.mp3")); // background music
		camera = new OrthographicCamera(1920, 1080); // setting to hd (for later scaling)
		arrow_left = new Texture("arrow_left.png");
		arrow_right = new Texture("arrow_right.png");
		
		myTexture = new Texture(Gdx.files.internal("button_play.png"));
		myTexture1 = new Texture(Gdx.files.internal("button_exit.png"));
	    myTextureRegion = new TextureRegion(myTexture);
	    myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
	    button = new ImageButton(myTexRegionDrawable); //Set the button up
	    button.setPosition(400, 270);
	    stage = new Stage(new ScreenViewport());
		stage.addActor(button); // button play
		
		myTextureRegion1 = new TextureRegion(myTexture1);
	    myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
		button1 = new ImageButton(myTexRegionDrawable1);
		button1.setPosition(400, 53);
		stage.addActor(button1); // button exit
		
		myTexture2 = new Texture(Gdx.files.internal("button_to_skins_menu.png"));
		myTextureRegion2 = new TextureRegion(myTexture2);
	    myTexRegionDrawable2 = new TextureRegionDrawable(myTextureRegion2);
	    button_to_skins = new ImageButton(myTexRegionDrawable2);
		button_to_skins.setPosition(400, 215);
		stage.addActor(button_to_skins); // button skins
		
		button_statistics = new Texture(Gdx.files.internal("button_statistics.png"));
	    myTextureRegion3 = new TextureRegion(button_statistics);
	    myTexRegionDrawable3 = new TextureRegionDrawable(myTextureRegion3);
	    button_to_statistics = new ImageButton(myTexRegionDrawable3); //Set the button up
	    button_to_statistics.setPosition(400, 157);
	    button_to_statistics.setTransform(true);
	    button_to_statistics.setScaleY(1.2f);;
		stage.addActor(button_to_statistics); // button statistics
		
		button_tutorial = new Texture(Gdx.files.internal("button_tutorial.png"));
	    myTextureRegion4 = new TextureRegion(button_tutorial);
	    myTexRegionDrawable4 = new TextureRegionDrawable(myTextureRegion4);
	    button_to_tutorial = new ImageButton(myTexRegionDrawable4); //Set the button up
	    button_to_tutorial.setPosition(400, 105);
		stage.addActor(button_to_tutorial); // button tutorial
		
		Gdx.input.setInputProcessor(stage); // start accepting button presses
		button.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	music_intro_fon.stop(); // stop playing background music
    			music_intro_fon2.stop(); // stop playing background music
    			game.setScreen(new MyGdxGame(game));
    			Background.new_fon = true; // it is for background in Class Background
    			Background.promezh_fon = true; // it is for background in Class Background
    			dispose();
                return true;
            }
            
            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
            	is_mouse_play = true;
            } // if the cursor is hovered
            
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            	is_mouse_play = false;
            } // if the cursor isn't hovered
            
        }); // pressing the button play
		
		Gdx.input.setInputProcessor(stage);
	    button1.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	music_intro_fon.stop(); // stop playing background music
    			music_intro_fon2.stop(); // stop playing background music
    			game.setScreen(new ExitMenu(game));
            	dispose();
                return true;
            }
            
            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
            	is_mouse_exit = true;
            } // if the cursor is hovered
            
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            	is_mouse_exit = false;
            } // if the cursor isn't hovered
        }); // pressing the button exit
	    
	    Gdx.input.setInputProcessor(stage);
	    button_to_skins.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	music_intro_fon.stop(); // stop playing background music
    			music_intro_fon2.stop(); // stop playing background music
    			game.setScreen(new skins_menu(game));
            	dispose();
                return true;
            }
            
            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
            	is_mouse_skins = true;
            } // if the cursor is hovered
            
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            	is_mouse_skins = false;
            } // if the cursor isn't hovered
        }); // pressing the button skins
	    
	    Gdx.input.setInputProcessor(stage);
	    button_to_statistics.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	music_intro_fon.stop(); // stop playing background music
    			music_intro_fon2.stop(); // stop playing background music
    			game.setScreen(new statistics_menu(game));
    			Background.new_fon = true; // it is for background in Class Background
    			Background.promezh_fon = true; // it is for background in Class Background
    			dispose();
                return true;
            }
            
            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
            	is_mouse_statistics = true;
            } // if the cursor is hovered
            
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            	is_mouse_statistics = false;
            } // if the cursor isn't hovered
            
        }); // pressing the button statistics
	    
		Gdx.input.setInputProcessor(stage);
		button_to_tutorial.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	music_intro_fon.stop(); // stop playing background music
    			music_intro_fon2.stop(); // stop playing background music
    			game.setScreen(new Tutorial(game));
    			dispose();
                return true;
            }
            
            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
            	is_mouse_tutorial = true;
            } // if the cursor is hovered
            
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            	is_mouse_tutorial = false;
            } // if the cursor isn't hovered
            
        }); //  pressing the button tutorial
	}
	

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(1, 1, 1, 1); // create background color
		game.batch.begin(); // start drawing
		game.batch.draw(bg_menu, 0, -100);
		
		if(is_mouse_exit) {
			game.batch.draw(arrow_right, 170, 25, 10, 12);
			game.batch.draw(arrow_left, 118, 25, 10, 12);
		}// if hovered over a button exit
		if(is_mouse_play) {
			game.batch.draw(arrow_right, 170, 119, 10, 12);
			game.batch.draw(arrow_left, 118, 119, 10, 12);
		} // if hovered over a button play
		if(is_mouse_skins) {
			game.batch.draw(arrow_right, 170, 95, 10, 12);
			game.batch.draw(arrow_left, 118, 95, 10, 12);
		} // if hovered over a button skins
		if(is_mouse_statistics) {
			game.batch.draw(arrow_right, 170, 70, 10, 12);
			game.batch.draw(arrow_left, 118, 70, 10, 12);
		} // if hovered over a button statistics
		if(is_mouse_tutorial) {
			game.batch.draw(arrow_right, 170, 45, 10, 12);
			game.batch.draw(arrow_left, 118, 45, 10, 12);
		} // if hovered over a button tutorial
		
		switch(random_choose_menu) {
		case 1:
			music_intro_fon.play(); // here the background music starts
			break;
		case 2:
			music_intro_fon2.play(); // here the background music starts
			break;
		default:
			System.out.println("Что пошло не так с музыкой фоновой в меню......");
		} // random background music
		game.batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime()); // button rendering
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		camera.setToOrtho(false, 1080, 600); // screen scaling
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
