import com.googlecode.javacv.cpp.opencv_core.CvMat;
import com.googlecode.javacv.cpp.opencv_core.CvPoint;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_core.CvSeq;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;
import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.*;
import com.googlecode.javacv.FrameRecorder.Exception;
import com.googlecode.javacv.cpp.*;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import static com.googlecode.javacv.cpp.opencv_calib3d.*;
import static com.googlecode.javacv.cpp.opencv_objdetect.*;

public class MesTraitements {
	public static int[] algomvt(int[] tab0, int[] tab1, int seuil, int hauteur, int largeur){
		int O=0;
		int[] tabOut=tab0;
		for (int l=0; l<hauteur; l++) {
			for(int c=0; c<largeur; c++) {
				O=Math.abs(tab1[l*largeur+c]-tab0[l*largeur+c]);
			if(O>seuil){
				tabOut[l*largeur+c]=0;
			}
			else tabOut[l*largeur+c]=255;
			}
		}
		return tabOut;
	}
}
