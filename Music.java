package dynamic_beat_16;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

//Thread == 프로그램 안에 있는 하나의 작은 프로그램
public class Music extends Thread {

		private Player player; //javazoom의 라이브러리 중 하나인 Player
		private boolean isLoop; //현재곡이 무한루프인지 아닌지에 대한 설정
		private File file;
		private FileInputStream fis;
		private BufferedInputStream bis;
		
		public Music(String name, boolean isLoop) {
			try {
				this.isLoop = isLoop;
				file = new File(Main.class.getResource("../music/" + name).toURI());
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		//현재 재생되고 있는 음악이 어느 위치인지
		public int getTime() {
			if (player == null) return 0;
			return player.getPosition();
		}
		//음악이 언제 실행되든지 항상 종료할 수 있게 해주는 함수
		public void close() {
			isLoop = false;
			player.close();
			this.interrupt(); //해당 스레드를 중지상태로 만듦
		}
		
		
		//스레드를 상속받으면 무조건 사용해야하는 함수
		@Override
		public void run() {
			try {
				do {
					player.play(); //일단 곡 실행시킴
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					player = new Player(bis);
				} while (isLoop); //isLoop라는 해당변수가 트루값이면 무한반복
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
}
