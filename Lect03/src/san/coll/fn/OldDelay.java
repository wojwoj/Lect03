package san.coll.fn;

import java.util.concurrent.Semaphore;

public class OldDelay {

	public static OldDelay create(NoArg producer) {
		return new OldDelay(producer);
	}

	private final NoArg producer;

	private boolean produced;

	private Object value;

	private OldDelay(NoArg producer) {
		this.producer = producer;
	}

	public Object call() {
		if (!produced) {
			value = producer.call();
			produced = true;
		}
		return value;
	}

}
