/**
 * 
 */
package com.andesacademy.enote.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(fileMetadata.getId().toString())
                .toUriString();

        return new UploadFileResponse(file.getName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

}
