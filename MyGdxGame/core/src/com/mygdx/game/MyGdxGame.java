//package com.mygdx.game;
//
//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//
//public class MyGdxGame extends ApplicationAdapter {
//	SpriteBatch batch;
//	Texture img;
//
//	@Override
//	public void create () {
//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
//	}
//
//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
//	}
//
//	@Override
//	public void dispose () {
//		batch.dispose();
//		img.dispose();
//	}
//}

package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class MyGdxGame implements ApplicationListener, InputProcessor {
	private SpriteBatch batch;
	private TextureAtlas textureAtlas;
	private Sprite sprite;
	private Pixmap pixmap;
	private int currentFrame = 1;
	private String currentAtlasKey = new String("0001");

	private float posX, posY;

	@Override
	public void create () {
		batch = new SpriteBatch();

		textureAtlas = new TextureAtlas(Gdx.files.internal("data/spritesheet.atlas"));
		TextureAtlas.AtlasRegion region = textureAtlas.findRegion("0001");

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		sprite = new Sprite(region);
		posX = w/2 - sprite.getWidth()/2;
		posY = h/2 - sprite.getHeight()/2;
		sprite.setPosition(posX, posY);
		sprite.scale(2.5f);
		Timer.schedule(new Task() {
			@Override
			public  void run() {
				currentFrame++;
				if(currentFrame > 20)
					currentFrame = 1;
				//currentAtlasKey = String.format("%04d", currentFrame);
				String base = new String();

				if(currentFrame >= 10)
					base = "00";
				else
					base = "000";

				currentAtlasKey = base + currentFrame;

				sprite.setRegion(textureAtlas.findRegion(currentAtlasKey));
			}
		}, 0, 1/30.0f);

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void dispose() {
		batch.dispose();
		textureAtlas.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//controlMouse();
		sprite.setPosition(posX,posY);

		batch.begin();
//		sprite.setPosition(0, 0);
		sprite.draw(batch);
//		sprite.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
//		sprite.draw(batch);
		batch.end();
	}

	@Override
	public boolean keyDown(int keycode) {
		float moveAmount = 1.0f;
		if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT))
			moveAmount = 10.0f;

		if(keycode == Keys.LEFT)
			posX-=moveAmount;
		if(keycode == Keys.RIGHT)
			posX+=moveAmount;
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(button == Buttons.LEFT){
			posX = screenX - sprite.getWidth()/2;
			posY = Gdx.graphics.getHeight() - screenY - sprite.getHeight()/2;
		}
		if(button == Buttons.RIGHT){
			posX = Gdx.graphics.getWidth()/2 - sprite.getWidth()/2;
			posY = Gdx.graphics.getHeight()/2 - sprite.getHeight()/2;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	public void controlMouse()
	{
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
		{
			sprite.setPosition(Gdx.input.getX() - sprite.getWidth()/2,
					Gdx.graphics.getHeight() - Gdx.input.getY() - sprite.getHeight()/2);
		}
		if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
		{
			sprite.setPosition(Gdx.graphics.getWidth()/2 -sprite.getWidth()/2,
					Gdx.graphics.getHeight()/2 - sprite.getHeight()/2);
		}
	}

	public void controlKeyboard()
	{
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
				sprite.translateX(-1f);
			else
				sprite.translateX(-10f);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
				sprite.translateX(1f);
			else
				sprite.translateX(10f);
		}
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}
}
