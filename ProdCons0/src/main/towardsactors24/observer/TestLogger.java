package main.towardsactors24.observer;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.msg.ApplMessage;
import unibo.basicomm23.utils.CommUtils;

public class TestLogger extends TestCase {

	@BeforeClass
	public static void activateConsumer() {
		CommUtils.outmagenta("activateConsumer");
		new MainObserver().configureTheSystem();
	}

	@Test
	public void testUpdate() throws IOException {
		CommUtils.outmagenta("testUpdate ======================================= ");
		IApplMessage m = CommUtils.buildDispatch("tester", "update", "testupdate", "consumer");
		CommUtils.outblue("" + m);
		assertEquals(m.msgContent(), "testupdate");

		readLogFile();
	}

	protected void readLogFile() throws IOException {
		String line, whoLogged, msg;
		IApplMessage m;
		File myObj = new File("obsloggerLog.txt");
		Scanner myReader = new Scanner(myObj);
		line = myReader.nextLine();

		whoLogged = line.split(":")[0];

		assertEquals(whoLogged, "consumer");

		msg = line.split(":")[1];

		m = new ApplMessage(msg.trim());
		CommUtils.outblue("" + m);
		assertEquals(m.msgContent(), "update");

		line = myReader.nextLine();

		whoLogged = line.split(":")[0];

		assertEquals(whoLogged, "producer");

		msg = line.split(":")[1];

		m = new ApplMessage(msg.trim());
		CommUtils.outblue("" + m);
		assertEquals(m.msgContent(), "ack(update)");

		myReader.close();

	}
}
