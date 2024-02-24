//sarah zhou
//cs142 winter 2024
//feb 21, 2024
//this is a class that takes an imagee "theImage" and flips it vertically

public class FlipVertical implements Filter {

    public void filter(PixelImage theImage) {
    
        Pixel[][] data = theImage.getData(); //gets data from image
        
        //flip pixels per column, top goes to bottom and vice versa
        for (int column = 0; column < theImage.getWidth(); column++) {
            for (int row = 0; row < theImage.getHeight()/ 2; row++) { //divided by 2 since otherwise the image reverts 
            //start counting the rows from 0
            //pixel temp = temporary holder
                Pixel temp = data[row][column];
                data[row][column] = data[theImage.getHeight() - row - 1][column];
                data[theImage.getHeight() - row - 1][column] = temp;
            }
        }
        
        // update the image with the moved pixels
        theImage.setData(data);
        
}
}