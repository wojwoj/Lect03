import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import san.coll.fn.Delay;
import san.coll.fn.NoArg;

public class TestDelay {

	public static Delay de;

	public static void main(String[] args) {
		de = Delay.create(new NoArg() {
			@Override
			public Object call() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Jak sie masz");
				return "Jak sie masz";
			}
		});

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("Start "+dateFormat.format(date));

		new Thread(new Runnable() {

			@Override
			public void run() {
				de.call();
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println("Stop Thread 1 "+dateFormat.format(date));
			}
		}).start();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				de.call();
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println("Stop Thread 2 "+dateFormat.format(date));
			}
		}).start();
	}

}
