public class GaussianFilter implements Filter {

    @Override
    public void filter(PixelImage theImage) {

        theImage.GaussianBlur();

    }

}
