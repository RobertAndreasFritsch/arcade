package environment.launch;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

public final class Updates {

	public static final void update() {
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(
					Runtime.getRuntime().exec(new String[] { "find", "/dev/sd??" }).getInputStream()));

			ArrayList<String> res = new ArrayList<String>();
			String line = null;
			while ((line = r.readLine()) != null) {
				res.add(line);
			}

			for (String s : res) {
				Runtime.getRuntime().exec(new String[] { "pmount", s }).waitFor();
			}

			for (File f : new File("/media").listFiles()) {
				for (File f2 : f.listFiles()) {
					boolean copiedSomething = false;
					if (f2.getName().equals("Tisch.jar")) {
						Runtime.getRuntime().exec(new String[] { "rm", "/home/tisch/Tisch.jar" }).waitFor();
						Runtime.getRuntime()
								.exec(new String[] { "cp", f2.getAbsoluteFile().toString(), "/home/tisch/Tisch.jar" })
								.waitFor();
						copiedSomething = true;
					}
					if (f2.getName().equals("res")) {
						Runtime.getRuntime().exec(new String[] { "rm", "-r", "/home/tisch/res" }).waitFor();
						Runtime.getRuntime()
								.exec(new String[] { "cp", "-R", f2.getAbsoluteFile().toString(), "/home/tisch/res" })
								.waitFor();
						copiedSomething = true;
					}

					if (copiedSomething) {
						Runtime.getRuntime().exec(new String[] { "shutdown", "now" });
					}
				}
			}

			for (String s : res) {
				Runtime.getRuntime().exec(new String[] { "pumount", s });
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
