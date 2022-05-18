package com.moran.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.moran.domain.BoardAttachVO;
import com.moran.mapper.BoardAttachMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
@AllArgsConstructor
public class FileCheckTask {
	private BoardAttachMapper mapper;
	
	@Scheduled(cron="0 0 2 * * *")
	public void checkFiles() {
		log.warn("File Check Task");
		log.warn("=================================");
		log.warn(getFolderYesterday());
		
		File file = new File("c:/upload", getFolderYesterday());
		
		if(!file.exists()) {
			return;
		}
		
		log.warn("===============================");
		
		List<BoardAttachVO> dbFiles = mapper.getOldFiles(); // DB내의 파일들
//		dbFiles.forEach(log::warn);
		
		// db > file type list
		// db List<BoardAttachVO> >> List<File>
		List<File> dbFiles2 = dbFiles.stream().map(attach -> {
			File f = new File(file, attach.getUuid());
			return f;
		}).collect(Collectors.toList());
	
		// thumbnail add
		dbFiles.stream()
//		.filter(attach->attach.isImage())
		.filter(BoardAttachVO::isImage)
		.map(attach->new File(file, "s_"+attach.getUuid()))
		.forEach(dbFiles2::add);
		
//		dbFiles2.forEach(log::warn);
		
		// db에 존재하지 않는 파일 삭제
		Arrays.asList(file.listFiles(f->!dbFiles2.contains(f))).forEach(File::delete);  // 업로드 폴더내의 파일들
		
	}
	
	private String getFolderYesterday() {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date().getTime() - 1000 * 60 * 60 * 24);
	}
}
