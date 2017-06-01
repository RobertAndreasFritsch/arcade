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
	Map<String, Image> images = new HashMap<>();
	Map<String, BufferedImage> bufferedImages = new HashMap<>();
	
	@Override
	public Image load(String path, ImageType imageType)
	{
		Image img;
		
		if ((img = images.get(path)) != null)
			return img;
		
		switch (imageType)
		{
		case GIF:
		case JPEG:
		case PNG:
			
			img = Toolkit.getDefaultToolkit().getImage(path);
			
			if (img != null)
				images.put(path, img);
			
			return img;

		default:
			return null;
		}
	}

	@Override
	public BufferedImage loadBuffered(String path, ImageType imageType)
	{
		BufferedImage img;
		
		if ((img = bufferedImages.get(path)) != null)
			return img;
		
		switch (imageType)
		{
		case GIF:
		case JPEG:
		case PNG:
			
			try
			{
				img = ImageIO.read(new File(path));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
			
			if (img != null)
				bufferedImages.put(path, img);
			
			return img;

		default:
			return null;
		}
	}
}
