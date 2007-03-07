package lv.webkursi.mtest.core.util;

public class UnitTestUtils {
	private static final String PORTAL_TEST_ROOT = "../portalTest";
	private static final String CONTEXT_FILE_PATH = "webapp/WEB-INF/portalTest-data.xml";
	private static final String RDF_DATA_DIR = "webapp/WEB-INF/data";
		
	public static String getPortalTestRoot() {
		String portalTestRoot = System.getProperty("portal.test.root");
		if (portalTestRoot == null) {
			portalTestRoot = PORTAL_TEST_ROOT;
		}		
		return portalTestRoot;
	}
	
	public static String getContextFilePath() {	
		String contextFilePath = System.getProperty("context.file.path");
		if (contextFilePath == null) {
			contextFilePath = CONTEXT_FILE_PATH;
		}
		return getPortalTestRoot() + "/" + contextFilePath;
	}
	
	public static String getRdfDataDir() {
		return getPortalTestRoot() + "/" + RDF_DATA_DIR;
	}
}
