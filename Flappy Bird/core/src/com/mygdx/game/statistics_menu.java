package com.mygdx.game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

public class statistics_menu implements Screen{
	final Drop game;
    private OrthographicCamera camera;
    private Texture fon, button_to_main_menu, line, max_result_text;
    private Music music_fon;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    protected ImageButton button;
    protected Stage stage;
    boolean is_mouse_back = false;
    static int max_result_score;
    FileReader file_read;
	BitmapFont Font_score_result;
    
    public statistics_menu(Drop gam) {
		Gdx.graphics.setVSync(true); // vertical sync
		this.game = gam;
		camera = new OrthographicCamera(1920, 1080); // setting to hd (for later scaling)
		fon = new Texture("statistics_menu_fon.png");
		music_fon = Gdx.audio.newMusic(Gdx.files.internal("statistics_menu_music.mp3")); // background music
		line = new Texture("back_to_menu_line.png");
		max_result_text = new Texture("max_result_text.png");
		Font_score_result = new BitmapFont();
		
		try {
			file_read = new FileReader("..//assets/results.txt");
			int c;
		    String temp = "", deshifr, temp_temp;
            try {
				while((c=file_read.read())!=-1){
				    temp += (char)c;
				}
				if(temp == "") {
					max_result_score = 0;
				}
				else {
					temp_temp = temp;
					deshifr = decryption(temp_temp);
					max_result_score = Integer.parseInt(deshifr);
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
	    button = new ImageButton(myTexRegionDrawable); //Set the button up
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
		game.batch.draw(fon, 0, 0);
		Font_score_result.setColor(Color.WHITE);
		Font_score_result.getData().setScale(3, 2); // font size
		Font_score_result.draw(game.batch, "" + max_result_score, 530, 225); // display final resalt
		game.batch.draw(max_result_text, 20, 200, 500, 50);
		if(is_mouse_back) {
			game.batch.draw(line, 0, 464, 38, 1);
		}
		music_fon.play();
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			music_fon.stop();
        	camera = new OrthographicCamera(1080, 600); // setting to hd (for later scaling)
			game.setScreen(new MainMenuScreen(game));
			dispose();
		}// Exit to the menu
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
		
	}

}
