package net.mirechoi.miflex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.mirechoi.miflex.dto.FileDTO;

@Mapper
public interface FileMapper {

	FileDTO fileBy(long id); //선택된 파일 하나
	List<FileDTO> listFileByBId(long bid); //글번호
	void insertFile(FileDTO file);
	void updateById(long id);//선택된 파일 업데이트
	int deleteFileByid(long id);
	int deleteFileBybid(long id);
}
