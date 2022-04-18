package com.mygdx.game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private Texture back, button_to_main_menu, line, skin_default, is_choose, skin_billy, skin_stepan, skin_shlepa, skin_sanitar;
    private TextureRegion myTextureRegion, myTextureRegion1, myTextureRegion2, myTextureRegion3, myTextureRegion4, myTextureRegion5;
    private TextureRegionDrawable myTexRegionDrawable, myTexRegionDrawable1, myTexRegionDrawable2, myTexRegionDrawable3, myTexRegionDrawable4, myTexRegionDrawable5;
    protected ImageButton button, button1, button2, button3, button4, button5;
    protected Stage stage;
    boolean is_mouse_back = false, is_mouse_skin_billy, skin_billy_is_choose = false, skin_stepan_is_choose = false, is_mouse_skin_stepan, is_mouse_skin_shlepa, skin_shlepa_is_choose = false;
	boolean skin_default_is_choose = false, skin_sanitar_is_choose = false, is_mouse_skin_sanitar;
	boolean is_mouse_skin_default;
	static boolean is_choose_skin = false; // skin selection
	Music music_fon;
	Sound skin_default_sound, skin_billy_sound, skin_stepan_sound, skin_shlepa_sound, skin_sanitar_sound, skin_not_available;
    FileReader file_read;
    private int scores_from_file;
    private Texture text_for_skin_shlepa, text_for_skin_billy, text_for_skin_sanitar, text_for_skin_stepan;
	
	public skins_menu(Drop gam) {
		Gdx.graphics.setVSync(true); // vertical sync
		this.game = gam;
		camera = new OrthographicCamera(1920, 1080); // setting to hd (for later scaling)
		back = new Texture("skins_menu_fon.png");
		line = new Texture("back_to_menu_line.png");
		is_choose = new Texture("skin_is_choose.png");
		skin_shlepa = new Texture("skin_shlepa.png");
		skin_sanitar = new Texture("skin_sanitar.png");
		text_for_skin_shlepa = new Texture("text_for_skin_shlepa.png");
		text_for_skin_billy = new Texture("text_for_skin_billy.png");
		text_for_skin_sanitar = new Texture("text_for_skin_sanitar.png");
		text_for_skin_stepan = new Texture("text_for_skin_stepan.png");
		music_fon = Gdx.audio.newMusic(Gdx.files.internal("skins_menu_music.mp3")); // background music
		skin_default_sound = Gdx.audio.newSound(Gdx.files.internal("skin_default_sound.mp3"));
		skin_billy_sound = Gdx.audio.newSound(Gdx.files.internal("skin_billy_sound.mp3"));
		skin_stepan_sound = Gdx.audio.newSound(Gdx.files.internal("skin_stepan_sound.mp3"));
		skin_shlepa_sound = Gdx.audio.newSound(Gdx.files.internal("skin_shlepa_sound.mp3"));
		skin_sanitar_sound = Gdx.audio.newSound(Gdx.files.internal("skin_sanitar_sound.mp3"));
		skin_not_available = Gdx.audio.newSound(Gdx.files.internal("skin_not_available.mp3"));
		
		try {
			file_read = new FileReader("..//assets/results.txt");
			int c;
		    String temp = "", deshifr, temp_temp;
            try {
				while((c=file_read.read())!=-1){
				    temp += (char)c;
				}
				if(temp == "") {
					statistics_menu.max_result_score = 0;
				}
				else {
					temp_temp = temp;
					deshifr = decryption(temp_temp);
					scores_from_file = Integer.parseInt(deshifr);
				}
				file_read.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		button_to_main_menu = new Texture(Gdx.files.internal("back_to_menu.png"));
	    myTextureRegion = new TextureRegion(button_to_main_menu);
	    myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
	    button = new ImageButton(myTexRegionDrawable); // Set the button up
	    button.setPosition(10, 1050);
	    stage = new Stage(new ScreenViewport());
		stage.addActor(button); // button to menu
		
	    Gdx.input.setInputProcessor(stage);
		button.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	music_fon.stop();
            	camera = new OrthographicCamera(1080, 600); // setting to hd (for later scaling)
    			game.setScreen(new MainMenuScreen(game));
    			dispose();
                return true;
            }
            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
            	is_mouse_back = true;
            } // if the cursor is hovered
            
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            	is_mouse_back = false;
            } // if the cursor isn't hovered
        }); //  pressing the button to menu
		
		if(scores_from_file < 150) {
			skin_billy = new Texture(Gdx.files.internal("skin_billy_not_available.png"));
			myTextureRegion1 = new TextureRegion(skin_billy);
		    myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
		    button1 = new ImageButton(myTexRegionDrawable1); // Set the button up
		    button1.setPosition(800, 150);
		    button1.setTransform(true);
		    button1.setScale(7f);
			stage.addActor(button1); // skin billy not avaialable
			
			Gdx.input.setInputProcessor(stage);
			button1.addListener(new InputListener(){
	            @Override
	            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
	            	is_mouse_skin_billy = true;
	            	skin_not_available.play();
	            } // if the cursor is hovered
	            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
	            	is_mouse_skin_billy = false;
	            	skin_not_available.stop();
	            } // if the cursor isn't hovered
	        }); //  pressing the skin billy
		}
		else {
			skin_billy = new Texture(Gdx.files.internal("skin_billy.png"));
			myTextureRegion1 = new TextureRegion(skin_billy);
		    myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
		    button1 = new ImageButton(myTexRegionDrawable1); //Set the button up
		    button1.setPosition(800, 150);
		    button1.setTransform(true);
		    button1.setScale(7f);
			stage.addActor(button1); // skin billy
			
			Gdx.input.setInputProcessor(stage);
			button1.addListener(new InputListener(){
	            @Override
	            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	            	Bird.img = new Texture("skin_billy.png");
	            	Bird.fly = Gdx.audio.newMusic(Gdx.files.internal("fly_billy.mp3")); // flight sound
	            	is_choose_skin = true;
	            	skin_billy_is_choose = true;
	            	skin_default_is_choose= false;
	            	skin_stepan_is_choose = false;
	            	skin_shlepa_is_choose = false;
	            	skin_sanitar_is_choose = false;
	                return true;
	            }
	            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
	            	is_mouse_skin_billy = true;
	            	skin_billy_sound.play();
	            } // if the cursor is hovered
	            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
	            	is_mouse_skin_billy = false;
	            	skin_billy_sound.stop();
	            } // if the cursor isn't hovered
	        }); //  pressing the skin billy
		}
		
		skin_default = new Texture(Gdx.files.internal("skin_default.png"));
		myTextureRegion2 = new TextureRegion(skin_default);
	    myTexRegionDrawable2 = new TextureRegionDrawable(myTextureRegion2);
	    button2 = new ImageButton(myTexRegionDrawable2); //Set the button up
	    button2.setPosition(300, 150);
	    button2.setTransform(true);
	    button2.setScale(7f);
		stage.addActor(button2); // skin default
		
	    Gdx.input.setInputProcessor(stage);
		button2.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	Bird.img = new Texture("skin_default.png");
            	Bird.fly = Gdx.audio.newMusic(Gdx.files.internal("fly_default.mp3")); // flight sound
            	is_choose_skin = true;
            	skin_default_is_choose = true;
            	skin_billy_is_choose = false;
            	skin_stepan_is_choose = false;
            	skin_shlepa_is_choose = false;
            	skin_sanitar_is_choose = false;
                return true;
            }
            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
            	is_mouse_skin_default = true;
            	skin_default_sound.play();
            } // if the cursor is hovered
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            	is_mouse_skin_default = false;
            	skin_default_sound.stop();  
            } // if the cursor isn't hovered
        }); // pressing the skin billy
		
		if(scores_from_file < 500) {
			skin_stepan = new Texture(Gdx.files.internal("skin_stepan_not_available.png"));
			myTextureRegion3 = new TextureRegion(skin_stepan);
		    myTexRegionDrawable3 = new TextureRegionDrawable(myTextureRegion3);
		    button3 = new ImageButton(myTexRegionDrawable3); //Set the button up
		    button3.setPosition(1300, 150);
		    button3.setTransform(true);
		    button3.setScale(7f);
			stage.addActor(button3); // skin stepan
			
		    Gdx.input.setInputProcessor(stage);
			button3.addListener(new InputListener(){
	            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
	            	is_mouse_skin_stepan = true;
	            	skin_not_available.play();
	            } // if the cursor is hovered
	            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
	            	is_mouse_skin_stepan = false;
	            	skin_not_available.stop();
	            } // if the cursor isn't hovered
	        }); // pressing the skin stepan
		}
		else {
			skin_stepan = new Texture(Gdx.files.internal("skin_stepan.png"));
			myTextureRegion3 = new TextureRegion(skin_stepan);
		    myTexRegionDrawable3 = new TextureRegionDrawable(myTextureRegion3);
		    button3 = new ImageButton(myTexRegionDrawable3); //Set the button up
		    button3.setPosition(1300, 150);
		    button3.setTransform(true);
		    button3.setScale(7f);
			stage.addActor(button3); // skin stepan
			
		    Gdx.input.setInputProcessor(stage);
			button3.addListener(new InputListener(){
	            @Override
	            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	            	Bird.img = new Texture("skin_stepan.png");
	            	Bird.fly = Gdx.audio.newMusic(Gdx.files.internal("fly_stepan1.mp3")); // flight sound
	            	is_choose_skin = true;
	            	skin_stepan_is_choose = true;
	            	skin_default_is_choose = false;
	            	skin_billy_is_choose = false;
	            	skin_shlepa_is_choose = false;
	            	skin_sanitar_is_choose = false;
	                return true;
	            }
	            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
	            	is_mouse_skin_stepan = true;
	            	skin_stepan_sound.play();
	            } // if the cursor is hovered
	            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
	            	is_mouse_skin_stepan = false;
	            	skin_stepan_sound.stop();
	            } // if the cursor isn't hovered
	        }); // pressing the skin stepan
		}
		
		if(scores_from_file < 50) {
			skin_shlepa = new Texture(Gdx.files.internal("skin_shlepa_not_available.png"));
			myTextureRegion4 = new TextureRegion(skin_shlepa);
		    myTexRegionDrawable4 = new TextureRegionDrawable(myTextureRegion4);
		    button4 = new ImageButton(myTexRegionDrawable4); //Set the button up
		    button4.setPosition(300, 650);
		    button4.setTransform(true);
		    button4.setScale(7f);
			stage.addActor(button4); // skin shlepa
			
		    Gdx.input.setInputProcessor(stage);
			button4.addListener(new InputListener(){
	            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
	            	is_mouse_skin_shlepa = true;
	            	skin_not_available.play();
	            } // if the cursor is hovered
	            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
	            	is_mouse_skin_shlepa = false;
	            	skin_not_available.stop();
	            } // if the cursor isn't hovered
	        }); // skin shlepa not available
		}
		else {
			skin_shlepa = new Texture(Gdx.files.internal("skin_shlepa.png"));
			myTextureRegion4 = new TextureRegion(skin_shlepa);
		    myTexRegionDrawable4 = new TextureRegionDrawable(myTextureRegion4);
		    button4 = new ImageButton(myTexRegionDrawable4); //Set the button up
		    button4.setPosition(300, 650);
		    button4.setTransform(true);
		    button4.setScale(7f);
			stage.addActor(button4); // skin shlepa
			
		    Gdx.input.setInputProcessor(stage);
			button4.addListener(new InputListener(){
	            @Override
	            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	            	Bird.img = new Texture("skin_shlepa.png");
	            	Bird.fly = Gdx.audio.newMusic(Gdx.files.internal("fly_shlepa.mp3")); // flight sound
	            	is_choose_skin = true;
	            	skin_shlepa_is_choose = true;
	            	skin_billy_is_choose = false;
	            	skin_default_is_choose= false;
	            	skin_stepan_is_choose = false;
	            	skin_sanitar_is_choose = false;
	                return true;
	            }
	            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
	            	is_mouse_skin_shlepa = true;
	            	skin_shlepa_sound.play();
	            } // if the cursor is hovered
	            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
	            	is_mouse_skin_shlepa = false;
	            	skin_shlepa_sound.stop();
	            } // if the cursor isn't hovered
	        }); // pressing the skin shlepa
		}
		
		if(scores_from_file < 300) {
			skin_sanitar = new Texture(Gdx.files.internal("skin_sanitar_not_available.png"));
			myTextureRegion5 = new TextureRegion(skin_sanitar);
		    myTexRegionDrawable5 = new TextureRegionDrawable(myTextureRegion5);
		    button5 = new ImageButton(myTexRegionDrawable5); //Set the button up
		    button5.setPosition(1300, 650);
		    button5.setTransform(true);
		    button5.setScale(7f);
			stage.addActor(button5); // skin shlepa
			
		    Gdx.input.setInputProcessor(stage);
			button5.addListener(new InputListener(){
	            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
	            	is_mouse_skin_sanitar = true;
	            	skin_not_available.play();
	            } // if the cursor is hovered
	            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
	            	is_mouse_skin_sanitar = false;
	            	skin_not_available.stop();
	            } // if the cursor isn't hovered
	        }); //  skin sanitar not available
		}
		else {
			skin_sanitar = new Texture(Gdx.files.internal("skin_sanitar.png"));
			myTextureRegion5 = new TextureRegion(skin_sanitar);
		    myTexRegionDrawable5 = new TextureRegionDrawable(myTextureRegion5);
		    button5 = new ImageButton(myTexRegionDrawable5); //Set the button up
		    button5.setPosition(1300, 650);
		    button5.setTransform(true);
		    button5.setScale(7f);
			stage.addActor(button5); // skin sanitar
			
		    Gdx.input.setInputProcessor(stage);
			button5.addListener(new InputListener(){
	            @Override
	            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	            	Bird.img = new Texture("skin_sanitar.png");
	            	Bird.fly = Gdx.audio.newMusic(Gdx.files.internal("fly_sanitar.mp3")); // flight sound
	            	is_choose_skin = true;
	            	skin_sanitar_is_choose = true;
	            	skin_shlepa_is_choose = false;
	            	skin_billy_is_choose = false;
	            	skin_default_is_choose= false;
	            	skin_stepan_is_choose = false;
	                return true;
	            }
	            public void enter(InputEvent event, float x, float y, int pointer,  @Null Actor fromActor) {
	            	is_mouse_skin_sanitar = true;
	            	skin_sanitar_sound.play();
	            } // if the cursor is hovered
	            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
	            	is_mouse_skin_sanitar = false;
	            	skin_sanitar_sound.stop();
	            } // if the cursor isn't hovered
	        }); // pressing the skin sanitar
		}
	}
	
	public String decryption(String temp) {
		String temp_deshifr = "";
		for(int i = 0; i < temp.length(); i++) {
			if(temp.charAt(i) == ')') {
				temp_deshifr += "0";
			}
			else if(temp.charAt(i) == 'l') {
				temp_deshifr += "1";
			}
			else if(temp.charAt(i) == 'b') {
				temp_deshifr += "2";
			}
			else if(temp.charAt(i) == 'z') {
				temp_deshifr += "3";
			}
			else if(temp.charAt(i) == 'v') {
				temp_deshifr += "4";
			}
			else if(temp.charAt(i) == '<') {
				temp_deshifr += "5";
			}
			else if(temp.charAt(i) == '[') {
				temp_deshifr += "6";
			}
			else if(temp.charAt(i) == '$') {
				temp_deshifr += "7";
			}
			else if(temp.charAt(i) == '!') {
				temp_deshifr += "8";
			}
			else if(temp.charAt(i) == 'j') {
				temp_deshifr += "9";
			}
		}
		return temp_deshifr;
	} // decryption
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		ScreenUtils.clear(1, 1, 1, 1); // create background color
		game.batch.begin(); // start drawing
		game.batch.draw(back, 0, 0);
		music_fon.play();
		music_fon.setVolume(0.3f);
		if(is_mouse_back) {
			game.batch.draw(line, 0, 464, 38, 1);
		}
		
		if(is_mouse_skin_billy) {
			game.batch.draw(line, 270, 50, 140, 1);
			if(scores_from_file < 150) {
				game.batch.draw(text_for_skin_billy, 80, 10, 500, 40);
			}
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
			if(scores_from_file < 500) {
				game.batch.draw(text_for_skin_stepan, 80, 10, 500, 40);
			}
		}
		if(skin_stepan_is_choose) {
			game.batch.draw(is_choose, 465, 220, 20, 20);
		}
		
		if(is_mouse_skin_shlepa) {
			game.batch.draw(line, 100, 280, 140, 1);
			if(scores_from_file < 50) {
				game.batch.draw(text_for_skin_shlepa, 80, 10, 500, 40);
			}
		}
		if(skin_shlepa_is_choose) {
			game.batch.draw(is_choose, 150, 450, 20, 20);
		}
		
		if(is_mouse_skin_sanitar) {
			game.batch.draw(line, 425, 280, 140, 1);
			if(scores_from_file < 300) {
				game.batch.draw(text_for_skin_sanitar, 80, 10, 500, 40);
			}
		}
		if(skin_sanitar_is_choose) {
			game.batch.draw(is_choose, 480, 450, 20, 20);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			music_fon.stop();
        	camera = new OrthographicCamera(1080, 600); // setting to hd (for later scaling)
			game.setScreen(new MainMenuScreen(game));
			dispose();
		}// Выход в меню
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
