package environment.launch;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

public final class Updates {

	public static final void update() {
		try {
			boolean done = false;
			BufferedReader r = new BufferedReader(
					new InputStreamReader(Runtime.getRuntime().exec(new String[] { "ls", "/dev" }).getInputStream()));

			ArrayList<String> res = new ArrayList<String>();
			String line = null;
			while ((line = r.readLine()) != null) {
				if (line.contains("sd") && line.endsWith("1")) {
					res.add(line);
				}
			}

			for (String s : res) {
				Runtime.getRuntime().exec(new String[] { "pmount", s });
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}

			for (File f : new File("/media").listFiles()) {
				for (File f2 : f.listFiles()) {
					if (f2.getName().equals("Tisch") && new File(f2.getAbsolutePath() + "/Tisch.jar").exists()) {
						Runtime.getRuntime().exec(new String[] { "rm", "-r", "/home/tisch/Tisch" }).waitFor();
						Runtime.getRuntime().exec(new String[] { "mv", f2.getAbsolutePath(), "/home/tisch" }).waitFor();
						Runtime.getRuntime().exec(new String[] { "rm", "-r", f2.getAbsolutePath() }).waitFor();
						Runtime.getRuntime().exec("reboot");
						done = true;
					}
				}
			}

			for (String s : res) {
				Runtime.getRuntime().exec(new String[] { "pumount", s });
			}

			if (done) {
				// Runtime.getRuntime().exec(new String[] { "reboot" });
				System.out.println("moved");
				System.exit(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
