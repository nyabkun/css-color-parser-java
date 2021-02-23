package nyab.css;

public class CSSColorParser {
	private static class NamedColor {
		final String name;
		final float[] rgba;

		NamedColor(String name, int r, int g, int b, int a) {
			this.name = name;
			this.rgba = new float[] { r, g, b, a };
		}
	}

	public static enum BGMode {
		WHITE,
		BLACK,
		IGNORE_ALPHA
	}

	private static final NamedColor[] namedColors = { new NamedColor("transparent", 0, 0, 0, 0),
			new NamedColor("aliceblue", 240, 248, 255, 1), new NamedColor("antiquewhite", 250, 235, 215, 1),
			new NamedColor("aqua", 0, 255, 255, 1), new NamedColor("aquamarine", 127, 255, 212, 1),
			new NamedColor("azure", 240, 255, 255, 1), new NamedColor("beige", 245, 245, 220, 1),
			new NamedColor("bisque", 255, 228, 196, 1), new NamedColor("black", 0, 0, 0, 1),
			new NamedColor("blanchedalmond", 255, 235, 205, 1), new NamedColor("blue", 0, 0, 255, 1),
			new NamedColor("blueviolet", 138, 43, 226, 1), new NamedColor("brown", 165, 42, 42, 1),
			new NamedColor("burlywood", 222, 184, 135, 1), new NamedColor("cadetblue", 95, 158, 160, 1),
			new NamedColor("chartreuse", 127, 255, 0, 1), new NamedColor("chocolate", 210, 105, 30, 1),
			new NamedColor("coral", 255, 127, 80, 1), new NamedColor("cornflowerblue", 100, 149, 237, 1),
			new NamedColor("cornsilk", 255, 248, 220, 1), new NamedColor("crimson", 220, 20, 60, 1),
			new NamedColor("cyan", 0, 255, 255, 1), new NamedColor("darkblue", 0, 0, 139, 1),
			new NamedColor("darkcyan", 0, 139, 139, 1), new NamedColor("darkgoldenrod", 184, 134, 11, 1),
			new NamedColor("darkgray", 169, 169, 169, 1), new NamedColor("darkgreen", 0, 100, 0, 1),
			new NamedColor("darkgrey", 169, 169, 169, 1), new NamedColor("darkkhaki", 189, 183, 107, 1),
			new NamedColor("darkmagenta", 139, 0, 139, 1), new NamedColor("darkolivegreen", 85, 107, 47, 1),
			new NamedColor("darkorange", 255, 140, 0, 1), new NamedColor("darkorchid", 153, 50, 204, 1),
			new NamedColor("darkred", 139, 0, 0, 1), new NamedColor("darksalmon", 233, 150, 122, 1),
			new NamedColor("darkseagreen", 143, 188, 143, 1), new NamedColor("darkslateblue", 72, 61, 139, 1),
			new NamedColor("darkslategray", 47, 79, 79, 1), new NamedColor("darkslategrey", 47, 79, 79, 1),
			new NamedColor("darkturquoise", 0, 206, 209, 1), new NamedColor("darkviolet", 148, 0, 211, 1),
			new NamedColor("deeppink", 255, 20, 147, 1), new NamedColor("deepskyblue", 0, 191, 255, 1),
			new NamedColor("dimgray", 105, 105, 105, 1), new NamedColor("dimgrey", 105, 105, 105, 1),
			new NamedColor("dodgerblue", 30, 144, 255, 1), new NamedColor("firebrick", 178, 34, 34, 1),
			new NamedColor("floralwhite", 255, 250, 240, 1), new NamedColor("forestgreen", 34, 139, 34, 1),
			new NamedColor("fuchsia", 255, 0, 255, 1), new NamedColor("gainsboro", 220, 220, 220, 1),
			new NamedColor("ghostwhite", 248, 248, 255, 1), new NamedColor("gold", 255, 215, 0, 1),
			new NamedColor("goldenrod", 218, 165, 32, 1), new NamedColor("gray", 128, 128, 128, 1),
			new NamedColor("green", 0, 128, 0, 1), new NamedColor("greenyellow", 173, 255, 47, 1),
			new NamedColor("grey", 128, 128, 128, 1), new NamedColor("honeydew", 240, 255, 240, 1),
			new NamedColor("hotpink", 255, 105, 180, 1), new NamedColor("indianred", 205, 92, 92, 1),
			new NamedColor("indigo", 75, 0, 130, 1), new NamedColor("ivory", 255, 255, 240, 1),
			new NamedColor("khaki", 240, 230, 140, 1), new NamedColor("lavender", 230, 230, 250, 1),
			new NamedColor("lavenderblush", 255, 240, 245, 1), new NamedColor("lawngreen", 124, 252, 0, 1),
			new NamedColor("lemonchiffon", 255, 250, 205, 1), new NamedColor("lightblue", 173, 216, 230, 1),
			new NamedColor("lightcoral", 240, 128, 128, 1), new NamedColor("lightcyan", 224, 255, 255, 1),
			new NamedColor("lightgoldenrodyellow", 250, 250, 210, 1), new NamedColor("lightgray", 211, 211, 211, 1),
			new NamedColor("lightgreen", 144, 238, 144, 1), new NamedColor("lightgrey", 211, 211, 211, 1),
			new NamedColor("lightpink", 255, 182, 193, 1), new NamedColor("lightsalmon", 255, 160, 122, 1),
			new NamedColor("lightseagreen", 32, 178, 170, 1), new NamedColor("lightskyblue", 135, 206, 250, 1),
			new NamedColor("lightslategray", 119, 136, 153, 1), new NamedColor("lightslategrey", 119, 136, 153, 1),
			new NamedColor("lightsteelblue", 176, 196, 222, 1), new NamedColor("lightyellow", 255, 255, 224, 1),
			new NamedColor("lime", 0, 255, 0, 1), new NamedColor("limegreen", 50, 205, 50, 1),
			new NamedColor("linen", 250, 240, 230, 1), new NamedColor("magenta", 255, 0, 255, 1),
			new NamedColor("maroon", 128, 0, 0, 1), new NamedColor("mediumaquamarine", 102, 205, 170, 1),
			new NamedColor("mediumblue", 0, 0, 205, 1), new NamedColor("mediumorchid", 186, 85, 211, 1),
			new NamedColor("mediumpurple", 147, 112, 219, 1), new NamedColor("mediumseagreen", 60, 179, 113, 1),
			new NamedColor("mediumslateblue", 123, 104, 238, 1), new NamedColor("mediumspringgreen", 0, 250, 154, 1),
			new NamedColor("mediumturquoise", 72, 209, 204, 1), new NamedColor("mediumvioletred", 199, 21, 133, 1),
			new NamedColor("midnightblue", 25, 25, 112, 1), new NamedColor("mintcream", 245, 255, 250, 1),
			new NamedColor("mistyrose", 255, 228, 225, 1), new NamedColor("moccasin", 255, 228, 181, 1),
			new NamedColor("navajowhite", 255, 222, 173, 1), new NamedColor("navy", 0, 0, 128, 1),
			new NamedColor("oldlace", 253, 245, 230, 1), new NamedColor("olive", 128, 128, 0, 1),
			new NamedColor("olivedrab", 107, 142, 35, 1), new NamedColor("orange", 255, 165, 0, 1),
			new NamedColor("orangered", 255, 69, 0, 1), new NamedColor("orchid", 218, 112, 214, 1),
			new NamedColor("palegoldenrod", 238, 232, 170, 1), new NamedColor("palegreen", 152, 251, 152, 1),
			new NamedColor("paleturquoise", 175, 238, 238, 1), new NamedColor("palevioletred", 219, 112, 147, 1),
			new NamedColor("papayawhip", 255, 239, 213, 1), new NamedColor("peachpuff", 255, 218, 185, 1),
			new NamedColor("peru", 205, 133, 63, 1), new NamedColor("pink", 255, 192, 203, 1),
			new NamedColor("plum", 221, 160, 221, 1), new NamedColor("powderblue", 176, 224, 230, 1),
			new NamedColor("purple", 128, 0, 128, 1), new NamedColor("red", 255, 0, 0, 1),
			new NamedColor("rosybrown", 188, 143, 143, 1), new NamedColor("royalblue", 65, 105, 225, 1),
			new NamedColor("saddlebrown", 139, 69, 19, 1), new NamedColor("salmon", 250, 128, 114, 1),
			new NamedColor("sandybrown", 244, 164, 96, 1), new NamedColor("seagreen", 46, 139, 87, 1),
			new NamedColor("seashell", 255, 245, 238, 1), new NamedColor("sienna", 160, 82, 45, 1),
			new NamedColor("silver", 192, 192, 192, 1), new NamedColor("skyblue", 135, 206, 235, 1),
			new NamedColor("slateblue", 106, 90, 205, 1), new NamedColor("slategray", 112, 128, 144, 1),
			new NamedColor("slategrey", 112, 128, 144, 1), new NamedColor("snow", 255, 250, 250, 1),
			new NamedColor("springgreen", 0, 255, 127, 1), new NamedColor("steelblue", 70, 130, 180, 1),
			new NamedColor("tan", 210, 180, 140, 1), new NamedColor("teal", 0, 128, 128, 1),
			new NamedColor("thistle", 216, 191, 216, 1), new NamedColor("tomato", 255, 99, 71, 1),
			new NamedColor("turquoise", 64, 224, 208, 1), new NamedColor("violet", 238, 130, 238, 1),
			new NamedColor("wheat", 245, 222, 179, 1), new NamedColor("white", 255, 255, 255, 1),
			new NamedColor("whitesmoke", 245, 245, 245, 1), new NamedColor("yellow", 255, 255, 0, 1),
			new NamedColor("yellowgreen", 154, 205, 50, 1) };

	private static float clampCssByte(float i) {  // Clamp to integer 0 .. 255.
		i = Math.round(i);  // Seems to be what Chrome does (vs truncation).
		return i < 0 ? 0 : i > 255 ? 255 : i;
	}

	private static float clampCssFloat(float f) {  // Clamp to float 0.0 .. 1.0.
		return f < 0 ? 0 : f > 1 ? 1 : f;
	}

	private static float parseCssInt(String str) { // int or percentage.
		char lastChar = str.charAt(str.length() - 1);
		if (lastChar == '%') {
			return clampCssByte( (Float.parseFloat(str)/100.0f * 255));
		} else {
			return clampCssByte(Integer.parseInt(str));
		}
	}

	private static float parseCssFloat(String str) {  // float or percentage.
		char lastChar = str.charAt(str.length() - 1);
		if (lastChar == '%') {
			return clampCssFloat(Float.parseFloat(str.substring(0, str.length()-1))/100.0f);
		} else {
			return clampCssFloat(Float.parseFloat(str));
		}
	}

	private static float cssHueToRGB(float m1, float m2, float h) {
	    if (h < 0.0f) {
	        h += 1.0f;
	    } else if (h > 1.0f) {
	        h -= 1.0f;
	    }

	    if (h * 6.0f < 1.0f) {
	        return m1 + (m2 - m1) * h * 6.0f;
	    }
	    if (h * 2.0f < 1.0f) {
	        return m2;
	    }
	    if (h * 3.0f < 2.0f) {
	        return m1 + (m2 - m1) * (2.0f / 3.0f - h) * 6.0f;
	    }
	    return m1;
	}

	private static float[] parseHexColor(String str) {
		float r = -1, g = -1, b = -1, a = 1;
        if (str.length() == 3) {
        	String R = str.substring(0, 1);
        	String G = str.substring(1, 2);
        	String B = str.substring(2, 3);
			r = Integer.parseInt(R + R, 16);
			g = Integer.parseInt(G + G, 16);
			b = Integer.parseInt(B + B, 16);
        } else if (str.length() == 6) {
			r = Integer.parseInt(str.substring(0, 2), 16);
			g = Integer.parseInt(str.substring(2, 4), 16);
			b = Integer.parseInt(str.substring(4, 6), 16);
        } else if (str.length() == 8) {
			r = Integer.parseInt(str.substring(0, 2), 16);
			g = Integer.parseInt(str.substring(2, 4), 16);
			b = Integer.parseInt(str.substring(4, 6), 16);
			a = Integer.parseInt(str.substring(6, 8), 16)/255.0f;
        }
        if( r >= 0 ) {
        	return new float[] { r, g, b, a };
        } else {
        	return null;
        }
	}

	private static float[] parseGenerously(String cssStr) {
		String[] params = cssStr.split(",");

		if( params.length == 1 ) {
			if ( cssStr.matches("\\d+") ) {
				int gray = parseByteGenerously(params[0]);

				return new float[] { gray, gray, gray, 1 };
			} else {
				return parseHexColor(cssStr);
			}
		} else if ( params.length == 2 ) {
			int r = parseByteGenerously(params[0]);
			int g = parseByteGenerously(params[1]);

			return new float[] { r, g, 0, 1 };
		} else if ( params.length == 3 ) {
			int r = parseByteGenerously(params[0]);
			int g = parseByteGenerously(params[1]);
			int b = parseByteGenerously(params[2]);

			return new float[] { r, g, b, 1 };
		} else {
			int r = parseByteGenerously(params[0]);
			int g = parseByteGenerously(params[1]);
			int b = parseByteGenerously(params[2]);
			int a = parseByteGenerously(params[3]);

			return new float[] { r, g, b , a };
		}
	}

	private static int parseByteGenerously(String numStr) {
		int colValue;
		try {
			colValue = Integer.parseInt(numStr);
		} catch ( Exception e ) {
			colValue = Integer.parseInt(numStr, 16);
		}

		if( colValue < 0 || colValue > 255 )  {
			colValue = colValue % 255;
		}

		return colValue;
	}

	private static float[] parse(String css_str) {
		// Remove all whitespace, not compliant, but should just be more accepting.
	    String str = css_str.replaceAll("[\\s　]", "").toLowerCase();

	    if( str.isEmpty() ) return null;

	    for (NamedColor namedColor : namedColors) {
	        if (str.equals(namedColor.name)) {
	        	return namedColor.rgba;
	        }
	    }

	    // #rgb / #rrggbb / #rrggbbaa
	    if (str.charAt(0) == '#') {
	    	return parseHexColor(str.substring(1));
	    }

	    int op = str.indexOf('('), ep = str.indexOf(')');
	    String fname = null;
	    String[] params = null;
	    if (op != -1 && ep > op ) {
	        fname = str.substring(0, op);
	        params = str.substring(op + 1, ep).split(",");
	    } else {
	    	return null;
	    }

		float alpha = 1.0f;
		if (fname.equals("rgba") || fname.equals("rgb")) {
			if (fname.equals("rgba")) {
				if (params.length != 4)
					return null;
				alpha = parseCssFloat(params[params.length - 1]);
			} else {
				if (params.length != 3)
					return null;
			}

			return new float[] { parseCssInt(params[0]), parseCssInt(params[1]), parseCssInt(params[2]), alpha };

		} else if (fname.equals("hsla") || fname.equals("hsl")) {
			if (fname.equals("hsla")) {
				if (params.length != 4)
					return null;
				alpha = parseCssFloat(params[params.length - 1]);
			} else {
				if (params.length != 3)
					return null;
			}

			float h = Float.parseFloat(params[0]) / 360.0f;
			// Normalize the hue to [0..1[
			h = (((Float.parseFloat(params[0]) % 360) + 360) % 360) / 360;

			// NOTE(deanm): According to the CSS spec s/l should only be
			// percentages, but we don't bother and let float or percentage.
			float s = parseCssFloat(params[1]);
			float l = parseCssFloat(params[2]);

			float m2 = l <= 0.5f ? l * (s + 1.0f) : l + s - l * s;
			float m1 = l * 2.0f - m2;

			return new float[] { clampCssByte(cssHueToRGB(m1, m2, h + (1 / 3f)) * 255f),
					clampCssByte(cssHueToRGB(m1, m2, h) * 255.0f),
					clampCssByte(cssHueToRGB(m1, m2, h - (1 / 3f)) * 255f), alpha };
		}

	    return null;
	}

	private static String preProcessStr(String cssStr) {
		return cssStr.replaceAll("[\\s　]", "").toLowerCase();
	}

	public static float[] cssToRGBA(String cssStr) {
		float[] result = null;
		try {
			cssStr = preProcessStr(cssStr);
			result = parse(cssStr);
			if( result != null ) return result;
		} catch (Exception e) {
			//TODO
			System.out.println(e);
		}

		try {
			return parseGenerously(cssStr);
		} catch ( Exception e ) {
			return null;
		}
	}

	public static int[] cssToRGB(String cssStr) {
		return cssToRGB(cssStr, BGMode.WHITE);
	}

	public static int[] cssToRGB(String cssStr, BGMode bgMode) {
		try {
			// https://stackoverflow.com/a/746937/14820021
			float[] rgba = cssToRGBA(cssStr);
			if( bgMode != BGMode.IGNORE_ALPHA ) {
				float alpha = rgba[3];
				float bg = bgMode == BGMode.WHITE ? 255 : 0;
				float add = (1 - alpha) * bg;
				float r = rgba[0] * alpha + add;
				float g = rgba[1] * alpha + add;
				float b = rgba[2] * alpha + add;

				return new int[] {(int) r, (int) g, (int) b};
			} else {
				return new int[] {(int) rgba[0], (int) rgba[1], (int) rgba[2]};
			}
		} catch (Exception e) {
			return null;
		}
	}
}