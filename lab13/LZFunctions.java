public class LZFunctions {
    static {
        System.loadLibrary("LZFunctions");
    }

    private native int lzOpenSourceFile(String pathToSourceFile);
    private native int lzOpenDestinationFile(String pathToDestinationFile);
    private native void lzCopyData(int pathToSource, int pathToDestinationFile);
    private native void lzCloseFile(int handle);

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            System.err.println("Invalid number of arguments");
            System.exit(1);
        }

	LZFunctions lzFunctions = new LZFunctions();

        String sourceFilePath = args[0];
        String destinationFilePath = args[1];

        int hSrcFile = lzFunctions.lzOpenSourceFile(sourceFilePath);
        int hDestFile = lzFunctions.lzOpenDestinationFile(destinationFilePath);

        lzFunctions.lzCopyData(hSrcFile, hDestFile);
        
        lzFunctions.lzCloseFile(hSrcFile);
        lzFunctions.lzCloseFile(hDestFile);
    }
}
