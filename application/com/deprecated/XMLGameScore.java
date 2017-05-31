package com.deprecated;

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

/**
 * @deprecated not used anymore
 */
@Deprecated
public class XMLGameScore
{
	private Document			doc;
	private final Element	root;
	private final File		f;

	public XMLGameScore(final File f)
	{
		this.f = f;
		this.doc = new Document("");
		this.doc.appendElement("Game");
		try
		{
			this.doc = Jsoup.parse(this.readFile(f), "", Parser.xmlParser());
		}
		catch (final IOException e)
		{
		}
		this.root = this.doc.getElementsByTag("Game").get(0);
	}

	public void addEntry(final String name, final int value)
	{
		if (this.root.getElementsByAttributeValue("name", name).size() == 0)
		{
			this.root.appendElement("entry");
			this.root.children().last().attr("name", name);
			this.root.children().last().attr("score", Integer.toString(value));
		}
		else
			if (value > Integer.parseInt(this.root.getElementsByAttributeValue("name", name).attr("score")))
			{
				this.root.getElementsByAttributeValue("name", name).attr("score", Integer.toString(value));
			}
	}

	public String[][] getAsTable()
	{
		final String[][] res = new String[2][this.root.children().size() > 5 ? 5 : this.root.children().size()];

		int counter = 0;
		for (final Element e : this.root.children())
		{
			if (counter == 5)
			{
				break;
			}

			res[0][counter] = e.attr("name");
			res[1][counter] = e.attr("score");
			counter++;
		}

		return res;
	}

	public String readFile(final File f) throws IOException
	{
		String res = "";

		final BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String line = null;
		while ((line = r.readLine()) != null)
		{
			res += line;
		}
		r.close();

		return res;
	}

	public void save()
	{
		try
		{
			this.writeFile(this.f);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public String toString()
	{
		return this.doc.html();
	}

	public void writeFile(final File f) throws FileNotFoundException
	{
		new File(f.getParent()).mkdirs();
		final PrintStream p = new PrintStream(f);
		p.print(this);
		p.close();
	}

}
