package service;

import java.util.Collection;

import domain.ListInfos;
import domain.UseHistoryVO;
import mapper.ListMapper;

public class ListServiceImpl implements ListService{

	@Override
	public ListInfos read(String uid) {
		
		Collection<UseHistoryVO> goList = new ListMapper().read(uid, "등교");
		Collection<UseHistoryVO> backList = new ListMapper().read(uid, "하교");
		
		//리턴값은 하나만 가능하므로 collection 2개를 옮겨줄 class가 필요.
		//new ListInfos(goList, backList); 에서 마우스 오버 후 create constructor 통해 ListInfos에 필드 및 getter & setter 생성
		ListInfos listinfos = new ListInfos();
		
		listinfos.setGoList(goList);
		listinfos.setBackList(backList);
		
		return listinfos;
	}

	

	
}
