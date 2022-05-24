package com.moran.mapper;

import org.apache.ibatis.annotations.Insert;

public interface SampleMapper {
	@Insert("INSERT INTO TBL_SAMPLE1 VALUES (#{str})")
	int insert1(String str);

	@Insert("INSERT INTO TBL_SAMPLE2 VALUES (#{str})")
	int insert2(String str);
}
