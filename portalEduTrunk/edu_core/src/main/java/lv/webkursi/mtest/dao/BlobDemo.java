package lv.webkursi.mtest.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BlobDemo {

	private static Log log = LogFactory.getLog(BlobDemo.class);

	public static String sDriver = "com.mysql.jdbc.Driver";

	public static String sURL = "jdbc:mysql://localhost/blobdemo";

	public static String sUsername = "root";

	public static String sPassword = "root";

	static String[] asColors = { "Beige", "Black", "Blue", "Green", "White",
			"Yellow" };

	static String[] asImages = { "Beige4j.jpg", "Black4j.jpg", "Blue4j.jpg",
			"Green4j.jpg", "White4j.jpg", "Yellow4j.jpg" };

	public static void main(String[] args) {
		Connection con = null;
		int iRowCount = 0;
		PreparedStatement pstmt = null;
		Statement stmt = null;

		try {
			checkImageFiles();
			Class.forName(sDriver).newInstance();

			con = DriverManager.getConnection(sURL, sUsername, sPassword);
			stmt = con.createStatement();

			stmt.executeUpdate("DROP TABLE TeeColor");
			System.out.println("Table TeeColor was removed.");
			stmt.executeUpdate("CREATE TABLE TeeColor ( "
					+ "TColor VARCHAR(10) NOT NULL, "
					+ "TCBlob BLOB NOT NULL, " + "PRIMARY KEY( TColor ))");
			System.out.println("Table TeeColor was created.");

			pstmt = con.prepareStatement("INSERT INTO TeeColor VALUES( ?, ? )");

			// writing to a database
			
			for (int j = 0; j < asColors.length; j++) {
				File file = new File("edu_core/src/main/resources/"
						+ asImages[j]);
				FileInputStream fis = new FileInputStream(file);
				pstmt.setString(1, asColors[j]);
				pstmt.setBinaryStream(2, fis, (int) file.length());
				pstmt.executeUpdate();
				iRowCount++;
			}
			
			// reading from the database
			ResultSet rs = stmt.executeQuery("SELECT * FROM TeeColor");
			while (rs.next()) {
				String color = rs.getString(1);
				InputStream is = rs.getBinaryStream(2);
				int count = 0;
				int bytesRead = 0;
				byte[] buffer = new byte[1024];
				while (true) {
					bytesRead = is.read(buffer);
					if (bytesRead == -1) {
						break;
					}
					count += bytesRead;
				}
				System.out
						.println("file " + color + " has size " + count);
				
			}
		} catch (Exception e1) {
			log.error("Image insertion failed", e1);
		} finally {

			System.out.println(iRowCount + " Rows inserted into TeeColor.");

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e2) {
					log.warn("Could not close statement");
				}
			}
			try {
				pstmt.close();
			} catch (Exception e) {
				log.warn("Could not close prepared statement");
			}

			try {
				con.close();
			} catch (Exception e) {
				log.warn("Could not close connection");
			}
		}		
	}
	
	public static void checkImageFiles() throws IOException {
		for (int i = 0; i < asImages.length; i++) {
			FileInputStream fis = new FileInputStream(
					"edu_core/src/main/resources/" + asImages[i]);
			int count = 0;
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while (true) {
				bytesRead = fis.read(buffer);
				if (bytesRead == -1) {
					break;
				}
				count += bytesRead;
			}
			System.out
					.println("file " + asImages[i] + " has size " + count);
		}

	}
}
