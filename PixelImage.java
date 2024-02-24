import java.awt.image.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Provides an interface to a picture as an array of Pixels
 */
public class PixelImage {
  private BufferedImage myImage;
  private int width;
  private int height;

  /**
   * Map this PixelImage to a real image
   * 
   * @param bi The image
   */
  public PixelImage(BufferedImage bi) {
    // initialise instance variables
    this.myImage = bi;
    this.width = bi.getWidth();
    this.height = bi.getHeight();
  }

  public void GaussianBlur() {

    int[][] gaussanBlurWeights = new int[3][3];

    gaussanBlurWeights[0][0] = 1;
    gaussanBlurWeights[1][0] = 2;
    gaussanBlurWeights[2][0] = 1;

    gaussanBlurWeights[0][1] = 2;
    gaussanBlurWeights[1][1] = 4;
    gaussanBlurWeights[2][1] = 2;
    gaussanBlurWeights[0][2] = 1;
    gaussanBlurWeights[1][2] = 2;
    gaussanBlurWeights[2][2] = 1;





    Pixel myArray[][] = getData();


    int len = myArray.length;
    for (int row = 0; row < len -1; row++) {
      for (int column = 0; column < len-1; column++) {

        if (row> 0 && column >0 && column <len - 1 && row <len-1 )
        {
          if(column == 280 && row ==140 )
          {
            int test = 0;
          }
        myArray[row][column] = BlurPixel(row, column, myArray,gaussanBlurWeights);
        }


      }
    }
setData(myArray);
    int myVal = 0;
  }

  public Pixel BlurPixel(int col, int row, Pixel[][] originalData, int[][] blurValues) {


    int redTotal = 0;
    int greenTotal = 0;
    int blueTotal = 0;

    for(int i = -1; i < 1; i++){
      for(int j = - 1; j < 1; j++){
   
          Pixel original = originalData[row + i][col + i];
          Pixel alteredPixel = GetAlteredPixel(row + i, col + j, originalData, blurValues[i + 1][j + 1]);

          redTotal += alteredPixel.red;
          greenTotal += alteredPixel.green;
          blueTotal += alteredPixel.blue;
   
         }
      }


    redTotal = redTotal/9;
    greenTotal = greenTotal/9;
    blueTotal = blueTotal /9;

    Pixel alteredPixel = originalData[row][col];
    alteredPixel.red = redTotal;
    alteredPixel.green = greenTotal;
    alteredPixel.blue = blueTotal;

    return alteredPixel;

    
  }

  public Pixel GetAlteredPixel(int col, int row, Pixel[][] originalData, int weight) {

    Pixel thePixel = originalData[row][col];

    Pixel theWeightedPixel = CalculateWeightedAverage(thePixel, weight);
    return theWeightedPixel;
  }

  // weighted average of pixels for filters
  public Pixel CalculateWeightedAverage(Pixel pixel, int weight) {

    // datatype anyName = new datatype();
    Pixel myPixel = new Pixel(pixel.red, pixel.green, pixel.blue);
    pixel.red = pixel.red * weight / 16;
    pixel.green = pixel.green * weight / 16;
    pixel.blue = pixel.blue * weight / 16;

    return pixel;

  }

  /**
   * Return the width of the image
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Return the height of the image
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Return the BufferedImage of this PixelImage
   */
  public BufferedImage getImage() {
    return this.myImage;
  }

  /**
   * Return the image's pixel data as an array of Pixels. The
   * first coordinate is the x-coordinate, so the size of the
   * array is [width][height], where width and height are the
   * dimensions of the array
   * 
   * @return The array of pixels
   */
  public Pixel[][] getData() {
    Raster r = this.myImage.getRaster();
    Pixel[][] data = new Pixel[r.getHeight()][r.getWidth()];
    int[] samples = new int[3];

    for (int row = 0; row < r.getHeight(); row++) {
      for (int col = 0; col < r.getWidth(); col++) {
        samples = r.getPixel(col, row, samples);
        Pixel newPixel = new Pixel(samples[0], samples[1], samples[2]);
        data[row][col] = newPixel;
      }
    }

    return data;
  }

  /**
   * Set the image's pixel data from an array. This array matches
   * that returned by getData(). It is an error to pass in an
   * array that does not match the image's dimensions or that
   * has pixels with invalid values (not 0-255)
   * 
   * @param data The array to pull from
   */
  public void setData(Pixel[][] data) {
    int[] pixelValues = new int[3]; // a temporary array to hold r,g,b values
    WritableRaster wr = this.myImage.getRaster();

    if (data.length != wr.getHeight()) {
      throw new IllegalArgumentException("Array size does not match");
    } else if (data[0].length != wr.getWidth()) {
      throw new IllegalArgumentException("Array size does not match");
    }

    for (int row = 0; row < wr.getHeight(); row++) {
      for (int col = 0; col < wr.getWidth(); col++) {
        pixelValues[0] = data[row][col].red;
        pixelValues[1] = data[row][col].green;
        pixelValues[2] = data[row][col].blue;
        wr.setPixel(col, row, pixelValues);
      }
    }
  }

}
