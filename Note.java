package dynamic_beat_16;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y =  580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME; //현재 노트가 어느 위치에 있는지
	private String noteType;
	private boolean proceeded = true;
	
	public String getNoteType() { //현재 노트의 타입을  반환해주는 함수
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded; //현재진행이 되고있는지
	}
	
	public void close() { //현재노트가 움직이지않도록
		proceeded = false;
	}
	
	public Note(String noteType ) {
		if (noteType.equals("S")) {
			x = 228;
		}
		else if(noteType.equals("D")) {
			x = 332;
		}
		else if(noteType.equals("F")) {
			x = 436;
		}
		else if(noteType.equals("Space")) {
			x = 540;
		}
		else if(noteType.equals("J")) {
			x = 744;
		}
		else if(noteType.equals("K")) {
			x = 848;
		}
		else if(noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType;
	}
	
	public void screenDraw(Graphics2D g) {
		if(!noteType.equals("Space")) //스페이스가 아니면
		{
			g.drawImage(noteBasicImage, x, y, null); //현재자신이 위치한 공간에 그려줌
		}
		else //스페이스면
		{
			g.drawImage(noteBasicImage, x, y, null); 
			g.drawImage(noteBasicImage, x + 100, y, null);  
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;
		if(y > 620) //엄청 아래로 내려갔다면
		{
			System.out.println("Miss");
			close();
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				drop(); //노트를  떨어뜨림
				if(proceeded) //현재진행중인상황이라면
				{
					Thread.sleep(Main.SLEEP_TIME); //메인에서 정의해준 시간만큼 쉴 수있도록
				}
				else {
					interrupt();
					break;
				}
			}
		} catch(Exception e) {//오류발생시
			System.out.println(e.getMessage()); //오류출력
		}
	}
	
	public void judge() {
		if ( y>= 613) {
			System.out.println("Late");
			close();
		}
		else if ( y>= 600) {
			System.out.println("Good");
			close();
		}
		else if ( y>= 587) {
			System.out.println("Great");
			close();
		}
		else if ( y>= 573) {
			System.out.println("Perfect");
			close();
		}
		else if ( y>= 565) {
			System.out.println("Great");
			close();
		}
		else if ( y>= 550) {
			System.out.println("Good");
			close();
		}
		else if ( y>= 533) {
			System.out.println("Early");
			close();
		}
	}
}
