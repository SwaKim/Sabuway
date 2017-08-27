package sabuway;

import sabuway.web.View;

/**
 * @Class 
 * @Description 서브웨이 매장관리 시스템
 * @author
 * @since 2017-08-25
 * @version 0.1
 * @see
 * <pre>
 *   수정일			수정자		수정내용		
 *	-------			-------		-------------------				
 *  2017.08.25		김수환		First commit		
 *  2017.08.26		김수환		프로젝트 구조
 * </pre>		
 * <pre>
 *  해야할것
 *    : 프로토타이핑툴
 * </pre>
 */
public class SubwayApplication {
	public static void main(String[] args) {
		View.makeInstance().mainMenu();
	}
}