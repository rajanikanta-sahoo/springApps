package com.sdrc.mongo.services;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;

import com.sdrc.mongo.utill.GeneretEmployeePDF;

@Service
public class EmployeePdfDetailsImpl implements EmployeePdfDetails{

	@Override
	public File creatPdfDetails() {
		
		File fileOut =new GeneretEmployeePDF().cretePdf(null);
		
		return fileOut;
	}

}
