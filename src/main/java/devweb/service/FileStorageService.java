package devweb.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

  private final Path fileStorageLocation;

  @Autowired
  public FileStorageService(Environment env) {
    this.fileStorageLocation = Paths.get(env.getProperty("app.file.upload-dir", "./uploads/files"))
        .toAbsolutePath().normalize();

    try {
      Files.createDirectories(this.fileStorageLocation);
    } catch (Exception ex) {
      throw new RuntimeException(
          "Erro ao criar o diretório", ex);
    }
  }

  
  public String storeFile(MultipartFile file, String cpf) {
    // Salvar padronizado
    String fileName = cpf + ".pdf";

    try {
      // Ver se tem caracteres validos
      if (fileName.contains("..")) {
        throw new RuntimeException(
            "Erro! Caracteres inválidos");
      }

      Path targetLocation = this.fileStorageLocation.resolve(fileName);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

      return fileName;
    } catch (IOException ex) {
      throw new RuntimeException("Erro! " + fileName + ". Tente denovo!", ex);
    }
  }
}
