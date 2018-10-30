import java.awt.Toolkit;
import java.io.IOException;

import pattern.Logger;

public class SingletonExample {

	public static void main(String[] args) throws IOException {

//			Logger logger = new Logger(); Logger에서 private이기 때문에 안 듬
			Logger logger = Logger.getInstance();
			logger.log("테스트입니다..");
			
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			
			//다른 프로세스 실행할 때 사용
			Runtime runtime = Runtime.getRuntime();
			runtime.exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe http://www.naver.com");
			
	}

}
