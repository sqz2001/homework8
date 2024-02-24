


// The negate transformation is done by replacing each Pixel in the image with a new Pixel 
// whose rgb values are calculated by subtracting the original rgb values from 255. 
// These subtractions are done individually for each of the red, green, and blue colors.

public class NegativeFilter implements Filter {

    public void filter(PixelImage theImage) {
    
        Pixel[][] data = theImage.getData(); //gets data from image

        Pixel[][] output = new Pixel[theImage.getHeight()][theImage.getWidth()]; //make it the same size

        for (int row = 0; row < theImage.getHeight(); row++) {
            for (int column = 0; column < theImage.getWidth(); column++) {
                Pixel pixel = data[row][column];

                //new rgb values calculated by subtracting original from 255
                Pixel thePixel = data[row][column];

                int negativeR = 255 - thePixel.red;
                int negativeG = 255 - thePixel.green;
                int negativeB = 255 - thePixel.blue;



                Pixel negativePixel = new Pixel(negativeR, negativeG, negativeB);

                
                output[row][column]= negativePixel;
            }
        }
        
        //set the image to the new data values
        theImage.setData(output);


    }
}
        