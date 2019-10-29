//TRAITEMENT DE SEQUENCE D'IMAGES 
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

public class VisioSeqJCV {
  
static public void main(String argv[]) {
 
  String file="C:/Users/etudiant/Desktop/TP2/blero_";
  int iMin=3;
  int iMax=25;
  
  //chargement image initiale (pour avoir les dimensions)
  IplImage img0=cvLoadImage(file+iMin+".jpg");
  int largeur=img0.width();
  int hauteur=img0.height();
  int seuil=3;
  
  //creation des fenetres de visu
  cvNamedWindow("vSeq",CV_WINDOW_AUTOSIZE);
  cvNamedWindow("vOut",CV_WINDOW_AUTOSIZE);
  
  //visualisation de la sequence
  for (int i=iMin;i<iMax ; i++) {
	  img0=cvLoadImage(file+i+".jpg");
      cvShowImage("vSeq",img0);
      cvWaitKey(30);
  }
  cvWaitKey();
  
  // declaration des tableaux de pixels pour calculs
  int[] tabPix0=new int[largeur*hauteur];
  int[] tabPix=new int[largeur*hauteur];
  CvMat Matrice0= cvCreateMat(hauteur,largeur,opencv_core.CV_8UC1);
  CvMat Matrice= cvCreateMat(hauteur,largeur,opencv_core.CV_8UC1);
  IplImage img1=cvCreateImage(cvGetSize(img0),IPL_DEPTH_8U,1);
  
  
  // Boucle de Traitement de chaque image
  for (int i=iMin+1;i<iMax ; i++) {
	  img0=cvLoadImage(file+(i-1)+".jpg");
	  img1=cvLoadImage(file+i+".jpg");
		for (int l=0; l<hauteur; l++) {
			for(int c=0; c<largeur; c++) {
				Matrice0.put((l*largeur + c), cvGet2D(img0.asCvMat(), l, c).green());
				tabPix0[l*largeur+c]=(int)cvGetReal2D(Matrice0, l, c);
				Matrice.put((l*largeur + c), cvGet2D(img1.asCvMat(), l, c).green());
				tabPix[l*largeur+c]=(int)cvGetReal2D(Matrice, l, c);
				/*tabPix[l*largeur + c]=255-tabPix[l*largeur+c];
				Matrice.put((l*largeur + c), tabPix[l*largeur + c]);*/
			}
		}
		//algo mouvement
			int [] tabOut;
			tabOut=MesTraitements.algomvt(tabPix0, tabPix, seuil, hauteur, largeur);
		for (int l=0; l<hauteur; l++) {
			for(int c=0; c<largeur; c++) {
				Matrice.put((l*largeur + c), tabOut[l*largeur + c]);
			}
			}
		img1 = Matrice.asIplImage();
		cvShowImage("vOut",img1);
		cvWaitKey(200);
   	}
  
  	cvWaitKey();
  	Matrice.release();
  	cvReleaseImage(img0);
  	cvDestroyWindow("vSeq");
  	cvDestroyWindow("vOut");
 }
}
//FIN DU PROGRAMME