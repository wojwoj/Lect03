import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import san.coll.fn.*;
import san.coll.fn.NoArg;


public class TestNewDelay {

	public static Delay de;
	public static OldDelay OldDe;
	public String timeThread1;
	public String timeThread2; 
	@Test
	public void testNewDelay() {
		de = Delay.create(new NoArg() {
			@Override
			public Object call() {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Call calculated");
				return "Call calculated";
			}
		});

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("Start "+dateFormat.format(date));

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				de.call();
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println("Stop Thread 1 "+dateFormat.format(date));
				timeThread1 = dateFormat.format(date);
			}
		});


		Thread t2= new Thread(new Runnable() {
			@Override
			public void run() {
				de.call();
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println("Stop Thread 2 "+dateFormat.format(date));
				timeThread2 = dateFormat.format(date);
			}
		});
		
		t1.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        t2.start();
        try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		assertEquals(timeThread1, timeThread2);
	}
	@Test
	public void testOldDelay() {
		OldDe = OldDelay.create(new NoArg() {
			@Override
			public Object call() {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Call calculated");
				return "Call calculated";
			}
		});

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("Start "+dateFormat.format(date));

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				OldDe.call();
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println("Stop Thread 1 "+dateFormat.format(date));
				timeThread1 = dateFormat.format(date);
			}
		});

		

		Thread t2= new Thread(new Runnable() {
			@Override
			public void run() {
				OldDe.call();
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println("Stop Thread 2 "+dateFormat.format(date));
				timeThread2 = dateFormat.format(date);
			}
		});
		
		t1.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        t2.start();
        try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		assertEquals(timeThread1, timeThread2);
	}

}
