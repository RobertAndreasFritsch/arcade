package environment.launch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

public class XMLGameScore {
	private Document doc;
	private Element root;
	private File f;

	public XMLGameScore(File f) {
		this.f = f;
		doc = new Document("");
		doc.appendElement("Game");
		try {
			doc = Jsoup.parse(readFile(f), "", Parser.xmlParser());
		} catch (IOException e) {
		}
		root = doc.getElementsByTag("Game").get(0);
	}

	public void addEntry(String name, int value) {
		if (root.getElementsByAttributeValue("name", name).size() == 0) {
			root.appendElement("entry");
			root.children().last().attr("name", name);
			root.children().last().attr("score", Integer.toString(value));
		} else if (value > Integer.parseInt(root.getElementsByAttributeValue("name", name).attr("score"))) {
			root.getElementsByAttributeValue("name", name).attr("score", Integer.toString(value));
		}
	}

	public String[][] getAsTable() {
		String[][] res = new String[2][root.children().size() > 5 ? 5 : root.children().size()];

		int counter = 0;
		for (Element e : root.children()) {
			if (counter == 5) {
				break;
			}

			res[0][counter] = e.attr("name");
			res[1][counter] = e.attr("score");
			counter++;
		}

		return res;
	}

	public String readFile(File f) throws IOException {
		String res = "";

		BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String line = null;
		while ((line = r.readLine()) != null) {
			res += line;
		}
		r.close();

		return res;
	}

	public void save() {
		try {
			writeFile(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return doc.html();
	}

	public void writeFile(File f) throws FileNotFoundException {
		new File(f.getParent()).mkdirs();
		PrintStream p = new PrintStream(f);
		p.print(this);
		p.close();
	}

}
