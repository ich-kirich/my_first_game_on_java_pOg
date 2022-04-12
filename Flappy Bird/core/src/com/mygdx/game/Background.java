package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {
	
	static class BGPicture{
		public static Texture tx_1;
		public static Texture tx_2;
		public static Texture tx_3;
		public static Texture tx_4;
		public static Texture tx_5;
		private Vector2 pos;
		public BGPicture(Vector2 pos) {
			tx_1 = new Texture("fon1.png");
			tx_2 = new Texture("fon2.png");
			tx_3 = new Texture("fon3.png");
			tx_4 = new Texture("fon4.png");
			tx_5 = new Texture("fon5.png");
			this.pos = pos;
		}
	}
	private int speed;
	private BGPicture[] backs;
	static boolean new_fon = true, promezh_fon = true;
	
	public Background() {
		speed = 4;
		backs = new BGPicture[2];
		backs[0] = new BGPicture(new Vector2(0, 0));
		backs[1] = new BGPicture(new Vector2(1080, 0));
	}
	
	public void render(SpriteBatch batch) {
		if(((MyGdxGame.score / 16) >= 100) && ((MyGdxGame.score / 16) < 300)) {
			for(int i = 0; i < backs.length; i++) {
				batch.draw(backs[i].tx_1, backs[i].pos.x, backs[i].pos.y);
			}
			if(backs[1].pos.x >= 890) {
				promezh_fon = false;
			}
			if(promezh_fon == false) {
				batch.draw(backs[1].tx_2, backs[1].pos.x, backs[1].pos.y);
			}
			if(backs[0].pos.x == -1080 && promezh_fon == false) {
				new_fon = false;
			}
			if(new_fon == false) {
				for(int i = 0; i < backs.length; i++) {
					batch.draw(backs[i].tx_2, backs[i].pos.x, backs[i].pos.y);
				}
			}
		}
		if((MyGdxGame.score / 16) >= 300 && ((MyGdxGame.score / 16) < 500)) {
			for(int i = 0; i < backs.length; i++) {
				batch.draw(backs[i].tx_2, backs[i].pos.x, backs[i].pos.y);
			}
			if((MyGdxGame.score / 16) == 300) {
				promezh_fon = true;
				new_fon = true;
			}
			if(backs[1].pos.x >= 890) {
				promezh_fon = false;
			}
			if(promezh_fon == false) {
				batch.draw(backs[1].tx_3, backs[1].pos.x, backs[1].pos.y);
			}
			if(backs[0].pos.x == -1080 && promezh_fon == false) {
				new_fon = false;
			}
			if(new_fon == false) {
				for(int i = 0; i < backs.length; i++) {
					batch.draw(backs[i].tx_3, backs[i].pos.x, backs[i].pos.y);
				}
			}
		}
		if((MyGdxGame.score / 16) >= 500 && ((MyGdxGame.score / 16) < 1000)) {
			for(int i = 0; i < backs.length; i++) {
				batch.draw(backs[i].tx_3, backs[i].pos.x, backs[i].pos.y);
			}
			if((MyGdxGame.score / 16) == 500) {
				promezh_fon = true;
				new_fon = true;
			}
			if(backs[1].pos.x >= 890) {
				promezh_fon = false;
			}
			if(promezh_fon == false) {
				batch.draw(backs[1].tx_4, backs[1].pos.x, backs[1].pos.y);
			}
			if(backs[0].pos.x == -1080 && promezh_fon == false) {
				new_fon = false;
			}
			if(new_fon == false) {
				for(int i = 0; i < backs.length; i++) {
					batch.draw(backs[i].tx_4, backs[i].pos.x, backs[i].pos.y);
				}
			}
		}
		if((MyGdxGame.score / 16) >= 1000) {
			for(int i = 0; i < backs.length; i++) {
				batch.draw(backs[i].tx_4, backs[i].pos.x, backs[i].pos.y);
			}
			if((MyGdxGame.score / 16) == 1000) {
				promezh_fon = true;
				new_fon = true;
			}
			promezh_fon = true;
			new_fon = true;
			if(backs[1].pos.x >= 890) {
				promezh_fon = false;
			}
			if(promezh_fon == false) {
				batch.draw(backs[1].tx_5, backs[1].pos.x, backs[1].pos.y);
			}
			if(backs[0].pos.x == -1080 && promezh_fon == false) {
				new_fon = false;
			}
			if(new_fon == false) {
				for(int i = 0; i < backs.length; i++) {
					batch.draw(backs[i].tx_5, backs[i].pos.x, backs[i].pos.y);
				}
			}
		}
	}
	
	public void update() {
		for(int i = 0; i < backs.length; i++) {
			backs[i].pos.x -= speed;
		}
		if(backs[0].pos.x < -1080) {
			backs[0].pos.x = 0;
			backs[1].pos.x = 1080;
		}
	}
}
