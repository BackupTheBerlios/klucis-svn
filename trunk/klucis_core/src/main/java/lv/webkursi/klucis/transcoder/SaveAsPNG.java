package lv.webkursi.klucis.transcoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

/**
 * This class receives an SVG file name and transcodes/rasterizes it
 * as PNG - o output file has the same name, but different extension. 
 * 
 * @author kap
 */
public class SaveAsPNG {
    public static void main(String [] args) throws Exception {
    	PNGTranscoder t = new PNGTranscoder();
        String svgURL = new File(args[0]).toURI().toURL().toString();
        System.err.println("svgURI = " + svgURL);
        TranscoderInput input = new TranscoderInput(svgURL);
		String fileName = getFileName(svgURL);
        OutputStream ostream = new FileOutputStream(fileName + ".png");
        TranscoderOutput output = new TranscoderOutput(ostream);
        t.transcode(input, output);
        ostream.flush();
        ostream.close();
        System.exit(0);
    }
    
	/**
	 * Return file name without path and extension. For example, if path =
	 * "file:/C:/workspace/klucis_trunk/klucis_core/bildeXXX.svg", then return
	 * "bildeXXX"
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileName(String path) {
		int pos1 = path.lastIndexOf('/');
		String result;
		result = (pos1 == -1) ? path : path.substring(pos1 + 1);
		int pos2 = result.lastIndexOf('.');
		result = (pos2 == -1) ? result : result.substring(0, pos2);
		return result;
	}
}