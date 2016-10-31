package a7;


public class ColorPixel implements Pixel {

	private double red;
	private double green;
	private double blue;
	
	private static final double RED_INTENSITY_FACTOR = 0.299;
	private static final double GREEN_INTENSITY_FACTOR = 0.587;
	private static final double BLUE_INTENSITY_FACTOR = 0.114;

	private static final char[] PIXEL_CHAR_MAP = {'#', 'M', 'X', 'D', '<', '>', 's', ':', '-', ' ', ' '};
	
	public ColorPixel(double r, double g, double b) {
		if (r > 1.0 || r < 0.0) {
			throw new IllegalArgumentException("Red out of bounds");
		}
		if (g > 1.0 || g < 0.0) {
			throw new IllegalArgumentException("Green out of bounds");
		}
		if (b > 1.0 || b < 0.0) {
			throw new IllegalArgumentException("Blue out of bounds");
		}
		red = r;
		green = g;
		blue = b;
	}
	
	@Override
	public double getRed() {
		return red;
	}

	@Override
	public double getBlue() {
		return blue;
	}

	@Override
	public double getGreen() {
		return green;
	}

	@Override
	public double getIntensity() {
		return RED_INTENSITY_FACTOR*getRed() + 
				GREEN_INTENSITY_FACTOR*getGreen() + 
				BLUE_INTENSITY_FACTOR*getBlue();
	}
	
	@Override
	public char getChar() {
		int char_idx = (int) (getIntensity()*10.0);
		return PIXEL_CHAR_MAP[char_idx];
	}
	
	//method that blends object pixel with parameter pixel based on specific weight
		public Pixel blend(Pixel p, double weight) {
			
			// parameter pixel's rgb values
			double pr = p.getRed();
			double pg = p.getGreen();
			double pb = p.getBlue();
			
			// object pixel's rgb values
			double red = this.getRed();
			double green = this.getGreen();
			double blue = this.getBlue();
			
			double newRed = (red * weight) + (pr * (1.0 - weight));
			double newBlue = (blue * weight) + (pb * (1.0 - weight));
			double newGreen = (green * weight) + (pg * (1.0 - weight));

			//new color pixel that holds blended rgb values
			ColorPixel blended = new ColorPixel(newRed, newGreen, newBlue);

			return blended;
		}

}
