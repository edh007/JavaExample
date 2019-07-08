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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame implements ApplicationListener {
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private Pixmap pixmap;

	@Override
	public void create () {
		batch = new SpriteBatch();

		pixmap = new Pixmap(256, 128, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.RED);
		pixmap.fill();
		pixmap.setColor(Color.BLACK);
		pixmap.drawLine(0, 0, pixmap.getWidth()-1, pixmap.getHeight()-1);
		pixmap.drawLine(0, pixmap.getHeight()-1, pixmap.getWidth()-1, 0);

		pixmap.setColor(Color.YELLOW);
		pixmap.drawCircle(pixmap.getWidth()/2, pixmap.getHeight()/2, pixmap.getHeight()/2 - 1);

		//texture = new Texture(Gdx.files.internal("data/jet.png"));
		texture = new Texture(pixmap);

		pixmap.dispose();

		sprite = new Sprite(texture);
	}
	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		sprite.setPosition(0, 0);
		sprite.draw(batch);
		sprite.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		sprite.draw(batch);
		batch.end();
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
