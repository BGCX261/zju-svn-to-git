package cn.itcast.bbs.cfg;

/**
 * 用于解析配置文件中的变量(用 "${" 和 "}" 包围起来的)
 * @author 传智播客.汤阳光 Date Mar 28, 2008
 */
public class VariableExpander {
	
	private static String prefix = "${";
	private static String postfix = "}";
	
	/**
	 * 解析字符串中的变量
	 * @param preExpansion
	 * @return
	 */
	public static String expandVariables(String preExpansion) {
		
		int fIndex = preExpansion.indexOf(prefix);
		
		if (fIndex == -1) {
			return preExpansion;
		}
		
		StringBuffer sb = new StringBuffer(preExpansion);
		
		while (fIndex > -1) {
			
			int lIndex = sb.indexOf(postfix);
			
			int start = fIndex + prefix.length();
			
			String varName = sb.substring(start, lIndex).trim();
			String varValue = SystemGlobals.getValue(varName);
			
			sb.replace(fIndex, lIndex + 1, varValue);
			
			fIndex = sb.indexOf(prefix);
		}
		
		return sb.toString();
	}
}
