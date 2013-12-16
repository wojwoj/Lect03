package san.coll.fn;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class Delay {

	public static Delay create(NoArg producer) {
		return new Delay(producer);
	}

	private Semaphore wojwoj = new Semaphore(1, true);

	private final NoArg producer;

	private AtomicBoolean produced = new AtomicBoolean(false);

	private Object value;

	private Delay(NoArg producer) {
		this.producer = producer;
	}

	public Object call() {
		if (produced.compareAndSet(false, false)) {
			try {
				wojwoj.acquire();
				if (!produced.get()) {
					value = producer.call();
					produced.set(true);
				}
				wojwoj.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return value;
	}

}
