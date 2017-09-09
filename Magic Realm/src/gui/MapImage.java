package gui;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import models.MagicRealmVariables;

public class MapImage {

	int newImageWidth = 497;
	int newImageHeight = 431;
	public BufferedImage playersTilesAndDwellings;
	int HEIGHT;
	int WIDTH;
	String folder;

	public BufferedImage rotate(int r, BufferedImage i){

		BufferedImage image = new BufferedImage(newImageWidth, newImageHeight,
				BufferedImage.TYPE_INT_ARGB);
		image = i;
		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(r), image.getWidth() / 2, image.getHeight() / 2);

		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_BILINEAR);
		return op.filter(image, null);
	}
	
	/**
	 * Method to overlay Images
	 *
	 * @param bgImage --> The background Image
	 * @param fgImage --> The foreground Image
	 * @return --> overlayed image (fgImage over bgImage)
	 */
	public static BufferedImage overlayImages(BufferedImage bgImage,
			BufferedImage fgImage,int x,int y) {

		/**
		 * Doing some preliminary validations.
		 * Foreground image height cannot be greater than background image height.
		 * Foreground image width cannot be greater than background image width.
		 *
		 * returning a null value if such condition exists.
		 */
		if (fgImage.getHeight() > bgImage.getHeight()
				|| fgImage.getWidth() > fgImage.getWidth()) {
			JOptionPane.showMessageDialog(null,
					"Foreground Image Is Bigger In One or Both Dimensions"
							+ "\nCannot proceed with overlay."
							+ "\n\n Please use smaller Image for foreground");
			return null;
		}

		/**Create a Graphics  from the background image**/
		Graphics2D g = bgImage.createGraphics();
		/**Set Antialias Rendering**/
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		/**
		 * Draw background image at location (0,0)
		 * You can change the (x,y) value as required
		 */
		g.drawImage(bgImage, 0, 0, null);

		/**
		 * Draw foreground image at location (0,0)
		 * Change (x,y) value as required.
		 */
		g.drawImage(fgImage, x, y, null);

		g.dispose();
		return bgImage;
	}

	/**
	 * This method reads an image from the file
	 * @param fileLocation -- > eg. "C:/testImage.jpg"
	 * @return BufferedImage of the file read
	 */
	public static BufferedImage readImage(String fileLocation) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(fileLocation));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	/**
	 * This method writes a buffered image to a file
	 * @param img -- > BufferedImage
	 * @param fileLocation --> e.g. "C:/testImage.jpg"
	 * @param extension --> e.g. "jpg","gif","png"
	 */
	public static void writeImage(BufferedImage img, String fileLocation,
			String extension) {
		try {
			//BufferedImage bi = img;
			Image bi=img;
			File outputfile = new File(fileLocation);
			//ImageIO.write(bi, extension, outputfile);
			ImageIO.write((RenderedImage) bi, extension, outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage zoom(int zoomLevel, MagicRealmVariables m){
		int newImageWidth = WIDTH * zoomLevel;
		int newImageHeight = HEIGHT * zoomLevel;
		BufferedImage resizedImage = new BufferedImage(newImageWidth , newImageHeight,  BufferedImage.TYPE_BYTE_BINARY);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(playersTilesAndDwellings, 0, 0, newImageWidth , newImageHeight , null);
		g.dispose();
		return playersTilesAndDwellings;
	}
	public BufferedImage setMapImage(MagicRealmVariables m) {

		HEIGHT = 431;
		WIDTH = 497;
		WIDTH=WIDTH + WIDTH/2;
		BufferedImage[] images = new BufferedImage[27];
		BufferedImage bgImage = readImage("back.gif");

		//set image for each grid value

		images[0] = readImage("tiles/spacer.gif");
		images[1] = readImage("tiles/pinewoods1.gif");
		images[2] = readImage("tiles/spacer.gif");
		images[3] = readImage("tiles/lindenwoods1.gif");
		images[4] = readImage("tiles/spacer.gif");
		images[5] = readImage("tiles/cavern1.gif");
		images[6] = readImage("tiles/badvalley1.gif");
		images[7] = readImage("tiles/maplewoods1.gif");
		images[8] = readImage("tiles/nutwoods1.gif");
		images[9] = readImage("tiles/evilvalley1.gif");
		images[10] = readImage("tiles/ledges1.gif");
		images[11] = readImage("tiles/crag1.gif");
		images[12] = readImage("tiles/darkvalley1.gif");
		images[13] = readImage("tiles/mountain1.gif");
		images[14] = readImage("tiles/caves1.gif");
		images[15] = readImage("tiles/ruins1.gif");
		images[16] = readImage("tiles/awfulvalley1.gif");
		images[17] = readImage("tiles/highpass1.gif");
		images[18] = readImage("tiles/borderland1.gif");
		images[19] = readImage("tiles/oakwoods1.gif");
		images[20] = readImage("tiles/deepwoods1.gif");
		images[21] = readImage("tiles/curstvalley1.gif");
		images[22] = readImage("tiles/spacer.gif");
		images[23] = readImage("tiles/cliff1.gif");
		images[24] = readImage("tiles/spacer.gif");
		images[25] = readImage("tiles/spacer.gif");
		images[26] = readImage("tiles/spacer.gif");

		//rotate images where applicable
		images[0] = rotate(0, images[0]);
		images[1] = rotate(60, images[1]);
		images[2] = rotate(0, images[2]);
		images[3] = rotate(120, images[3]);
		images[4] = rotate(0, images[4]);
		images[5] = rotate(120, images[5]);
		images[6] = rotate(0, images[6]);
		images[7] = rotate(180, images[7]);
		images[8] = rotate(240, images[8]);
		images[9] = rotate(180, images[9]);
		images[10] = rotate(300, images[10]);
		images[11] = rotate(240, images[11]);
		images[12] = rotate(180, images[12]);
		images[13] = rotate(0, images[13]);
		images[14] = rotate(180, images[14]);
		images[15] = rotate(180, images[15]);
		images[16] = rotate(60, images[16]);
		images[17] = rotate(180, images[17]);
		images[18] = rotate(0, images[18]);
		images[19] = rotate(300, images[19]);
		images[20] = rotate(60, images[20]);
		images[21] = rotate(60, images[21]);
		images[22] = rotate(0, images[22]);
		images[23] = rotate(0, images[23]);
		images[24] = rotate(0, images[24]);
		images[25] = rotate(0, images[25]);
		images[26] = rotate(0, images[26]);


		//overlay the images onto the background

		playersTilesAndDwellings = overlayImages(bgImage, images[0],0,HEIGHT/2);
		playersTilesAndDwellings = overlayImages(bgImage, images[1],0,1*HEIGHT+HEIGHT/2);
		playersTilesAndDwellings = overlayImages(bgImage, images[2],0,2*HEIGHT+HEIGHT/2);
		playersTilesAndDwellings = overlayImages(bgImage, images[3],0,3*HEIGHT+HEIGHT/2);
		playersTilesAndDwellings = overlayImages(bgImage, images[4],0,4*HEIGHT+HEIGHT/2);
		playersTilesAndDwellings = overlayImages(bgImage, images[5],WIDTH,HEIGHT/2);
		playersTilesAndDwellings = overlayImages(bgImage, images[6],WIDTH,1*HEIGHT+HEIGHT/2);
		playersTilesAndDwellings = overlayImages(bgImage, images[7],WIDTH,2*HEIGHT+HEIGHT/2);
		playersTilesAndDwellings = overlayImages(bgImage, images[8],WIDTH,3*HEIGHT+HEIGHT/2);
		playersTilesAndDwellings = overlayImages(bgImage, images[9],2*WIDTH,HEIGHT/2);
		playersTilesAndDwellings = overlayImages(bgImage, images[10],2*WIDTH,1*HEIGHT+HEIGHT/2);
		playersTilesAndDwellings = overlayImages(bgImage, images[11],2*WIDTH,2*HEIGHT+HEIGHT/2);
		playersTilesAndDwellings = overlayImages(bgImage, images[12],2*WIDTH,3*HEIGHT+HEIGHT/2);
		playersTilesAndDwellings = overlayImages(bgImage, images[13],497*3/4,HEIGHT);
		playersTilesAndDwellings = overlayImages(bgImage, images[14],497*3/4,2*HEIGHT);
		playersTilesAndDwellings = overlayImages(bgImage, images[15],497*3/4,3*HEIGHT);
		playersTilesAndDwellings = overlayImages(bgImage, images[16],497*3/4,4*HEIGHT);
		playersTilesAndDwellings = overlayImages(bgImage, images[17],497*3/4+WIDTH,0*HEIGHT);
		playersTilesAndDwellings = overlayImages(bgImage, images[18],497*3/4+WIDTH,1*HEIGHT);
		playersTilesAndDwellings = overlayImages(bgImage, images[19],497*3/4+WIDTH,2*HEIGHT);
		playersTilesAndDwellings = overlayImages(bgImage, images[20],497*3/4+WIDTH,3*HEIGHT);
		playersTilesAndDwellings = overlayImages(bgImage, images[21],497*3/4+WIDTH,4*HEIGHT);
		playersTilesAndDwellings = overlayImages(bgImage, images[22],497*3/4+2*WIDTH,0*HEIGHT);
		playersTilesAndDwellings = overlayImages(bgImage, images[23],497*3/4+2*WIDTH,1*HEIGHT);
		playersTilesAndDwellings = overlayImages(bgImage, images[24],497*3/4+2*WIDTH,2*HEIGHT);
		playersTilesAndDwellings = overlayImages(bgImage, images[25],497*3/4+2*WIDTH,3*HEIGHT);
		playersTilesAndDwellings = overlayImages(bgImage, images[26],497*3/4+2*WIDTH,4*HEIGHT);

		//set the dwellings
		BufferedImage inn = readImage("dwellings/inn.gif");
		BufferedImage chapel = readImage("dwellings/chapel.gif");
		BufferedImage guard = readImage("dwellings/guard.gif");
		BufferedImage house = readImage("dwellings/house.gif");
		BufferedImage ghost = readImage("dwellings/ghost.gif");
		playersTilesAndDwellings = overlayImages(bgImage, inn,m.getDwellingLocationsData().get("Inn")[0],m.getDwellingLocationsData().get("Inn")[1]);
		playersTilesAndDwellings = overlayImages(bgImage, chapel,m.getDwellingLocationsData().get("Chapel")[0],m.getDwellingLocationsData().get("Chapel")[1]);
		playersTilesAndDwellings = overlayImages(bgImage, guard,m.getDwellingLocationsData().get("Guard")[0],m.getDwellingLocationsData().get("Guard")[1]);
		playersTilesAndDwellings = overlayImages(bgImage, house,m.getDwellingLocationsData().get("House")[0],m.getDwellingLocationsData().get("House")[1]);
		playersTilesAndDwellings = overlayImages(bgImage, ghost,m.getDwellingLocationsData().get("Ghost")[0],m.getDwellingLocationsData().get("Ghost")[1]);


		//CREATE A NEW OVERLAY FOR THE CHARACTER CHITS!

		if (m.getUserClassData().contains("Amazon")){
			if(m.getUserStatusData().get("Amazon")=="Hidden"){
				folder="hiddenchars";
			} else if (m.userAlertData.get("Amazon")){
				folder="alertchars";
			} else{
				folder="characters";
			}
			BufferedImage amaz = readImage(folder + "/amazon.png");
			playersTilesAndDwellings = overlayImages(bgImage, amaz,m.getPlayerLocationsData().get("Amazon")[0]-50,m.getPlayerLocationsData().get("Amazon")[1]-50);
		}	
		if (m.getUserClassData().contains("Berserker")){
			if(m.getUserStatusData().get("Berserker")=="Hidden"){
				folder="hiddenchars";
			} else if (m.userAlertData.get("Berserker")){
				folder="alertchars";
			} else{
				folder="characters";
			}
			BufferedImage bers = readImage(folder + "/berserker.png");
			playersTilesAndDwellings = overlayImages(bgImage, bers,m.getPlayerLocationsData().get("Berserker")[0]-50,m.getPlayerLocationsData().get("Berserker")[1]-50);
		}

		if (m.getUserClassData().contains("Black Knight")){
			if(m.getUserStatusData().get("Black Knight")=="Hidden"){
				folder="hiddenchars";
			} else if (m.userAlertData.get("Black Knight")){
				folder="alertchars";
			} else{
				folder="characters";
			}
			BufferedImage black = readImage(folder + "/black_knight.png");
			playersTilesAndDwellings = overlayImages(bgImage, black,m.getPlayerLocationsData().get("Black Knight")[0]-50,m.getPlayerLocationsData().get("Black Knight")[1]-50);
		}
		if (m.getUserClassData().contains("Captain")){
			if(m.getUserStatusData().get("Captain")=="Hidden"){
				folder="hiddenchars";
			} else if (m.userAlertData.get("Captain")){
				folder="alertchars";
			} else{
				folder="characters";
			}
			BufferedImage capt = readImage(folder + "/captain.png");
			playersTilesAndDwellings = overlayImages(bgImage, capt,m.getPlayerLocationsData().get("Captain")[0]-50,m.getPlayerLocationsData().get("Captain")[1]-50);
		}

		if (m.getUserClassData().contains("Druid")){
			if(m.getUserStatusData().get("Druid")=="Hidden"){
				folder="hiddenchars";
			} else if (m.userAlertData.get("Druid")){
				folder="alertchars";
			} else{
				folder="characters";
			}
			BufferedImage druid = readImage(folder + "/druid.png");
			playersTilesAndDwellings = overlayImages(bgImage, druid,m.getPlayerLocationsData().get("Druid")[0]-50,m.getPlayerLocationsData().get("Druid")[1]-50);
		}

		if (m.getUserClassData().contains("Dwarf")){
			if(m.getUserStatusData().get("Dwarf")=="Hidden"){
				folder="hiddenchars";
			} else if (m.userAlertData.get("Dwarf")){
				folder="alertchars";
			} else{
				folder="characters";
			}
			BufferedImage dwarf = readImage(folder + "/dwarf.png");
			playersTilesAndDwellings = overlayImages(bgImage, dwarf,m.getPlayerLocationsData().get("Dwarf")[0]-50,m.getPlayerLocationsData().get("Dwarf")[1]-50);
		}

		if (m.getUserClassData().contains("Elf")){
			if(m.getUserStatusData().get("Elf")=="Hidden"){
				folder="hiddenchars";
			} else if (m.userAlertData.get("Elf")){
				folder="alertchars";
			} else{
				folder="characters";
			}
			BufferedImage elf = readImage(folder + "/elf.png");
			playersTilesAndDwellings = overlayImages(bgImage, elf,m.getPlayerLocationsData().get("Elf")[0]-50,m.getPlayerLocationsData().get("Elf")[1]-50);
		}

		if (m.getUserClassData().contains("Familiar")){
			BufferedImage fam = readImage("characters/familiar.gif");
			playersTilesAndDwellings = overlayImages(bgImage, fam,m.getPlayerLocationsData().get("Familiar")[0]-50,m.getPlayerLocationsData().get("Familiar")[1]-50);
		}

		if (m.getUserClassData().contains("Magician")){
			if(m.getUserStatusData().get("Magician")=="Hidden"){
				folder="hiddenchars";
			} else if (m.userAlertData.get("Magician")){
				folder="alertchars";
			} else{
				folder="characters";
			}
			BufferedImage magi = readImage(folder + "/magician.png");
			playersTilesAndDwellings = overlayImages(bgImage, magi,m.getPlayerLocationsData().get("Magician")[0]-50,m.getPlayerLocationsData().get("Magician")[1]-50);
		}


		if (m.getUserClassData().contains("Phantasm")){
			BufferedImage phan = readImage("characters/phantasm.gif");
			playersTilesAndDwellings = overlayImages(bgImage, phan,m.getPlayerLocationsData().get("Phantasm")[0]-50,m.getPlayerLocationsData().get("Phantasm")[1]-50);
		}

		if (m.getUserClassData().contains("Pilgrim")){
			if(m.getUserStatusData().get("Pilgrim")=="Hidden"){
				folder="hiddenchars";
			} else if (m.userAlertData.get("Pilgrim")){
				folder="alertchars";
			} else{
				folder="characters";
			}
			BufferedImage pilg = readImage(folder + "/pilgrim.png");
			playersTilesAndDwellings = overlayImages(bgImage, pilg,m.getPlayerLocationsData().get("Pilgrim")[0]-50,m.getPlayerLocationsData().get("Pilgrim")[1]-50);
		}

		if (m.getUserClassData().contains("Sorceror")){
			if(m.getUserStatusData().get("Sorceror")=="Hidden"){
				folder="hiddenchars";
			} else if (m.userAlertData.get("Sorceror")){
				folder="alertchars";
			} else{
				folder="characters";
			}
			BufferedImage sorc = readImage(folder + "/sorceror.png");
			playersTilesAndDwellings = overlayImages(bgImage, sorc,m.getPlayerLocationsData().get("Sorceror")[0]-50,m.getPlayerLocationsData().get("Sorceror")[1]-50);
		}
		if (m.getUserClassData().contains("Swordsman")){
			if(m.getUserStatusData().get("Swordsman")=="Hidden"){
				folder="hiddenchars";
			} else if (m.userAlertData.get("Swordsman")){
				folder="alertchars";
			} else{
				folder="characters";
			}
			BufferedImage sword = readImage(folder + "/swordsman.png");
			playersTilesAndDwellings = overlayImages(bgImage, sword,m.getPlayerLocationsData().get("Swordsman")[0]-50,m.getPlayerLocationsData().get("Swordsman")[1]-50);
		}
		if (m.getUserClassData().contains("White Knight")){
			if(m.getUserStatusData().get("White Knight")=="Hidden"){
				folder="hiddenchars";
			} else if (m.userAlertData.get("White Knight")){
				folder="alertchars";
			} else{
				folder="characters";
			}
			BufferedImage white = readImage(folder + "/white_knight.png");
			playersTilesAndDwellings = overlayImages(bgImage, white,m.getPlayerLocationsData().get("White Knight")[0]-50,m.getPlayerLocationsData().get("White Knight")[1]-50);
		}
		if (m.getUserClassData().contains("Witch")){
			if(m.getUserStatusData().get("Witch")=="Hidden"){
				folder="hiddenchars";
			} else if (m.userAlertData.get("Witch")){
				folder="alertchars";
			} else{
				folder="characters";
			}
			BufferedImage witch = readImage(folder + "/witch.png");
			playersTilesAndDwellings = overlayImages(bgImage, witch,m.getPlayerLocationsData().get("Witch")[0]-50,m.getPlayerLocationsData().get("Witch")[1]-50);
		}
		if (m.getUserClassData().contains("Witch King")){
			if(m.getUserStatusData().get("Witch King")=="Hidden"){
				folder="hiddenchars";
			} else if (m.userAlertData.get("Witch King")){
				folder="alertchars";
			} else{
				folder="characters";
			}
			BufferedImage witchk = readImage(folder + "/witch_king.png");
			playersTilesAndDwellings = overlayImages(bgImage, witchk,m.getPlayerLocationsData().get("Witch King")[0]-50,m.getPlayerLocationsData().get("Witch King")[1]-50);
		}

		if (m.getUserClassData().contains("Wizard")){
			if(m.getUserStatusData().get("Wizard")=="Hidden"){
				folder="hiddenchars";
			} else if (m.userAlertData.get("Wizard")){
				folder="alertchars";
			} else{
				folder="characters";
			}
			BufferedImage wizard = readImage(folder + "/wizard.png");
			playersTilesAndDwellings = overlayImages(bgImage, wizard,m.getPlayerLocationsData().get("Wizard")[0]-50,m.getPlayerLocationsData().get("Wizard")[1]-50);
		}
		if (m.getUserClassData().contains("Woods Girl")){
			if(m.getUserStatusData().get("Woods Girl")=="Hidden"){
				folder="hiddenchars";
			} else if (m.userAlertData.get("Woods Girl")){
				folder="alertchars";
			} else{
				folder="characters";
			}
			BufferedImage woodsg = readImage(folder + "/woodsgirl.png");
			playersTilesAndDwellings = overlayImages(bgImage, woodsg,m.getPlayerLocationsData().get("Woods Girl")[0]-50,m.getPlayerLocationsData().get("Woods Girl")[1]-50);
		}
		//writeImage(playersTilesAndDwellings, "playersTilesAndDwellings.gif", "gif");
		return playersTilesAndDwellings;

		//this is how you write an image to a file
		//writeImage(playersTilesAndDwellings, "back.gif", "gif");

		//this is how you rotate an image:
		//imagesg1[0][0] = rotate(180, imagesg1[0][0]); -> signifies upside down

	}


}