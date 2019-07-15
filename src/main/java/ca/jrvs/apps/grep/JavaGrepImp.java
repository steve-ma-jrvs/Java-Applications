package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class JavaGrepImp implements JavaGrep {

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
    if (args.length != 3) {
      try {
        throw new IllegalAccessException("USAGE: regex rootPath outFile");
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Top level search workflow
   *
   * @throms IOException
   */
  @Override
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<>();
    String rootPath = getRootPath();

    for (File file : listFilesLambda(rootPath)) {
      for (String line : readLinesLambda(file)) {
        if (containsPattern(line)) {
          matchedLines.add(line);
        }
      }
    }

    writeToFile(matchedLines);
  }

  /**
   * Traverse a given directory and return all files
   *
   * @param rootDir input directory
   * @return files under the rootDir
   */

  public List<File> listFilesLambda(String rootPath) throws IOException {
    return Files.walk(Paths.get(rootPath)).filter(Files::isRegularFile).map(Path::toFile)
        .collect(Collectors.toList());
  }

  @Override
  public List<File> listFiles(String rootDir) {
    List<File> results = new ArrayList<>();
    File path = new File(rootDir);
    File[] files = path.listFiles();

    for (File file : files) {
      results.add(file);
    }
    return results;
  }


  public List<String> readLinesLambda(File inputFile) throws IOException {
    return Files.lines(Paths.get(inputFile.getPath())).collect(Collectors.toList());
  }

  /**
   * Read a file and return all the lines
   *
   * Explain FileReader, BufferedReader, and character encoding
   *
   * @param inputFile file to be read
   * @return lines
   * @throws IllegalAccessException if a given inputFile is not a File
   */
  @Override
  public List<String> readLines(File inputFile) {
    if (!inputFile.isFile()) {
      try {
        throw new IllegalAccessException(inputFile + "is not a file!");
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    ArrayList<String> lines = new ArrayList<>();
    BufferedReader reader;

    try {
      reader = new BufferedReader(new FileReader(inputFile));

      String line = reader.readLine();
      while (line != null) {
        lines.add(line);
        line = reader.readLine();
      }
      reader.close();

    } catch (FileNotFoundException ee) {
      ee.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return lines;
  }

  /**
   * check if a line contains the regex pattern (passed by user)
   *
   * @param line input string
   * @return true if there is a match
   */
  @Override
  public boolean containsPattern(String line) {

    return line.matches(getRegex());
  }

  /**
   * Write lines to a file
   * <p>
   * Explore: FileOutputStream, OutputStreamWriter, and BufferedWriter
   *
   * @param lines matched line
   * @throws IOException if write failed
   */
  @Override
  public void writeToFile(List<String> lines) throws IOException {

    File file = new File(getOutFile());
    FileOutputStream fs = new FileOutputStream(file);
    OutputStreamWriter ow = new OutputStreamWriter(fs);
    BufferedWriter writer = new BufferedWriter(ow);

    for (String line : lines) {
      writer.write(line);
      writer.write("\n");
    }
    writer.flush();
    fs.close();

  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}
