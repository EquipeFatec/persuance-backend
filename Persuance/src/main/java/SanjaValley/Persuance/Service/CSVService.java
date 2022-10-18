package SanjaValley.Persuance.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import SanjaValley.Persuance.Entity.Palavra;
import SanjaValley.Persuance.Helper.CSVHelper;
import SanjaValley.Persuance.Repository.PalavraRepository;

@Service
public class CSVService {
  @Autowired
  PalavraRepository PalavraRepository;

  public void save(MultipartFile file) {
    try {
      List<Palavra> Palavra = CSVHelper.csvToUploadedData(file.getInputStream());
      PalavraRepository.saveAll(Palavra);

    } catch(IOException e) {
      throw new RuntimeException("Failed to store csv data: " + e.getMessage());
    }
  }

  public List<Palavra> getAllPalavra() {
    return PalavraRepository.findAll();
  }
}
