package com.burakguvenc.survivorbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

public class SurvivorBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture mauntain;
	Texture mauntain2;
	Texture bird;
	Texture alp;
	Texture bee1;
	Texture bee2;
	Texture bee3;
	float arkaplanX=0;
	float arkaplan2X=0;
	float arkaplan3X=0;
	float arkaplan4X=0;
	float birdX=0;
	float birdY=0;
	int gamestate=0;
	float velocity=0;
	float gravity=0.30f;
	float enemyVelocity=2;
	float hız=2f;
	float alpX=0;
	float alpY=0;
	float enemyhız=2f;
	Random random;
	int score=0;
	int scoredEnemy=0;
	BitmapFont font;
	BitmapFont font2;
	BitmapFont font3;
	BitmapFont font4;

	Circle birdCircle;
	ShapeRenderer shapeRenderer;

	int numberOfEnemy=4;
	float[] enemyX=new float[numberOfEnemy];

	float[] enemyOffSet=new float[numberOfEnemy];
	float[] enemyOffSet2=new float[numberOfEnemy];
	float[] enemyOffSet3=new float[numberOfEnemy];
	float distance=0;

	Circle[]enemyCircle;
	Circle[]enemyCircle2;
	Circle[]enemyCircle3;


	@Override
	public void create () {
	batch=new SpriteBatch();
	mauntain=new Texture("mauntain.png");
	mauntain2=new Texture("mauntain2.png");
	bird=new Texture("bluebird.png");
	//alp=new Texture("Alp2.png");
	bee1=new Texture("bee.png");
	bee2=new Texture("bee.png");
	bee3=new Texture("redbee.png");

	arkaplanX=0;
	arkaplan2X=Gdx.graphics.getWidth();

	arkaplan3X=0;
	arkaplan4X=Gdx.graphics.getWidth();

	distance=Gdx.graphics.getWidth()/2;
	random=new Random();

	birdX=Gdx.graphics.getWidth()/2 -bird.getHeight()/2;
	birdY=Gdx.graphics.getHeight()/3;
	alpX=Gdx.graphics.getWidth()/2 -bird.getHeight()/2;
	alpY=Gdx.graphics.getHeight()/3;
	shapeRenderer=new ShapeRenderer();

	birdCircle=new Circle();
	enemyCircle=new Circle[numberOfEnemy];
	enemyCircle2=new Circle[numberOfEnemy];
	enemyCircle3=new Circle[numberOfEnemy];

	font=new BitmapFont();
	font.setColor(Color.WHITE);
	font.getData().setScale(5);

	font2=new BitmapFont();
	font2.setColor(Color.WHITE);
	font2.getData().setScale(6);

	font3=new BitmapFont();
	font3.setColor(Color.GRAY);
	font3.getData().setScale(7);

	font4=new BitmapFont();
	font3.setColor(Color.GRAY);
	font4.getData().setScale(7);


	for (int i=0;i<numberOfEnemy;i++){
		enemyOffSet[i]=(random.nextFloat()-0.5f) * (Gdx.graphics.getHeight()-200);
		enemyOffSet2[i]=(random.nextFloat()-0.5f) * (Gdx.graphics.getHeight()-200);
		enemyOffSet3[i]=(random.nextFloat()-0.5f) * (Gdx.graphics.getHeight()-200);


		enemyX[i]=Gdx.graphics.getWidth() - bee1.getWidth()/2 + i*distance;

		enemyCircle[i]=new Circle();
		enemyCircle2[i]=new Circle();
		enemyCircle3[i]=new Circle();

	}

	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(mauntain,arkaplanX,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch.draw(mauntain,arkaplan2X,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


		if (score>50){
			batch.draw(mauntain2,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			batch.draw(mauntain2,arkaplanX,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
			batch.draw(mauntain2,arkaplan2X,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		}else if (score==3){
			batch.draw(alp,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		}

		if (gamestate==1){

			if (enemyX[scoredEnemy]<Gdx.graphics.getWidth()/2 -bird.getHeight()/2){
				score++;

				if (scoredEnemy<numberOfEnemy-1){
					scoredEnemy++;
				}else{
					scoredEnemy=0;
				}
			}
			arkaplanX--;
			arkaplan2X--;
			if (arkaplanX<=-Gdx.graphics.getWidth()){
				arkaplanX=Gdx.graphics.getWidth();
			}
			if (arkaplan2X<=-Gdx.graphics.getWidth()){
				arkaplan2X=Gdx.graphics.getWidth();
			}

			arkaplan3X--;
			arkaplan4X--;
			if (arkaplan3X<=-Gdx.graphics.getWidth()){
				arkaplan3X=Gdx.graphics.getWidth();
			}
			if (arkaplan4X<=-Gdx.graphics.getWidth()){
				arkaplan4X=Gdx.graphics.getWidth();
			}
			if (Gdx.input.justTouched()){
				velocity=-6-0.5f;
			}

			for (int i=0;i<numberOfEnemy;i++){

				if (enemyX[i] < 0){
					enemyX[i]=enemyX[i]+numberOfEnemy*distance;

					enemyOffSet[i]=(random.nextFloat()-0.5f) * (Gdx.graphics.getHeight()-200);
					enemyOffSet2[i]=(random.nextFloat()-0.5f) * (Gdx.graphics.getHeight()-200);
					enemyOffSet3[i]=(random.nextFloat()-0.5f) * (Gdx.graphics.getHeight()-200);

				}else{
					enemyX[i]=enemyX[i]-enemyVelocity;
				}

				enemyX[i]=enemyX[i] - enemyVelocity-enemyhız;

				batch.draw(bee1,enemyX[i],Gdx.graphics.getHeight()/2+enemyOffSet[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/12);
				batch.draw(bee2,enemyX[i],Gdx.graphics.getHeight()/2+enemyOffSet2[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/12);
				batch.draw(bee3,enemyX[i],Gdx.graphics.getHeight()/2+enemyOffSet3[i],Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/12);
				batch.draw(alp,Gdx.graphics.getHeight()/2+enemyOffSet[i],enemyX[i],Gdx.graphics.getWidth()/14,Gdx.graphics.getHeight()/9);


				enemyCircle[i]=new Circle(enemyX[i] + Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffSet[i] + Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/30);
				enemyCircle2[i]=new Circle(enemyX[i] + Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffSet2[i] + Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/30);
				enemyCircle3[i]=new Circle(enemyX[i] + Gdx.graphics.getWidth()/30,Gdx.graphics.getHeight()/2+enemyOffSet3[i] + Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/30);

			}



			if (birdY>0 && birdY < Gdx.graphics.getHeight()+(bird.getHeight())/20){
				velocity=velocity+gravity;
				birdY=birdY - velocity;
			}else{
				gamestate=2;
			}

		}else if(gamestate==0){
			if (Gdx.input.justTouched()){
				gamestate=1;
			}
		}else if (gamestate==2){

			font2.draw(batch,"Oyun Bitti! Oynamak icin Tekrar Tiklayiniz",200,Gdx.graphics.getHeight()/2+250);
			font3.draw(batch,"Puan: ",800,640);
			font4.draw(batch,String.valueOf(score),1100,640);

			if (Gdx.input.justTouched()){
				gamestate=1;

				birdY=Gdx.graphics.getHeight()/3;

				for (int i=0;i<numberOfEnemy;i++){
					enemyOffSet[i]=(random.nextFloat()-0.5f) * (Gdx.graphics.getHeight()-200);
					enemyOffSet2[i]=(random.nextFloat()-0.5f) * (Gdx.graphics.getHeight()-200);
					enemyOffSet3[i]=(random.nextFloat()-0.5f) * (Gdx.graphics.getHeight()-200);


					enemyX[i]=Gdx.graphics.getWidth() - bee1.getWidth()/2 + i*distance;

					enemyCircle[i]=new Circle();
					enemyCircle2[i]=new Circle();
					enemyCircle3[i]=new Circle();

				}
				velocity=0;
				scoredEnemy=0;
				score=0;
			}
		}


	batch.draw(bird,birdX,birdY,Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()/10);
	font.draw(batch,String.valueOf(score),100,220);


	batch.end();

	birdCircle.set(birdX+Gdx.graphics.getWidth()/28,birdY+Gdx.graphics.getHeight()/20,Gdx.graphics.getWidth()/45);
	//shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
	//shapeRenderer.setColor(Color.BLACK);
	//shapeRenderer.circle(birdCircle.x,birdCircle.y,birdCircle.radius);


	for (int i=0;i<numberOfEnemy;i++){
		//shapeRenderer.circle(enemyX[i]+45 + Gdx.graphics.getWidth()/100,Gdx.graphics.getHeight()/2+enemyOffSet[i] + Gdx.graphics.getHeight()/25,Gdx.graphics.getWidth()/46);
		//shapeRenderer.circle(enemyX[i]+45 + Gdx.graphics.getWidth()/100,Gdx.graphics.getHeight()/2+enemyOffSet2[i] + Gdx.graphics.getHeight()/25,Gdx.graphics.getWidth()/46);
		//shapeRenderer.circle(enemyX[i]+45 + Gdx.graphics.getWidth()/100,Gdx.graphics.getHeight()/2+enemyOffSet3[i] + Gdx.graphics.getHeight()/25,Gdx.graphics.getWidth()/46);

		if (Intersector.overlaps(birdCircle,enemyCircle[i]) || Intersector.overlaps(birdCircle,enemyCircle2[i]) || Intersector.overlaps(birdCircle,enemyCircle3[i])){
			gamestate=2;
		}

	}
		//shapeRenderer.end();
	}
	
	@Override
	public void dispose () {


	}
}
