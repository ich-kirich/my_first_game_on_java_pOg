package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

public class skins_menu implements Screen{
	final Drop game;
    private OrthographicCamera camera;
    private Texture back, button_to_main_menu, line, skin_default, is_choose, skin_billy, skin_stepan;
    private TextureRegion myTextureRegion, myTextureRegion1, myTextureRegion2, myTextureRegion3;
    private TextureRegionDrawable myTexRegionDrawable, myTexRegionDrawable1, myTexRegionDrawable2, myTexRegionDrawable3;
    protected ImageButton button, button1, button2, button3;
    protected Stage stage;
    boolean is_mouse_back = false, is_mouse_skin_billy, skin_billy_is_choose = false, skin_stepan_is_choose = false, is_mouse_skin_stepan;
	boolean skin_default_is_choose = false;
	boolean is_mouse_skin_default;
	static boolean is_choose_skin = false; // переменная выбора скина
	Music music_fon;
	Sound skin_default_sound, skin_billy_sound, skin_stepan_sound;
	
	public skins_menu(Drop gam) {
		Gdx.graphics.setVSync(true); // вертикальная синхронизация
		this.game = gam;
		camera = new OrthographicCamera(1920, 1080); // установка на hd (для последующего масштабирования)
		back = new Texture("skins_menu_fon.png");
		line = new Texture("back_to_menu_line.png");
		is_choose = new Texture("skin_is_choose.png");
		music_fon = Gdx.audio.newMusic(Gdx.files.internal("skins_menu_music.mp3")); // фоновая музыка
		skin_default_sound = Gdx.audio.newSound(Gdx.files.internal("skin_default_sound.mp3"));
		skin_billy_sound = Gdx.audio.newSound(Gdx.files.internal("skin_billy_sound.mp3"));
		skin_stepan_sound = Gdx.audio.newSound(Gdx.files.internal("skin_stepan_sound.mp3"));
		
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
        }); //  нажатие на кнопку YES
		
		skin_billy = new Texture(Gdx.files.internal("skin_billy.png"));
		myTextureRegion1 = new TextureRegion(skin_billy);
	    myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
	    button1 = new ImageButton(myTexRegionDrawable1); //Set the button up
	    button1.setPosition(800, 150);
	    button1.setTransform(true);
	    button1.setScale(7f);
		stage.addActor(button1); // скин billy
		
	    Gdx.input.setInputProcessor(stage);
		button1.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	Bird.img = new Texture("skin_billy.png");
            	Bird.fly = Gdx.audio.newMusic(Gdx.files.internal("fly_billy.mp3")); // при полете звук
            	is_choose_skin = true;
            	skin_billy_is_choose = true;
            	skin_default_is_choose= false;
            	skin_stepan_is_choose = false;
                return true;
            }
            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
            	is_mouse_skin_billy = true;
            	skin_billy_sound.play();
            } // если курсор наведен
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            	is_mouse_skin_billy = false;
            	skin_billy_sound.stop();
            } // если не наведен курсор
        }); //  Нажатие на скин билли
		
		skin_default = new Texture(Gdx.files.internal("skin_default.png"));
		myTextureRegion2 = new TextureRegion(skin_default);
	    myTexRegionDrawable2 = new TextureRegionDrawable(myTextureRegion2);
	    button2 = new ImageButton(myTexRegionDrawable2); //Set the button up
	    button2.setPosition(300, 150);
	    button2.setTransform(true);
	    button2.setScale(7f);
		stage.addActor(button2); // скин по умолчанию
		
	    Gdx.input.setInputProcessor(stage);
		button2.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	Bird.img = new Texture("skin_default.png");
            	Bird.fly = Gdx.audio.newMusic(Gdx.files.internal("fly_default.mp3")); // при полете звук
            	is_choose_skin = true;
            	skin_default_is_choose = true;
            	skin_billy_is_choose = false;
            	skin_stepan_is_choose = false;
                return true;
            }
            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
            	is_mouse_skin_default = true;
            	skin_default_sound.play();
            } // если курсор наведен
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            	is_mouse_skin_default = false;
            	skin_default_sound.stop();  
            } // если не наведен курсор
        }); //  Нажатие на скин стандартный
		
		skin_stepan = new Texture(Gdx.files.internal("skin_stepan.png"));
		myTextureRegion3 = new TextureRegion(skin_stepan);
	    myTexRegionDrawable3 = new TextureRegionDrawable(myTextureRegion3);
	    button3 = new ImageButton(myTexRegionDrawable3); //Set the button up
	    button3.setPosition(1300, 150);
	    button3.setTransform(true);
	    button3.setScale(7f);
		stage.addActor(button3); // скин степана
		
	    Gdx.input.setInputProcessor(stage);
		button3.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	Bird.img = new Texture("skin_stepan.png");
            	Bird.fly = Gdx.audio.newMusic(Gdx.files.internal("fly_stepan1.mp3")); // при полете степана
            	is_choose_skin = true;
            	skin_stepan_is_choose = true;
            	skin_default_is_choose = false;
            	skin_billy_is_choose = false;
                return true;
            }
            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
            	is_mouse_skin_stepan = true;
            	skin_stepan_sound.play();
            } // если курсор наведен
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            	is_mouse_skin_stepan = false;
            	skin_stepan_sound.stop();
            } // если не наведен курсор
        }); //  Нажатие на скин степана
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		ScreenUtils.clear(1, 1, 1, 1); // create background color
		game.batch.begin(); // начало отрисовки
		game.batch.draw(back, 0, 0);
		music_fon.play();
		music_fon.setVolume(0.5f);
		if(is_mouse_back) {
			game.batch.draw(line, 0, 464, 38, 1);
		}
		
		if(is_mouse_skin_billy) {
			game.batch.draw(line, 270, 50, 140, 1);
		}
		if(skin_billy_is_choose) {
			game.batch.draw(is_choose, 310, 220, 20, 20);
		}
		
		if(is_mouse_skin_default) {
			game.batch.draw(line, 100, 50, 140, 1);
		}
		if(skin_default_is_choose) {
			game.batch.draw(is_choose, 155, 220, 20, 20);
		}
		
		if(is_mouse_skin_stepan) {
			game.batch.draw(line, 440, 50, 140, 1);
		}
		if(skin_stepan_is_choose) {
			game.batch.draw(is_choose, 465, 220, 20, 20);
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
		skin_stepan_sound.dispose();
		skin_default_sound.dispose();
		skin_billy_sound.dispose();
		back.dispose();
		button_to_main_menu.dispose();
		line.dispose();
		skin_default.dispose();
		is_choose.dispose();
		skin_billy.dispose();
		skin_stepan.dispose();
		
	}
		
}
