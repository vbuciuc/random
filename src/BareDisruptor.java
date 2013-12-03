import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;


public class BareDisruptor {

	static ExecutorService executor = Executors.newSingleThreadExecutor();
	
	public static void main(String[] args) {
		RingBuffer<Event> ringBuffer = RingBuffer.createSingleProducer(Event.FACTORY, 1024);
		
		
	}
	
	private static class Event {
		static EventFactory<Event> FACTORY = new EventFactory<Event>() {

			@Override
			public Event newInstance() {
				// TODO Auto-generated method stub
				return null;
			}};
	}
	
	private EventHandler<Event> handler = new EventHandler<Event>() {

		@Override
		public void onEvent(Event event, long sequence, boolean endOfBatch)
				throws Exception {
			
		}
	};
}
