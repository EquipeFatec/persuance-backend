package SanjaValley.Persuance.Helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import SanjaValley.Persuance.Entity.Palavra;


public class CSVHelper {
  public static String TYPE = "text/csv";
  public static boolean hasCSVFormat(MultipartFile file) {
      return TYPE.equals(file.getContentType());
  }
  public static List<Palavra> csvToUploadedData(InputStream is) {
    try (
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(
        fileReader,
        CSVFormat.EXCEL.withDelimiter(';').withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
      )
    ) {
      List<Palavra> uploadedData = new ArrayList<Palavra>();
      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
      for (CSVRecord csvRecord : csvRecords) {
        Palavra palavraConstructor = new Palavra();
        palavraConstructor.setPalavra(csvRecord.get(1));
        palavraConstructor.setConjucacao(csvRecord.get(2));
        palavraConstructor.setTraducao(csvRecord.get(3));

        String aprovada = csvRecord.get(4);
        boolean bAprovada = aprovada.equalsIgnoreCase("SIM");
        palavraConstructor.setAprovada(bAprovada);

        palavraConstructor.setSignificado(csvRecord.get(5));
        palavraConstructor.setExemploAprovado(csvRecord.get(6));
        palavraConstructor.setClasseGramatical(csvRecord.get(7));
        palavraConstructor.setCategoria(csvRecord.get(8));

        uploadedData.add(palavraConstructor);
      }
      return uploadedData;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

  private static Date parseDateFromString(String stringDate) {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    try {
      Date date = format.parse(stringDate);
      return date;
    } catch(ParseException error) {
      System.err.println(error);
    }
    return null;
  }
}
