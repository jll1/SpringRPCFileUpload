package com.example.grpc.client.grpcclient;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.InputStream;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class MatrixEndpoint {    
	private final StorageService storageService;
	GRPCClientService grpcClientService;

	@Autowired
    public MatrixEndpoint(StorageService storageService,GRPCClientService grpcClientService) {
		this.grpcClientService = grpcClientService;
		this.storageService = storageService;
	}
	@RequestMapping(value="/", method=RequestMethod.POST)
	@PostMapping("/")
	public String handleFileUpload(@RequestParam("files") MultipartFile[] files,RedirectAttributes redirectAttributes) {
		try {
			List<int[][]> l = new ArrayList<>();
			List<List<Integer>> k = new ArrayList<>();
			for(MultipartFile file : files){
				String str = new String(file.getBytes());
				Pattern p = Pattern.compile("\\d+");
	        	Matcher m = p.matcher(str);
	        	List<Integer> n = new ArrayList<Integer>();
	        	while(m.find()) {
	            	n.add(Integer.parseInt(m.group()));
	        	}
				int matrixSize = (int)Math.sqrt(n.size());
				int[][] a = new int[matrixSize][matrixSize];
				if(n.size()%4==0){
				int count = 0;
				for(int i = 0; i<matrixSize; i++) {
				    for(int j = 0; j<matrixSize; j++){
				        a[i][j] = n.get(count);
				        count +=1;
				    }
				}
				l.add(a);
				k.add(n);
				redirectAttributes.addFlashAttribute("message","You successfully uploaded " + file.getOriginalFilename());
				}else{
					return "Matrix dimensions not power of 2";
				}
			};
			if(k.get(0).size() == k.get(1).size() && k.get(0).size()%4==0){
				return grpcClientService.mult(l.get(0),l.get(1),(int)Math.sqrt(k.get(0).size()));
			}else{
				return "Matrix dimensions unequal";
			}
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}return "";
	}
}