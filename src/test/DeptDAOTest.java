package test;

import java.util.ArrayList;

public class DeptDAOTest {
	public static void main(String[] args) {
		DeptDAO dao = new DeptDAO();
		dao.insert(new DeptVO(2000, "기획", null, null));	
//		dao.update(new DeptVO(1000, "영업", null, null));		
//		dao.delete(new DeptVO(1000, null, null, null));
//		dao.selectOne(new DeptVO(110, null, null, null));
		
//		DeptVO deptVO = dao.selectOne(new DeptVO(10,null,null,null));
//		System.out.println(deptVO);
	
//		ArrayList<DeptVO> list = dao.selectAll(null);
//		System.out.println(list);
	}
}

