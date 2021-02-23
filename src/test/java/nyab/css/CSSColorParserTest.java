package nyab.css;

import static nyab.css.CSSColorParser.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

class CSSColorParserTest {
	String[][] equalCssColorPatterns = {
			{"#FFFFFF", "#FFF", "#FFFFFFFF", "255, 255, 255", "255", "rgb(255,255,255)",
				"rgb(255,255,255);", "rgba(255,255,255,1)", "white", "WHITE"},
			{"#000000", "#000", "#000000FF", "0,0,0", "rgb(0,0,0)", "0",
					"rgb(0,0,0);", "rgba(0,0,0,1)", "black", "Black"},
			{"#F0F8FF", "aliceblue"},
			{"#556B2F", "DarkOliveGreen"},
			{"hsl(328, 100%, 44%)", "rgb(224, 0, 120)", "224, 0, 120"},
			{"hsl(294, 28%, 44%)", "#895190"},
			{"hsla(328, 31%, 44%, 0.42)", "rgba(147, 77, 115, 0.42)"},
	};

	@Test
	void testCssToRGBA() {
		Function<String, float[]> fun = (String s) -> cssToRGBA(s);

		for (String[] cssPatterns : equalCssColorPatterns) {
			for (int i = 0; i < cssPatterns.length - 1; i++) {
				var resultA = fun.apply(cssPatterns[i]);
				var resultB = fun.apply(cssPatterns[i + 1]);

				testCSStoRGBAResult(resultA, cssPatterns[i]);
				testCSStoRGBAResult(resultB, cssPatterns[i + 1]);
				assertArrayEquals(resultA, resultB, cssPatterns[i] + " = " + cssPatterns[i+1]);
			}
		}
	}

	@Test
	void testCssToRGB() {
		Function<String, int[]> fun = (String s) -> cssToRGB(s);

		for (String[] cssPatterns : equalCssColorPatterns) {
			for (int i = 0; i < cssPatterns.length - 1; i++) {
				var resultA = fun.apply(cssPatterns[i]);
				var resultB = fun.apply(cssPatterns[i + 1]);

				testCSStoRGBResult(resultA, cssPatterns[i]);
				testCSStoRGBResult(resultA, cssPatterns[i + 1]);
				assertArrayEquals(resultA, resultB);
			}
		}
	}
	
	void testCSStoRGBAResult(float[] rgba, String css) {
		assertNotNull(rgba, css);
		
		for(float i : rgba) {
			assertTrue( 0 <= i && i <= 255, css);	
		}
		
		float alpha = rgba[3];
		assertTrue( 0.0f <= alpha && alpha <= 1.0f);	
		assertEquals(4, rgba.length, css);
	}
	
	void testCSStoRGBResult(int[] rgb, String css) {		
		assertNotNull(rgb);
		
		for(float i : rgb) {
			assertTrue( 0 <= i && i <= 255);	
		}
		
		assertEquals(3, rgb.length, css);
	}

}
