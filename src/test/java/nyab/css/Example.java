package nyab.css;

import java.util.Arrays;

import nyab.css.CSSColorParser;

public class Example {
  public static void main(String[] args) {
    // [255, 204, 85]
    int[] rgb = CSSColorParser.cssToRGB("#ffcc55");
    System.out.println(Arrays.toString(rgb));

    // [238, 190, 79]
    rgb = CSSColorParser.cssToRGB("#ffcc55ee", CSSColorParser.BGMode.BLACK);
    System.out.println(Arrays.toString(rgb));

    // [77.0, 88.0, 99.0, 0.5]
    float[] rgba = CSSColorParser.cssToRGBA("rgba(77,88,99,0.5)");
    System.out.println(Arrays.toString(rgba));
  }
}