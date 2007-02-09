package lv.webkursi.klucis.transcoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;

/**
 * This class receives an SVG file name and transcodes/rasterizes it
 * as JPEG - output file is always out.jpg 
 * 
 * @author Batik standard sample by Apache
 */
public class SaveAsJPEG {
	public static void main(String[] args) throws Exception {
		JPEGTranscoder t = new JPEGTranscoder();
		t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(.8));
		String svgURL = new File(args[0]).toURI().toURL().toString();
		TranscoderInput input = new TranscoderInput(svgURL);
		OutputStream ostream = new FileOutputStream("out.jpg");
		TranscoderOutput output = new TranscoderOutput(ostream);
		t.transcode(input, output);
		ostream.flush();
		ostream.close();
		System.exit(0);
	}
}