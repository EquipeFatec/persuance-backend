package SanjaValley.Persuance.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import SanjaValley.Persuance.Entity.Palavra;
import SanjaValley.Persuance.Entity.ResponseMessage;
import SanjaValley.Persuance.Helper.CSVHelper;
import SanjaValley.Persuance.Service.CSVService;

@CrossOrigin(origins = "*", originPatterns = "*")
@RestController
@RequestMapping(path = "/api/csv")
public class CSVUploadDataController {
	@Autowired
	private CSVService csvService;

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		if (CSVHelper.hasCSVFormat(file)) {
			try {
				csvService.save(file);
				message = "File uploaded successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not upload file: " + file.getOriginalFilename() + " .Error message: " + e.getMessage();
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}
		message = "File uploaded is not a CSV file";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}

	@GetMapping("/csvdata")
	public ResponseEntity<List<Palavra>> getAllCSVUploadData() {
		try {
			List<Palavra> csvUploadData = csvService.getAllPalavra();
			if (csvUploadData.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(csvUploadData, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

