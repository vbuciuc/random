import java.lang.Thread.State;
import java.util.concurrent.locks.LockSupport;

import com.google.caliper.api.BeforeRep;
import com.google.caliper.api.Macrobenchmark;
import com.google.caliper.runner.CaliperMain;


public class TestUnParkMacro {

	Thread t;
	
	{
		t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					LockSupport.park();
				}
			}
		});
		t.start();
	}
	
	@BeforeRep void waitPark() {
		while (t.getState() != State.WAITING) {Thread.yield();};
		
		System.out.println("parked");
	}
	
	@Macrobenchmark void unPark() {
		LockSupport.unpark(t);
	}
	
	public static void main(String[] args) {
		CaliperMain.main(TestUnParkMacro.class, new String[]{"-l0"});
	}
}
