package dynamic_beat_16;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y =  580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME; //���� ��Ʈ�� ��� ��ġ�� �ִ���
	private String noteType;
	private boolean proceeded = true;
	
	public String getNoteType() { //���� ��Ʈ�� Ÿ����  ��ȯ���ִ� �Լ�
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded; //���������� �ǰ��ִ���
	}
	
	public void close() { //�����Ʈ�� ���������ʵ���
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
		if(!noteType.equals("Space")) //�����̽��� �ƴϸ�
		{
			g.drawImage(noteBasicImage, x, y, null); //�����ڽ��� ��ġ�� ������ �׷���
		}
		else //�����̽���
		{
			g.drawImage(noteBasicImage, x, y, null); 
			g.drawImage(noteBasicImage, x + 100, y, null);  
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;
		if(y > 620) //��û �Ʒ��� �������ٸ�
		{
			System.out.println("Miss");
			close();
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				drop(); //��Ʈ��  ����߸�
				if(proceeded) //�����������λ�Ȳ�̶��
				{
					Thread.sleep(Main.SLEEP_TIME); //���ο��� �������� �ð���ŭ �� ���ֵ���
				}
				else {
					interrupt();
					break;
				}
			}
		} catch(Exception e) {//�����߻���
			System.out.println(e.getMessage()); //�������
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
