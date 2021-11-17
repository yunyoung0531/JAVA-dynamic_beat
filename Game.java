package dynamic_beat_16;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {

	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();

	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();

	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;

	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String difficulty, String musicTitle) // 생성자
	{
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		this.gameMusic = new Music(this.musicTitle, false); // 한번만 실행되도록

	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);

		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);

		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (!note.isProceeded()) // 현재 노트가 작동중인 상태가 아니면
			{
				noteList.remove(i); // 노트에서 지워버림
				i--;
			} else {
				note.screenDraw(g);
			}
			note.screenDraw(g);
		}

		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		// 바로위 => 텍스트깨짐방지
		g.setFont(new Font("나눔손글씨 펜", Font.BOLD, 30));
		g.drawString(titleName, 20, 720);
		g.drawString(difficulty, 1190, 702);
		g.setFont(new Font("나눔손글씨 펜", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("나눔손글씨 펜", Font.BOLD, 30));
		g.drawString("000000", 565, 702); // 점수출력
		g.drawImage(blueFlareImage, 320, 370, null);
	}

	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumBig1.mp3", false).start();
	}

	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	@Override
	public void run() {
		// dropNotes();
		dropNotes(this.titleName);
	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	} // 지금실행되고있는 게임스레드를 종료시켜준다

	public void dropNotes(String titleName) //각각의 노트들이 떨어지도록 
	{
		int startTime = 4460 - Main.REACH_TIME * 1000;
		int gap = 125;
		Beat[] beats = new Beat[] {
				//new Beat(startTime, "S"),
				new Beat(startTime + gap * 6, "S"),
				new Beat(startTime + gap * 8, "D"),
				new Beat(startTime + gap * 10, "F"),
				new Beat(startTime + gap * 12, "S"),
				new Beat(startTime + gap * 14, "D"),
				new Beat(startTime + gap * 16, "F"),
				new Beat(startTime + gap * 18, "Space"),
				new Beat(startTime + gap * 20, "J"),
				new Beat(startTime + gap * 22, "K"),
				new Beat(startTime + gap * 24, "J"),
				new Beat(startTime + gap * 26, "Space"),
				new Beat(startTime + gap * 28, "L"),
				new Beat(startTime + gap * 30, "S"),
				new Beat(startTime + gap * 32, "D"),
				new Beat(startTime + gap * 34, "F"),
				//커버린
				new Beat(startTime + gap * 34, "S"),
				new Beat(startTime + gap * 36, "D"),
				new Beat(startTime + gap * 38, "S"),
				new Beat(startTime + gap * 40, "D"),
				new Beat(startTime + gap * 42, "S"),
				new Beat(startTime + gap * 44, "D"),
				new Beat(startTime + gap * 46, "J"),
				new Beat(startTime + gap * 48, "K"),
				new Beat(startTime + gap * 50, "L"),
				new Beat(startTime + gap * 52, "F"),
				new Beat(startTime + gap * 52, "Space"),
				new Beat(startTime + gap * 52, "J"),
				
				new Beat(startTime + gap * 54, "Space"),
				new Beat(startTime + gap * 56, "K"),
				new Beat(startTime + gap * 56, "D"),
				new Beat(startTime + gap * 57, "F"),
				new Beat(startTime + gap * 58, "L"),
				new Beat(startTime + gap * 60, "K"),
				new Beat(startTime + gap * 62, "J"),
				new Beat(startTime + gap * 64, "S"),
				new Beat(startTime + gap * 66, "D"),
				new Beat(startTime + gap * 68, "F"),
				new Beat(startTime + gap * 75, "F"),
				new Beat(startTime + gap * 77, "D"),
				new Beat(startTime + gap * 79, "F"),
				new Beat(startTime + gap * 81, "D"),
				
				new Beat(startTime + gap * 86, "Space"),
				new Beat(startTime + gap * 86, "D"),
				new Beat(startTime + gap * 86, "K"),
				new Beat(startTime + gap * 88, "D"),
				new Beat(startTime + gap * 90, "D"),
				new Beat(startTime + gap * 92, "D"),
				new Beat(startTime + gap * 96, "Space"),
				new Beat(startTime + gap * 98, "L"),
				new Beat(startTime + gap * 100, "K"),
				new Beat(startTime + gap * 102, "J"),
		};
		
		
		int i = 0;
		gameMusic.start(); 
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++; 
				dropped = true;
			} //노트 떨어뜨리는 과정
			if (!dropped) {
				try {
					Thread.sleep(5); //쉬도록
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
	//판정함수 like Queue
	public void judge(String input) {
		for(int i = 0;i<noteList.size();i++) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				note.judge();
				break;
			}
		}
	}
}
