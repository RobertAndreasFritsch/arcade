package com.game.ctrl;

import java.awt.Image;
import java.awt.image.BufferedImage;

public interface ImageCtrl
{
	Image load(String path, ImageType imageType);

	BufferedImage loadBuffered(String path, ImageType imageType);
}
