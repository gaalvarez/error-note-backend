/**
 * 
 */
package com.andesacademy.enote.resource;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andesacademy.enote.exception.MyFileNotFoundException;
import com.andesacademy.enote.model.File;
import com.andesacademy.enote.model.pojo.UploadFileResponse;
import com.andesacademy.enote.repository.FileRepository;
import com.andesacademy.enote.service.StorageService;

/**
 * @author a2g
 *
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FileResource {

	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private StorageService storageService;

	@PostMapping("/files")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {

		final File fileMetadata = new File();
		final String path = storageService.store(file);
		fileMetadata.setPatchFile(path);
		fileRepository.save(fileMetadata);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
				.path(fileMetadata.getId().toString()).toUriString();

		return new UploadFileResponse(file.getName(), fileDownloadUri, file.getContentType(), file.getSize());
	}

	@GetMapping("/files/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("id") Integer id, HttpServletRequest request) {
		final Optional<File> file = fileRepository.findById(id);
		if (file.isPresent()) {
			// Load file as Resource
			Resource resource = storageService.loadAsResource(file.get().getPatchFile());

			// Try to determine file's content type
			String contentType = null;
			try {
				contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			} catch (IOException ex) {
				Logger.getLogger(FileResource.class.getName()).info("Could not determine file type.");
			}

			// Fallback to the default content type if type could not be determined
			if (contentType == null) {
				contentType = "application/octet-stream";
			}

			return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
		} else {
			throw new MyFileNotFoundException("File not found id: " + id);
		}
	}
}
