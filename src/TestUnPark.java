import java.util.concurrent.locks.LockSupport;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.caliper.api.BeforeRep;
import com.google.caliper.runner.CaliperMain;
import com.google.caliper.runner.Running.BeforeExperimentMethods;

public class TestUnPark {
	@Param({"1", "5","10","50","100","500"}) int size;
	
	Thread[] threads;
	

	@BeforeExperiment public void setUp() {
		threads = new Thread[size];
		
		for (int i = 0; i < threads.length; i++) {
			Runnable runs = new Runnable() {		
				@Override
				public void run() {
					while(true) {
						LockSupport.park();
					}
				}
			};
			
			threads[i] = new Thread(runs);
			threads[i].start();
		}
	}
	
	@Benchmark public void timeUnpark(int reps) {
		for (; reps > 0; reps--) {
			LockSupport.unpark(threads[reps % threads.length]);
		}
	}
	
	public static void main(String[] args) {
		CaliperMain.main(TestUnPark.class, new String[]{"-iruntime"});
	}
}
