package main.utils;

import java.awt.image.BufferedImage;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;

public class ImageResize {
	public static BufferedImage resize(BufferedImage img, int width, int height) {
		return Scalr.resize(img, Method.QUALITY, Mode.AUTOMATIC, width,height,
				Scalr.OP_ANTIALIAS);
	}
}
