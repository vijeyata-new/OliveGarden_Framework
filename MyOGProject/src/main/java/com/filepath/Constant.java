package com.filepath;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Constant {

	public static Path currentRelativePath = Paths.get("");
	
	public static final String Path_Testdata=currentRelativePath.toAbsolutePath().toString()+File.separator
			+"TestData"+File.separator;
	public static final String File_Testdata="TestFile.xlsx";
	

	
}
