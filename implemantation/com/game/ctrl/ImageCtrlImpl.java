package com.game.ctrl;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageCtrlImpl implements ImageCtrl
{
	public Map<String, Image>			images			= new HashMap<>();
	public Map<String, BufferedImage>	bufferedImages	= new HashMap<>();

	@Override
	public Image load(final String path, final ImageType imageType)
	{
		Image img;

		if ((img = this.images.get(path)) != null) { return img; }

		switch (imageType)
		{
		case GIF:
		case JPEG:
		case PNG:

			img = Toolkit.getDefaultToolkit().getImage(path);

			if (img != null)
			{
				this.images.put(path, img);
			}

			return img;

		default:
			return null;
		}
	}

	@Override
	public BufferedImage loadBuffered(final String path, final ImageType imageType)
	{
		BufferedImage img;

		if ((img = this.bufferedImages.get(path)) != null) { return img; }

		switch (imageType)
		{
		case GIF:
		case JPEG:
		case PNG:

			try
			{
				img = ImageIO.read(new File(path));
			}
			catch (final Exception e)
			{
				e.printStackTrace();
				return null;
			}

			if (img != null)
			{
				this.bufferedImages.put(path, img);
			}

			return img;

		default:
			return null;
		}
	}
}
